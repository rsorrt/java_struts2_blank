package application.resource;

import it.sgsbpvn.common.host.CCIConnectionFactory;

import java.io.File;
import java.io.FileNotFoundException;
import java.net.URL;

import javax.mail.Session;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.resource.cci.ConnectionFactory;
import javax.sql.DataSource;
import javax.transaction.UserTransaction;

//import rsorrt.commons.sgs.ConstantSGS;

public class ResourceJndi  {

	private static final String ENV_USERTRANSACTION = "java:comp/UserTransaction";
	
	private static final String ENV_PREFIX = "java:comp/env/";
	private static Context ENV_CONTEXT = null;

	
	public static UserTransaction getUserTransaction() throws Exception {
		
		Object obj = new InitialContext().lookup(ENV_USERTRANSACTION);
		
		if ( obj instanceof UserTransaction) {
			return (UserTransaction)obj;
		} else {
			throw new Exception("Jndi getUserTransaction: obj is not UserTransaction type");
		} //
		
	} //
	
	// get object by lookup in Context
	public static Object getObject(String name) throws Exception {

		if (ENV_CONTEXT == null) {
			try {
				ENV_CONTEXT = (Context) new InitialContext().lookup("java:comp/env");
			} catch (Exception e) {
				throw new Exception("Jndi.getObject: ENV_CONTEXT error: " + e.toString());
			} //try

		} // if

		try {
			return ENV_CONTEXT.lookup(name);
		} catch (Exception e) {
			throw new Exception("Jndi.getObject: lookup error for name: '" + name + "' " + e.toString());
		} //try

	} // getObject
	
	
	public static DataSource getDataSource(String name) throws Exception {

		Object obj = getObject(name);
	
		if (obj == null) {
			throw new NullPointerException(
					"FactoryJndi.getDataSource obj not found in context for name:" + name);
		} // if

		if (obj instanceof DataSource) {
			return (DataSource) obj;
		} // if

		throw new NamingException(
				"FactoryJndi.getDataSource obj found in context for name:"
						+ name + " is not a DataSource type but " + obj.getClass().getName());

	} // getDataSource

	public static URL getURL(String name) throws Exception {

		Object obj = getObject(name);

		if (obj == null) {
			throw new NullPointerException(
					"FactoryJndi.getUrl obj not found in context for name:"	+ name);
		} //

		if (obj instanceof URL) {
			return (URL) obj;
		} //

		throw new Exception("For JndiName: '" + name + "'  obj found is not URL");

	}

	public static String getURLPath(String name) throws Exception {
		
		URL url = getURL(name);
		String protocol = url.getProtocol();
		if (protocol != null && protocol.equalsIgnoreCase("file")) {
			File f = new File(url.getFile());
			return f.getAbsolutePath();
		} //
		throw new Exception("Jndi.getUrlPath: protocol is not file but: " + protocol);
	}
	
	
	public static String getURLPathFileExisting(String name) throws Exception {
		
		String path = getURLPath(name);
		
		File f = new File(path);
		
		if ( !f.exists() ) {
			throw new FileNotFoundException("FactoryJndi.getURLPathFileExisting: file not existing for path: '" + path + "'");
		} //

		if ( !f.isFile() ) {
			throw new FileNotFoundException("FactoryJndi.getURLPathFileExisting: dir found not file for path: '" + path + "'");
		} //
		
		
		return path;
	}
	
	
	public static String getURLPathDirExisting(String name) throws Exception {
		
		String path = getURLPath(name);
		File f = new File(path);
		
		if ( !f.exists() ) {
			throw new FileNotFoundException("FactoryJndi.getURLPathFileExisting: file not existing for path: '" + path + "'");
		} //
		
		if ( !f.isDirectory() ) {
			throw new FileNotFoundException("FactoryJndi.getURLPathFileExisting: file found not dir for path: '" + path + "'");
		} //
		
		return path;
	}
	
	
	public static CCIConnectionFactory getCCIConnectionFactory(String name)	throws Exception {
		
		Object obj = getObject(name);
		
		if ( obj == null) {
			throw new Exception(
					"Jndi.getConnFactory: object found for name: '" + name + "' is null");
		} //
		
		if (! (obj instanceof ConnectionFactory) ) {
			throw new Exception(
					"Jndi.getConnFactory: object found for name: '" + name + "' is type: " +
					obj.getClass().getName() + 
					" not javax.resource.cci.ConnectionFactory as expected" );
		} //
		
		// il lookup lo fa internamente la classe CCIConnectionFactory
		CCIConnectionFactory cciConnectionFactory = CCIConnectionFactory.getECIConnectionFactory("java:comp/env/" + name);
		return cciConnectionFactory;
		
	}
	
	
	public static Session getMailSession(String name) throws Exception {
		Object obj = getObject(name);
		if (obj == null) {
			throw new NullPointerException(
					"FactoryJndi.getMailSession obj not found in context for name:" + name);
		} // if
		if (obj instanceof Session) {
			return (Session) obj;
		} // if
		throw new NamingException(
				"FactoryJndi.getMailSession obj found in context for name:"
				+ name + " is not a javax.mail.Session type but " + obj.getClass().getName()
		);
	} // getMailSession

	
	// clean lookup name from the ENC (Environment Naming Context) prefix
	public static String normalizeName(String name) {
		if (name != null && name.startsWith(ENV_PREFIX)) {
			return name.substring(14);
		}
		return name;
	} //normalizeName

	
	/*
	public static CCIConnectionFactory getCCIConnectionFactorySGS(String abi)
			throws Exception {

		String istituto = ConstantSGS.getIstituto(abi);

		if (StringUtils.isBlank(istituto)) {
			throw new Exception(
					"Jndi.getConnFactorySGS: istituto not found for abi:" + abi);
		} //

		String name = ConstantSGS.JNDI_SERVER_CICS_PREFIX + istituto;

		if (StringUtils.isBlank(name)) {
			throw new Exception(
					"Jndi.getConnFactorySGS: jndi name not found for abi " + abi);
		} //

		return getCCIConnectionFactory(name);
		
	} //
	
	*/
	
	
	// eof
}
