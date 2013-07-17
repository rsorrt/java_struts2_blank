package application.resource;



import it.sgsbpvn.common.host.CCIConnectionFactory;

import java.io.File;
import java.io.FileInputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.mail.Session;
import javax.sql.DataSource;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.PropertyConfigurator;

import application.Constant;
import application.motore.bean.Istituti;
import application.motore.bean.Istituto;
import application.motore.config.Config;
import application.motore.config.ConfigInitializer;
import application.motore.factory.FactoryResource;


public class AppResourcesInitializer {
	
	// la prima inizializzazione a buon fine setta il lock a true
	private static boolean isLock = false;
	
	private Map<String, List<String>> errors     = new LinkedHashMap<String, List<String>>();
	private Map<String, List<String>> messages   = new LinkedHashMap<String, List<String>>();
	
	
	public AppResourcesInitializer()  {
	} // 		
		
	
	public void initialize(String pathDirApp) {
		
		if ( isLock ) {
			addError("LOCKED", "LOCK ATTIVO, AppResourcesInitializer E' GIA' STATO USATO");
			return;
		}//
		
		AppResources.pathDirApp = pathDirApp;

		try {
		
			// configuro Log4j
			initLog4j("url/pathFileLog4j");
			
			Config config = initConfig();
			if ( config == null ) {
				addError("Config", "Error in File Config");
				return;
			} //
	
			
			initCics(config);
			initDataSources(config);
			initDataSource(config);
			initDirDati(config);
		
			initUrlXSSO(config);
			
			initXSSO(config);
			
			checkMailSession(config);
			
		} catch (Exception e) {
			addError("ECCEZIONE", e);
			return;
		}
		
		
		setIsLock(true);
		
	}
	
	
	
	public int sizeErrors() {
		return errors.size();
	} //
	
	public int messagesErrors() {
		return messages.size();
	} //
	
	
	
	public String toString() {
		
		String ret =  this.getClass().getName();
		
		List<String> list = null;
		
		ret ="\nMESSAGES: " + messages.size();
		
		for (String idMsg: messages.keySet()) {
			list = messages.get(idMsg);
			ret += "\n\n " +
					idMsg + 
					"\n-------------------------------";
			for (String msg: list) {
				ret += "\n  " + msg;
			} //for
		} //for
		
		if ( errors.size() > 0 ) {
			ret += "\n\nERRORS: " + errors.size();
			for	(String idError: errors.keySet()) {
				list = errors.get(idError);
				ret += "\n\n " +
						idError +
						"\n-------------------------------";
				
				if ( list != null ) {
					for (String error: list) {
						ret += "\n    " + error;
					} //for
				} else {
					ret += "\n    list is null";
				}//

				
						
			}//for
		} //if 
		
		return ret;
	}
	
	// CONFIG
	//
	private Config initConfig() {
		
		String id = "initConfig";
		
		try {

			// il nomeJndi del config va recuperato via Constant
			String jndiName = 	Constant.JNDI_PATH_FILE_CONFIG;
			
			addMsg(id,"jndiName: " + jndiName);
			
			String path = ResourceJndi.getURLPathFileExisting(jndiName);

			addMsg(id, "path from jndi: " + path);

			AppResources.pathFileConfig = path;
			Config config = ConfigInitializer.get(path);
			AppResources.config = config;
			
			if (config.getIsErrors()) {
				addError(id, config.getErrors());
				return null;
			} else {
				addMsg(id, "OK");
			} //
			
			return config;

		} catch (Exception e) {
			addError(id, e);
			return null;
		}
		
	} //setConfig
	

	
	
