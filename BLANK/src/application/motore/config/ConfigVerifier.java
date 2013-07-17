package application.motore.config;

import org.apache.commons.lang.StringUtils;



public class ConfigVerifier {

		
	
	private ConfigVerifier() {
	}
	
	public static boolean getIsErrors(Config conf){
		return StringUtils.isNotBlank(getErrors(conf));
	}
	
	
	public static String getErrors(Config conf){
		return null;
	}
	
		
}
