package application.struts2.listener;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import rsorrt.commons.factory.FactoryLogger;
import rsorrt.commons.log.MyLogger;

import application.Constant;
import application.motore.config.Config;
import application.motore.factory.FactoryMail;
import application.resource.AppResources;
import application.resource.AppResourcesInitializer;

import com.opensymphony.xwork2.ActionSupport;


public class ApplicationListener extends ActionSupport implements
		ServletContextListener {

	private final static MyLogger logger = FactoryLogger.getLogger();
	
	private static final long serialVersionUID = -123239521046619339L;
		
	private ServletContext application;
	private String pathDirDati;
	private AppResources appResources = new AppResources();
	
	private String msg = "";
	private String mailSubject = "";
	
	
	private Config config;
	
	public void contextInitialized(ServletContextEvent event) {

		try {
			
		
		application = event.getServletContext();

		logLine();
		log(Constant.APP_NAME + " APPLICATION STARTING...");
		logTime();
		logLine();

		String pathDirApp = event.getServletContext().getRealPath(".");

		//since servelt 2.5 - WAS è 2.4
		//String pathContext = event.getServletContext().getContextPath();
		//System.out.println("\n\n==============PATH_CONTEXT: " + pathContext);
		
		//------------------------------------------------------------------
		AppResourcesInitializer initializer = new AppResourcesInitializer();
		initializer.initialize(pathDirApp);
		log(initializer.toString());
		logSeparator();
		
		if ( ! initializer.isError() ) {
			logOK();
		} else {
			logKO(
				"\n=========> ATTENZIONE ERRORI PRESENTI <=========" + 
				"\n" +
				initializer
			);
		} // if
	
		
		config = AppResources.getConfig();
		
		if ( config.isSimulazioneHost()) {
			log(
					"\n       --------------------        " +
					"\n------>MODALITA SIMULAZIONE<-------" +
					"\n       --------------------        " +
					"\n"
				);
		}//
		
		logLine();
		log(Constant.APP_NAME + " APPLICATION STARTED.");
		logTime();
		logLine();
		
		
		
		
	
		
		/*
		if ( config.getMailIsSend()) {
			boolean mailSent = mailSend(config.getMailSubject() + " - App started", msg);
			log("Mail sent: " + mailSent);
		} //
		*/
		
		log();
		log();
		log("end of log.");
		
		logger.warn(msg);
		
		} catch (Exception e) {
			
			System.out.println(Constant.APP_NAME + " ECCEZIONE " + e);
			logger.error("ECCEZIONE " + e);
	        FactoryMail.send(config, "VCWEB STARTED CON ECCEZIONE: " + msg + " ECCEZIONE: " + e);
			
		} 		
	} //

	public void contextDestroyed(ServletContextEvent event) {
		logger.warn("VCWEB CONTEXT DESTROYED");
	}
		
	private String getTime() {
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		Date d = new Date();
		return dateFormat.format(d);
	} //

	private void logTime() {
		log(getTime());
	} //

	private void logEsito(boolean esito) {
		log("ESITO: " + esito);
	} //
	
	private void log() {
		log("\n");
	} //
	
	
	
	
	private void log(Exception e) {
		log(e.toString());
	} //
	
	private void logOK() {
		log(
			"\n#################################" +
			"\n#----->    OK " + Constant.APP_NAME + " OK    <-----#" +
			"\n       " + getTime() + 
			"\n#################################"
		);
	} //

	private void logKO(String msg) {
		log(
			"\n#################################" +
			"\n#----->!!! KO " + Constant.APP_NAME + " KO !!!<-----#" +
			"\n" + msg +
			"\n       " + getTime() + 
			"\n#################################"
		);
	}
		
	
	private void logSeparator() {
		log("-----------------------------------------");
	} //
	
	private void logLine() {
		log("___________________________________________________");
	} //
	
	
	private void log(String msg) {
		this.msg += "\n" + msg;
		//}
	} //
	
	
	
	
	
	
} // END OF FILE
