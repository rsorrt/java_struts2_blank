
package application.motore.bean;

import it.sgsbpvn.common.host.CCIConnectionFactory;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;

import javax.mail.Session;
import javax.sql.DataSource;



import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;

import application.motore.config.Config;
import application.motore.config.ConfigInitializer;

import rsorrt.commons.bean.MyMail;
import rsorrt.commons.dao.jdbc.JdbcUtil;
import rsorrt.commons.factory.FactoryLogger;
import rsorrt.commons.factory.FactoryMailSession;
import rsorrt.commons.log.MyLogger;
import rsorrt.commons.sgs.ConstantSGS;
import rsorrt.commons.util.UtilCtg;



public class Test extends BaseBean {

	public static final String PROXY_HOST = "proxypool.intranet.servizi"; 
	public static final String PROXY_PORT = "8080";

	
	public static  final MyLogger logger = FactoryLogger.getLogger();
	public static final Logger loggerEmpty = FactoryLogger.getLoggerEmpty();
	
	public static final String separator = "------------------------------------";
	
	public  static final  String abiVERONA  = "5188";
	public  static final  String abiCLI     = "6200";
	
	public static final String pathAbsoluteApplication = new File("").getAbsolutePath();
	
	public static final String pathFileConfig =
			"D:/LANGUAGES/JAVA/SCRIPTS/PROGETTI/WEBAPPS/VCWEB/FILES/config/config.properties";
	
	public static final String pathDirDati =
			"D:/LANGUAGES/JAVA/SCRIPTS/PROGETTI/WEBAPPS/VCWEB/FILES/dati";
	
	public static final String pathDirElaborazioniDisallineamentiMonitoraggioCerved = "D:/LANGUAGES/JAVA/SCRIPTS/PROGETTI/WEBAPPS/VCWEB/FILES/dati/elaborazioni/disallineamenti/monitoraggio/cerved";
	
	public static final String cervedUrl = "http://www.cerved.com";
	
	public static Config config = null;
	
	public static MyMail mail = null;

	
	public static final DataSource dataSourceMYSQL = JdbcUtil.getDataSourceMYSQL(
			"vcweb","vcweb",
			"jdbc:mysql://localhost/vcweb"
	);
	
	public static final DataSource dataSourceORACLE = JdbcUtil.getDataSourceORACLE(
			"VCWEB", 
			"VC123.web",
			"jdbc:oracle:thin:@sgsvrdborat03lx.servizi:1551:UNIT4"
	);
	
	
	public static final DataSource datasourceDB2_HOLDING = JdbcUtil.getDataSourceDB2(
			"VCPUSER", 
			"12QWASZX",
			"jdbc:db2://ITBPVRDB2A.plexvr01.mvs.zserver:7020/ITBPVRDB2H:currentSchema=VCAHOL;"
	);

	
	
	
	static {
		
		try {
			config =  ConfigInitializer.get(pathFileConfig);
		    
			Session mailSession = FactoryMailSession.get("10.183.230.116", 8080);
			mail = new MyMail(mailSession);
			
		} catch (Exception e) {
			logger.warn("testId", "Creazione CONFIG ERROR: " + e);
		}
		
	}//
	
	
	public static  void print(String title, String[] arr) {
		System.out.println( 
			separator +
			"\n" + title + ":n " + arr.length +
			"\n" + separator
		);
		int count = 0 ;
		for (String s : arr) {
			System.out.println(" " + count + ") " + s);
		} //
		System.out.println(separator);
	} //
	
	
	
	public static void print(String title, List list) {
		System.out.println(
			"\n" + separator +
			"\n" + title + ": " + list.size() +
			"\n" + separator
		);
		int count = 0 ;
		for (Object o : list) {
			count++ ;
			System.out.println(" " + count + ") " + o);
		} //
		System.out.println(separator);
	} //
	
	

	public static String getFileContentFromTestDir(String nomeFile) throws Exception {
		String pathDir = "..//FILES//test";
		String pathFile = pathDir + File.separator + nomeFile;
		File f = new File(pathFile);
		if ( !f.exists()) {
			throw new FileNotFoundException("IL FILE: " + pathFile + " NON ESISTE");
		} 
		
		return FileUtils.readFileToString(f);
		
	}	
	//


	
	public static CCIConnectionFactory getConnFactoryTest(
			  String abi
				) throws Exception {
		return getConnFactoryTest(Integer.parseInt(abi));
	}
	
	
	public static CCIConnectionFactory getConnFactoryTest(
			  int abi
				) throws Exception {

			boolean verbose = false;
	    	
	 	    String istituto = ConstantSGS.getIstituto(abi);
	 	    
	 	    String serverName = istituto + "T01";
	 	    String connectionUrl = "tcp://ctgsvl.intranet.servizi"; 
	 	    int portNumber = 2006;
	 	    
	 	    //String serverNameProduzione = "TPW0";
	 	    //String connectionUrlProduzione = "ssl://ctgprdsslsso.intranet.servizi";
	 	    //int portNumberProduzione = 8051;
	 	    
	 	    //serverName = serverNameProduzione;
	 	    //connectionUrl = connectionUrlProduzione;
	 	    //portNumber = portNumberProduzione;
	 	    
	 	    // cics collaudo
	 	    //serverName = istituto + "C11";
	 	    //connectionUrl = "ssl://ctgsvlsslsso.intranet.servizi";
	 		//portNumber = 8051;
	 	   
	 	    System.out.println("\nCreo ConnFactory per abi: " + abi + " server: " + serverName + "\n");
	 	    
	 	   CCIConnectionFactory connFactory = 
		    		UtilCtg.getConnFactory(
		    				verbose, 
		    				connectionUrl,
		    				portNumber,
		    				serverName,
		    				null, null
		    		);	
		
		
	 	   return connFactory;
	 	   
		} //

	
	
	
	public static void setProxy() {
		 // PROXY
		System.getProperties().put("proxySet","true");
		System.getProperties().put("proxyPort",PROXY_PORT + "");
		System.getProperties().put("proxyHost",PROXY_HOST);
	} //
	
	
	
	public static final void exit() {
		System.exit(0);
	}//	
	
	
	
	
public static void main(String[] args) throws Exception {
	
	// System.out.println(UtilDateTime.formatDateTimeMilliseconds(new Date()));
	// Util.sleepMs(235);
	// System.out.println(UtilDateTime.formatDateTimeMilliseconds(new Date()));
	

	
	//String path = "D:/temp/cervedXML.zip";
	//byte[] b = FileUtils.readFileToByteArray(new File(path));
//	String sBase64 = UtilCrypt.base64Encode(b);
	//System.out.println(sBase64);
	
}


public static final String username = "GS01160"; 
public static final String password = "merluzzo";
public static final User user = new User(username, password);
public static final User cervedUser_VERONA = new User("BPN5", "CERVED");

	
//END OF CLASS
}
