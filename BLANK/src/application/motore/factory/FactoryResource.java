package application.motore.factory;


import javax.mail.Session;
import javax.sql.DataSource;


import org.apache.log4j.PropertyConfigurator;

import application.Constant;
import application.motore.config.Config;
import application.motore.config.ConfigInitializer;
import application.resource.ResourceJndi;

public class FactoryResource {
	
	public static DataSource getDataSource(Config config, String abi) throws Exception {
		String jndiNameDataSourcePrefix = config.getJndiNameDataSourcePrefix();
		String jndiName = jndiNameDataSourcePrefix + abi;
		return ResourceJndi.getDataSource(jndiName);
	}
	
	public static DataSource getDataSource(Config config) throws Exception {
		String jndiName = config.getJndiNameDataSource();
		return ResourceJndi.getDataSource(jndiName);
	}
	
	public static Config getConfig() throws Exception {
			String path = ResourceJndi.getURLPathFileExisting(Constant.JNDI_PATH_FILE_CONFIG);
			return ConfigInitializer.get(path);
	}//
	
	public static String getPathdirDati(Config config) throws Exception {
		String jndiName = config.getJndiNamePathDirDati();
		return ResourceJndi.getURLPathDirExisting(jndiName);
	}
	
	public static void log4J(Config config) throws Exception {
		String jndiName = "url/pathFileLog4j";
		String path =  ResourceJndi.getURLPathFileExisting(jndiName);
		PropertyConfigurator.configure(path);
	}
	
	public static Session getMailSession(Config config) throws Exception {
		String jndiName = config.getJndiNameMailSession();
		return ResourceJndi.getMailSession(jndiName);
	}
	
	
// END OF CLASS	
}