	private boolean initLog4j(String jndiName) {
		String id = "initLog4j";
		
		try {
			
			//String jndiName = config.getJndiNamePathFileLog4j();
			addMsg(id, "jndiName: " + jndiName);
			
			String path =  ResourceJndi.getURLPathFileExisting(jndiName);
			addMsg(id, "path from jndi: " + path);
			
			File f = new File(path);
			if ( ! f.exists()) {
				addError(id, "file not found: " + path);
			}//
			
			addMsg(id, "PropertyConfigurator.configure: " + path);
			PropertyConfigurator.configure(path);
			addMsg(id, "PropertyConfigurator.configure OKAY");
			
			AppResources.pathFileLog4j = path;
		
			addMsg(id, "OK");
			return true;
			
			} catch (Exception e) {
				addError(id, e);
				return false;
			}
			
	} //initLog4j
	
	
	
	
	
	
	// CICS
	//
	private boolean initCics (Config config)  {
		
		String id = "initCics";
		boolean esito = true;
		
		try {
			
		Istituti istituti = config.getHostIstituti();
		
		Map<Integer, CCIConnectionFactory> map = new HashMap<Integer, CCIConnectionFactory>();	
		
		for(Istituto istituto: istituti) {
			
			String nome = istituto.getNome();
			int abi = istituto.getAbi();
			//String ambiente = istituto.getAmbiente();
			String letteraIstituto = istituto.getLetteraIstituto();
			
			String jndiName = ""; 
			
			//if ( UtilString.isNotBlank(ambiente) && ambiente.equalsIgnoreCase("NEWREL")) {
			//	jndiName = config.getJndiNameCicsPrefix() + letteraIstituto + "_newrel" ;
			//} else {
				jndiName = config.getJndiNameCicsPrefix() + letteraIstituto;
			//}//
			
			addMsg(id, "istituto: " + nome + " abi: " + abi + " cicsJndiName: " + jndiName);
			
			try {
				CCIConnectionFactory cciConnectionFactory = ResourceJndi.getCCIConnectionFactory(jndiName);
				if ( cciConnectionFactory == null ) {
					esito = false;
				} else {
					map.put(abi, cciConnectionFactory);
				} //
			} catch (Exception e) {
				addError(id, e);
				esito = false;
			}
			
		} //for istituti
		
		AppResources.cicsIstituti = map;
		
		if (esito) {
			addMsg(id, "OK");
		} 
		
		return esito;

		} catch (Exception e) {
			addError(id, e);
			return false;
		}
		
	}

	
	//init in APPLICATION SCOPE the DataSources
	private boolean initDataSources (Config config)  {
		
		String id = "initDataSources";
		boolean esito = true;
		
		try {
			
		Istituti istituti = config.getHostIstituti();	
		
		Map<Integer, DataSource> map = new HashMap<Integer, DataSource>();	
		
		String jndiNameDataSource = config.getJndiNameDataSourcePrefix();
	//	Connection conn = null;
	//	DatabaseMetaData metadata = null;
		DataSource ds = null;
		
		for(Istituto istituto: istituti) {
			
			String nome = istituto.getNome();
			int abi = istituto.getAbi();
			String abiX = StringUtils.leftPad(String.valueOf(abi), 5, "0");
			String jndiName = jndiNameDataSource + abiX;
						
			addMsg(id, "datasourceJndiName: " + jndiName);
			
			try {
				
				ds = ResourceJndi.getDataSource(jndiName);
				
				if (ds == null) {
					addError(id, "dataSource jndiName: " + jndiName + " is null");
					esito = false;
				} else {
					
					/*
					conn = ds.getConnection();
					metadata = conn.getMetaData();
					log (
						"  url: " + metadata.getURL() +
						"  productName: " + metadata.getDatabaseProductName() 
					) ;
					conn.close();
					*/
					
					map.put(abi, ds);
			
				} //if
				
			} catch (Exception e) {
				addError(id, e);
				esito = false;
			}
			
		} //for istituti
		
		//application.setAttribute(Constant.APP_DATASOURCES, map);
		AppResources.dataSourcesIstituti = map;
		
		if (esito) {
			addMsg(id, "OK");
		} 
		
		return esito;

		} catch (Exception e) {
			addError(id, e);
			return false;
		}
		
	}
	
	private boolean initDirDati(Config config) {
		String id = "initDirDati";
		try {
			
			String jndiName = config.getJndiNamePathDirDati();
			addMsg(id, "jndiName:" + jndiName);
			
			String pathDirDati = ResourceJndi.getURLPathDirExisting(jndiName);
			addMsg(id, "path from jndi: " + pathDirDati);
			
			AppResources.pathDirDati = pathDirDati;
			
			addMsg(id, "OK");
			return true;

		} catch (Exception e) {
			addError(id, e);
			return false;
		}
		
	} //setDirDati
	
	
	
	
	
