package application.motore.factory;

import javax.mail.Session;

import application.motore.config.Config;

import rsorrt.commons.bean.MyMail;
import rsorrt.commons.factory.FactoryLogger;
import rsorrt.commons.log.MyLogger;

public class FactoryMail {
	
	private static final MyLogger logger = FactoryLogger.getLogger();
	
	public static MyMail get(Config config) throws Exception {
			Session mailSession = FactoryResource.getMailSession(config);
			MyMail mail = new MyMail(mailSession);
			//MyMail mail = new MyMail (config.getSmtpHost(), config.getSmtpPort());
			//mail.setAddressFrom(config.getMailFrom());
			mail.setAddressesTo(config.getMailTo());
			mail.setSubject(config.getMailSubject());
			return mail;
	} //get
		
	
	public static void send(Config config, String text) {
		try {
			MyMail mail = get(config);
			mail.setAddressesTo(config.getMailTo());
			mail.setSubject(config.getMailSubject());
			mail.setText(text);
			mail.send();
		} catch (Exception e) {
			logger.warn("MAIL SENDING ERROR: " + e);
		}
		
		
} //get
	
	
// end of file		
} //
	
	