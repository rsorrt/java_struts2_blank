package application.struts2.listener;


//import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import rsorrt.commons.factory.FactoryLogger;
import rsorrt.commons.log.MyLogger;



public class SessionListener implements HttpSessionListener {

	private final static MyLogger logger = FactoryLogger.getLogger();
	
	private String id;
	//private ServletContext servletContext;
	
	public void sessionCreated(HttpSessionEvent evt) {
			
		//try {
		//	HttpSession session = evt.getSession();
		//	String sessionId = session.getId();
			//System.out.println("--<SESSIONID: " + sessionId);
		//} catch (Exception e) {
		//	logger.warn("SessionId non recuperato: " + e);
		//}
		 //HttpSession session = evt.getSession();
		 //ServletContext context = session.getServletContext();
		
		//this.id = session.getId();
		//logger.info("SESSION CREATED ID: " + id);

	}

	public void sessionDestroyed(HttpSessionEvent evt) {
		//logger.info("SESSION DESTROYED ID: " + id);
	}



}
