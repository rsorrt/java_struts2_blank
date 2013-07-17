package application.motore.config;

import rsorrt.commons.factory.FactoryLogger;
import rsorrt.commons.log.MyLogger;
import application.motore.bean.Test;


public class ConfigTest extends Test {

	public final static MyLogger logger = FactoryLogger.getLogger();
	
	public static void main(String[] args) throws Exception {
		
		String pathFile = "D:\\LANGUAGES\\JAVA\\SCRIPTS\\PROGETTI\\WEBAPPS\\VCWEB\\FILES\\config\\config.properties";
		Config conf = ConfigInitializer.get(pathFile);
		System.out.println(conf);
		
		
		boolean isErrors =  config.getIsErrors();
		System.out.println("\nConfigIsErrors: " + isErrors );
		if ( isErrors) {
			System.out.println("\nCONFIG ERRORS:\n" + config.getErrors());
		} //
	
		
		//Istituti istituti = config.getHost_istituti();
		//System.out.println(istituti);
		
		//System.out.println(config.getProfiloAdmin());
		
		//print("profiliNomiSenzaADMIN", config.getProfiliNomiSenzaADMIN());
		//print("profiliNomi", config.getProfiliNomi());
		
		//print("profiliSenzaADMIN", config.getProfiliSenzaADMIN());
		//print("profili", config.getProfili());
		
		//System.out.println(conf.getCervedUsers());
		
		//User user = conf.getCervedUsers().get("05188");
		//System.out.println(user);
		
		
	}
	

	
}