	/*
	private boolean initMailSession(Config config) {
		String id = "initMailSession";
		try {
			String jndiName = config.getJndiNameMailSession();
			AppResources.mailSession = ResourceJndi.getMailSession(jndiName);
			String des = "";
			Properties props = AppResources.mailSession.getProperties();
			for (Enumeration e = props.keys() ; e.hasMoreElements() ;) {
				String key = (String)e.nextElement();
				des += key + " = " + props.get(key) + "\n";
			}
			addMsg(id, des);
			addMsg(id, "OK");
		    return true;
		} catch (Exception e) {
			addError(id, e);
			return false;
		}		
	}//
	*/
	
	
	
	
	
	
	

	
	private boolean initDataSource (Config config) {
		
		String id = "initDataSource";
		
		try {
			String jndiName = 	config.getJndiNameDataSource();
			addMsg(id, "jndiName: " + jndiName);
			DataSource dataSource = ResourceJndi.getDataSource(jndiName);
			
			AppResources.dataSource = dataSource;
			addMsg(id, "OK");
			return true;
			
		} catch (Exception e) {
			addError(id, e);
			return false;
		}
	} //

	
	
	public void checkMailSession(Config config) {
		String id = "checkMailSession";
		try {
			addMsg(id,"jndiName: " + config.getJndiNameMailSession() );
			Session mailSession = FactoryResource.getMailSession(config);
			Properties props = mailSession.getProperties();
			addMsg(id," mailSession properties: " );
			for (Enumeration e = props.keys() ; e.hasMoreElements() ;) {
				String key = (String)e.nextElement();
				addMsg(id,"  " + key + "=" +  props.get(key) );
			} //for
		} catch (Exception e) {
			addError(id, e);
		}
	}//checkMailSession
	
	
	
	//------------------------------------------------
	
	
	private boolean initUrlXSSO(Config config) {
		String id ="initUrlXSSO";
		try	{
			String jndiName = config.getJndiNameUrlXSSO();
			addMsg(id,"jndiName:" + jndiName);
			URL url = ResourceJndi.getURL(jndiName);
			addMsg(id, "URL:" + url);
			AppResources.urlXSSO = url.toString();
			addMsg(id, "OK");
			return true;
		} catch (Exception e) {
			addError(id, e);
			return false;
		}
	} //initUrlXXSO
	
	
	
	//------------------------------------------------
	
	
	
	
	private boolean initXSSO(Config config) {
		
		String id = "initXSSO";
		
		try {

			String jndiName = config.getJndiNamePathFileXSSO();
			addMsg(id, "jndiName:" + jndiName);
			
			String path = ResourceJndi.getURLPathFileExisting(jndiName);
			addMsg(id, "path from jndi: " + path);
			
			byte[] cipherKey = null; 
			
			try {
				File f = new File(path);
				FileInputStream fis = new FileInputStream(f);
				cipherKey = new byte[fis.available()];
		        fis.read(cipherKey);
		        fis.close();
			} catch (Exception e) {
				addError(id, "File is not existing: " + path);
				return false;
			}
	        
			AppResources.xssoCipherKey = cipherKey;
			addMsg(id, "OK");
			return true;

		} catch (Exception e) {
			addError(id, e);
		}

		return false;
		
	} //setXSSO
	
	
	
/*
	private boolean initDAO (DataSource dataSourceDB2,	DataSource dataSourceORACLE) {
		
		boolean esito = false;
		
		try {
			
			DAO dao = FactoryDAO.get(null, AppResources.getDataSource());
			appResources.setDao(dao);
			appResources.save(session);
			
			return true;
			
		} catch (Exception e) {
			log("checkDAO: ERROR: " + e);
			return false;
		}
		
	} //
*/


	public boolean getIsError () {
		return isError();
	} //

	public boolean isError () {
		return errors.size() > 0 ;
	} //
	

	
	
	// PRIVATE
	//-----------------------------------------------
	

	
	private void addError(String id, Exception e) {
		addError(id, e.toString());
	} //
	
	private void addError(String id, String error) {
		List<String> list = errors.get(id);
		if ( list == null ) {
			list = new ArrayList<String>();
			errors.put(id, list);
		} //
		list.add(error);
	} //

	
	private void addMsg(String id, String msg) {
		List<String> list = messages.get(id);
		if ( list == null ) {
			list = new ArrayList<String>();
			messages.put(id, list);
		} //
		list.add(msg);
	} //

	public static boolean getIsLock() {
		return isLock;
	}
	public static boolean isLock() {
		return isLock;
	}
	public static void setIsLock(boolean isLock) {
		AppResourcesInitializer.isLock = isLock;
	}


	

	/*
	private String getTime() {
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		Date d = new Date();
		return dateFormat.format(d);
	} //
	*/
	
		
	
}
