package application;

import java.util.ArrayList;
import java.util.List;

public class Constant {
	
	
	public static final List<String> themes = new ArrayList<String>();
	public static String theme = "redmond";
	
	static {
		//themes.add("base");
		themes.add("black-tie");
		themes.add("blitzer");
		themes.add("cupertino");
		themes.add("dark-hive");
		themes.add("dot-luv");
		themes.add("eggplant");
		themes.add("excite-bike");
		themes.add("flick");
		themes.add("hot-sneaks");
		themes.add("humanity");
		themes.add("le-frog");
		themes.add("mint-choco");
		themes.add("overcast");
		themes.add("pepper-grinder");
		themes.add("redmond");
		themes.add("smoothness");
		themes.add("south-street");
		themes.add("start");
		themes.add("sunny");
		themes.add("swanky-purse");
		themes.add("trontastic");
		themes.add("ui-darkness");
		themes.add("ui-lightness");
		themes.add("vader");
	}//
	
	
	
	
	
	
	
	public static final String APP_RESOURCES = "APP_RESOURCES";
	
	public static final String APP_NAME = "BLANK";
	
	public static final String JNDI_PATH_FILE_CONFIG = "url/pathFileConfig";
		
	
	public static final String APP_CONFIG = "APP_CONFIG";
	public static final String APP_USER   = "APP_USER";
	
	
	public static final String APP_LOG4J   = "APP_LOG4J";
	
	// istituto scelto dall'utente
	// oppure unico cui era abilitato
	public static final String APP_ISTITUTO   = "APP_ISTITUTO";
	
	// istituti ammessi per l'utente
	public static final String APP_ISTITUTI_AMMESSI   = "APP_ISTITUTI_AMMESSI";
	
	
	// cics factory degli istituti
	public static final String APP_CICS_FACTORY   = "APP_CICS_FACTORY";
	
	// datasource
	public static final String APP_DATASOURCE   = "APP_DATASOURCE";
	
	// datasources degli istituti
	public static final String APP_DATASOURCES   = "APP_DATASOURCES";
	
	public static final String APP_XSSO = "APP_XSSO";
	
	public static final String APP_DIRDATI = "APP_DIRDATI";
	
	public static final String APP_DAO = "APP_DAO";
	
	
}
