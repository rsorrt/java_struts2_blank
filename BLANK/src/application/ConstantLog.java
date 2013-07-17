package application;

import java.io.File;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import rsorrt.commons.util.UtilString;

public class ConstantLog {
	
	public static final String PROD_1 = "sgsvraswas001lx.servizi";
	public static final String PROD_2 = "sgsvraswas002lx.servizi";
	public static final String PROD_3 = "sgsvraswas003lx.servizi";
	public static final String PROD_4 = "sgsvraswas004lx.servizi";
	public static final String PROD_5 = "sgsvraswas005lx.servizi";
	public static final String PROD_6 = "sgsvraswas006lx.servizi";
	
	public static final String TEST_1 = "sgsvraswast01lx.servizi";
	public static final String TEST_2 = "sgsvraswast02lx.servizi";
	
	public static final String ROB    = "LU0464663DXP";
	
	public static final Map<String, String> appLog    = new HashMap<String, String>();
	public static final Map<String, String> systemLog = new HashMap<String, String>();
	
	
	static {
		appLog.put(PROD_1, "http://waslog61.intranet.servizi/prod/Prod01Node01");
		appLog.put(PROD_2, "http://waslog61.intranet.servizi/prod/Prod01Node02");
		appLog.put(PROD_3, "http://waslog61.intranet.servizi/prod/Prod01Node09");
		appLog.put(PROD_4, "http://waslog61.intranet.servizi/prod/Prod01Node07");
		appLog.put(PROD_5, "http://waslog61.intranet.servizi/prod/Prod01Node03");
		appLog.put(PROD_6, "http://waslog61.intranet.servizi/prod/Prod01Node11");
		appLog.put(TEST_1, "http://waslog61.intranet.servizi/coll/Coll01Node01");
		appLog.put(TEST_2, "http://waslog61.intranet.servizi/coll/Coll01Node02");
		appLog.put(ROB, "D:/LANGUAGES/JAVA/SCRIPTS/PROGETTI/WEBAPPS/VCWEB/FILES/log");
		
		systemLog.put(PROD_1, "http://waslog61.intranet.servizi/prod/Prod01Node01/Prod01_ep");
		systemLog.put(PROD_2, "http://waslog61.intranet.servizi/prod/Prod01Node02/Prod02_ep");
		systemLog.put(PROD_3, "http://waslog61.intranet.servizi/prod/Prod01Node09/Prod09_ep");
		systemLog.put(PROD_4, "http://waslog61.intranet.servizi/prod/Prod01Node07/Prod07_ep");
		systemLog.put(PROD_5, "http://waslog61.intranet.servizi/prod/Prod01Node03/Prod03_ep");
		systemLog.put(PROD_6, "http://waslog61.intranet.servizi/prod/Prod01Node11/Prod11_ep");
		systemLog.put(TEST_1, "http://waslog61.intranet.servizi/coll/Coll01Node01/Coll01_ep");
		systemLog.put(TEST_2, "http://waslog61.intranet.servizi/coll/Coll01Node02/Coll02_ep");
		systemLog.put(ROB, "D:/LANGUAGES/JAVA/SCRIPTS/PROGETTI/WEBAPPS/VCWEB/FILES/log");

	
	
	}//
	
	public static String getAppLog(String hostName) throws Exception {
		return getLog(getUrlAppLog(hostName));
	}
	public static String getSystemLog(String hostName) throws Exception {
		return getLog(getUrlSystemLog(hostName));
	}


	
	
	public static String getUrlAppLog(String hostName) {
		return appLog.get(hostName) + "/vcweb.log"; 
	}//
	
	public static String getUrlSystemLog(String hostName) {
		return systemLog.get(hostName) + "/SystemOut.log"; 
	}//
	
	
	
	
	
	private static String getLog(String address) throws Exception {
		if (UtilString.startsWith(address, "http")) {
			return getFromURL(address);
		} else {
			return getFromFile(address);
		}//
		
	}//
	
	public static String getFromURL(String address) throws Exception {
		URLConnection conn = new URL(address).openConnection();
		Scanner scan = new Scanner(conn.getInputStream());
		return getContent(scan);
	}//
	
	public static String getFromFile(String address) throws Exception {
		Scanner scan = new Scanner(new File(address));
		return getContent(scan);
	}//

	
	private static String getContent(Scanner scan) throws Exception {
		StringBuilder content = new StringBuilder();   
		while(scan.hasNext()) {
		  content.append("\n").append(scan.nextLine().trim());
		}//
		scan.close();
		return content.toString();
	}//

	
	
	public static void main(String[] args) throws Exception {
		
		 
		System.out.println(getLog(ROB));
		
	}
	
	
	
	
}
