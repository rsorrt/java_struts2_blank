package application.resource;

import it.abaco.xsso.client.XSSOClient;
import it.sgsbpvn.common.host.CCIConnectionFactory;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

import rsorrt.commons.factory.FactoryLogger;
import rsorrt.commons.log.MyLogger;

import application.Constant;
import application.motore.bean.Istituti;
import application.motore.bean.Istituto;
import application.motore.bean.User;
import application.motore.config.Config;
import application.motore.dao.DAO;


public class AppResources {
	
	public static final String ID = Constant.APP_RESOURCES;
	
	
	private static final MyLogger logger = FactoryLogger.getLogger();
	
	//VARIABILI APPLICATION
	//usate a livello di applicazione quindi comuni a tutti gli user
	
	// path dir di root dell'applicazione
	static String pathDirApp;
	
	static Config config;
	static String pathFileConfig;
	
	static String pathDirDati;
	static String pathFileLog4j;

	
	static String pathFileDocEsempiRicercaAnagraficaPersona;
	static String pathFileDocEsempiVisura;
	static String pathFileTestVisure;
	static String pathDirElaborazioniDisallineamentiMonitoraggioCerved;
		
	static String urlXSSO;
	static String urlCerved;
	
	static DataSource dataSource;
	static Map<Integer, DataSource> dataSourcesIstituti;
	static Map<Integer, CCIConnectionFactory> cicsIstituti;
	
	static byte[] xssoCipherKey;
	
	// VARIABILI USER
	// variabili a livello di user
	
	private Istituti istitutiAmmessi;
	private Istituto istituto;
	private User user;
	private DAO dao;
	
	// chiamata xsso cross
	private XSSOClient xsso;
	
	private String theme = application.Constant.theme;
	private List<String> themes = application.Constant.themes;
		
	//identifica una sessione chiamata da una applicazione esterna
	//per avere un servizio
	private boolean servizio = false;
	
	// restituisce l'oggetto AppResource in Session, se non esiste lo crea
	public static AppResources get(Map<String, Object> session)  {
		AppResources appResources = (AppResources)session.get(ID);
		if ( appResources == null ) {
			appResources =  new AppResources();
		} 
		return appResources;
	}
	
	// per le pagine jsp
	public static AppResources get(HttpSession session)  {
		AppResources appResources = (AppResources)session.getAttribute(ID);
		if ( appResources == null ) {
			appResources =  new AppResources();
		} 
		return appResources;
	}
	
	// per le pagine jsp
		public void save(HttpSession session)  {
			session.setAttribute(ID, this);
		}
	
	// per le pagine jsp
	//	public static AppResources get(ServletContext application)  {
	//		return (AppResources)application.getAttribute(ID);
	//	}
	
	
	public void save(Map<String, Object> session) {
		session.put(ID, this);
	} 
	
	public void invalidate(Map<String, Object> session) {
		session.put(ID, null);
	} //
	
	
	public String toString() {
		
		return 
		
		"\n------------------------------------------------------" +
		"\nAPPLICATION CONTEXT" +
		"\n------------------------------------------------------" +
		"\n	pathDirApp: " + pathDirApp +
		"\n	pathFileConfig: " + pathFileConfig +
		"\n	config: " + (config!=null) +
		"\n	pathDirDati: " + pathDirDati +
		"\n	pathFileLog4j: " + pathFileLog4j +
		"\n xssoCipherKey: " + xssoCipherKey +
		
		"\n dataSource: " +  dataSource +
		"\n dataSourcesIstituti: " + dataSourcesIstituti +
		"\n cicsIstituti: " + cicsIstituti +
		
		"\n\n	PATH:" +
		"\n  pathFileDocEsempiRicercaAnagraficaPersona: " + pathFileDocEsempiRicercaAnagraficaPersona + 
		"\n  pathFileDocEsempiVisura: " + pathFileDocEsempiVisura + 
		"\n  pathFileTestVisure: " + pathFileTestVisure + 
		"\n  pathDirElaborazioniDisallineamentiMonitoraggioCerved: " + pathDirElaborazioniDisallineamentiMonitoraggioCerved + 
		
		"\n\n URL:" +
		"\n  urlXSSO: " + urlXSSO +
		"\n  urlCerved: " + urlCerved +
		
		"\n\n------------------------------------------------------" +
		"\nSESSION CONTEXT" +
		"\n------------------------------------------------------" +
		"\n istitutiAmmessi: " + istitutiAmmessi +
		"\n istituto: " + istituto +
		"\n user: " + user +
		"\n dao: " + dao +
		"\n xsso: " + xsso +
		"\n\n THEME:" +
		"\n  theme : " + theme
 
		;		
		
		
		
	}
	//
	

	
	public static Config getConfig() {
		if ( AppResources.config == null ) {
			logger.fatal("config is null");
		}
		return AppResources.config;
	}
	
	public static String getPathFileConfig() {
		return AppResources.pathFileConfig;
	}
	
	
	public static String getPathDirApp() {
		if ( AppResources.pathDirApp == null ) {
			logger.fatal("pathDirApp is null");
		}
		return AppResources.pathDirApp;
	}
	
	
	public static String getPathDirDati() {
		if ( AppResources.pathDirDati == null ) {
			logger.fatal("pathDirDati is null");
		}
		return AppResources.pathDirDati;
	}
	
	
	public static String getPathFileLog4j() {
		if ( AppResources.pathFileLog4j == null ) {
			logger.fatal("pathFileLog4j is null");
		}
		return AppResources.pathFileLog4j;
	}
	
	public static String getPathFileDocEsempiRicercaAnagraficaPersona() {
		if ( AppResources.pathFileDocEsempiRicercaAnagraficaPersona == null ) {
			logger.error("AppResources.pathFileDocEsempiRicercaAnagraficaPersona is null");
		}
		return AppResources.pathFileDocEsempiRicercaAnagraficaPersona;
	}
		
	public static String getPathFileDocEsempiVisura() {
		if ( AppResources.pathFileDocEsempiVisura == null ) {
			logger.error("AppResources.pathFileDocEsempiVisura is null");
		}
		return AppResources.pathFileDocEsempiVisura;
	}
	
	public static String getPathFileTestVisure() {
		if ( AppResources.pathFileTestVisure == null ) {
			logger.error("AppResources.pathFileTestVisure is null");
		}
		return AppResources.pathFileTestVisure;
	} //
	
	public static String getPathDirElaborazioniDisallineamentiMonitoraggioCerved() {
		if ( AppResources.pathDirElaborazioniDisallineamentiMonitoraggioCerved == null ) {
			logger.error("AppResources.pathDirElaborazioniDisallineamentiMonitoraggioCerved is null");
		}
		return AppResources.pathDirElaborazioniDisallineamentiMonitoraggioCerved;
	} //
	
	

	
	public static String getUrlXSSO() {
		if ( AppResources.urlXSSO == null ) {
			logger.fatal("AppResources.urlXSSO is null");
		}
		return AppResources.urlXSSO;
	}
	
	public static String getUrlCerved() {
		if ( AppResources.urlCerved == null ) {
			logger.fatal("AppResources.urlCerved is null");
		}
		return AppResources.urlCerved;
	}
	
	
	
	public static DataSource getDataSource() {
		if ( AppResources.dataSource == null ) {
			logger.warn("AppResources.dataSource is null");
		}
		return AppResources.dataSource;
	}


	public static Map<Integer, DataSource> getDataSourcesIstituti() {
		if ( AppResources.dataSourcesIstituti == null ) {
			logger.fatal("AppResources.dataSourcesIstituti is null");
		}
		return AppResources.dataSourcesIstituti;
	}


	public static Map<Integer, CCIConnectionFactory> getCicsIstituti() {
		if ( cicsIstituti == null ) {
			logger.fatal("AppResources.cicsIstituti is null");
		}
		return AppResources.cicsIstituti;
	}

	

	public static byte[] getXSSOCipherKey() {
		if ( xssoCipherKey == null ) {
			logger.fatal("AppResources.xssoCipherKey is null");
		}
		return AppResources.xssoCipherKey;
	}

	
	
	// NON STATICHE
	//-------------------------------------------------------
	
	
	public DataSource getDataSourceIstituto () {
		if ( dataSourcesIstituti == null ) {
			//logger.fatal("dataSourcesIstituti is null");
		}
		return dataSourcesIstituti.get(getAbi());
	} //
	
	public static DataSource getDataSourceIstituto (int abi) {
		if ( dataSourcesIstituti == null) {
			logger.fatal("dataSourcesIstituti is null");
		}
		return dataSourcesIstituti.get(abi);
	} //
	
	
	public int getAbi() {
		if ( istituto == null) {
			logger.fatal("istituto is null");
		}
		return istituto.getAbi();
	} //
	
	public String getAbiString() {
		if ( istituto == null) {
			logger.fatal("istituto is null");
		}
		return String.valueOf(istituto.getAbi());
	} //
	
	
	public CCIConnectionFactory getCicsIstituto() {
		if ( cicsIstituti == null) {
			logger.fatal("cicsIstituti is null");
		}
		return cicsIstituti.get(getAbi());
	} //
	
	
	public static CCIConnectionFactory getCicsIstituto(int abi) {
		if ( cicsIstituti == null) {
			logger.fatal("cicsIstituti is null");
		}
		return cicsIstituti.get(abi);
	} //
	
	
	public Istituti getIstitutiAmmessi() {
		if ( istitutiAmmessi == null) {
			logger.fatal("istitutiammessi is null");
		}
		return istitutiAmmessi;
	}

	public  void setIstitutiAmmessi(Istituti istitutiAmmessi) {
		this.istitutiAmmessi = istitutiAmmessi;
	}

	public Istituto getIstituto() {
		//if ( istituto == null) {
			//logger.fatal("istituto is null");
		//}
		return istituto;
	}

	public Istituto getIstitutoQuietly() {
		return istituto;
	}
	
	public void setIstituto(Istituto istituto) {
		this.istituto = istituto;
	}

	public User getUser() {
		if ( user == null) {
			//logger.fatal("user is null");
		}
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	

	public DAO getDao() {
		if ( dao == null) {
			logger.fatal("dao is null");
		}
		return dao;
	}

	public void setDao(DAO dao) {
		this.dao = dao;
	}

	
	public String getTheme() {
		return theme;
	}//
	public void setTheme(String theme) {
		this.theme = theme;
	}//
	
	public List<String> getThemes() {
		return themes;
	}//

	public void setXsso(XSSOClient xsso) {
		this.xsso = xsso;
	}	
	
	public XSSOClient getXsso() {
		return this.xsso;
	}	
	
	public boolean isServizio() {
		return servizio;
	}
	
	public void setServizio(boolean servizio) {
		this.servizio = servizio;
	}
	
}
