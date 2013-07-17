package application.motore.dao.implement.log;


import application.motore.constant.Esito;
import rsorrt.commons.bean.MyElapsed;
import rsorrt.commons.dao.jdbc.Jdbc;
import rsorrt.commons.util.UtilNumber;

public class Prova extends application.motore.bean.Test {

	public static void main(String[] args){
		
		MyElapsed elapsed = new MyElapsed();
		
		rsorrt.commons.dao.jdbc.Jdbc jdbc = new Jdbc(dataSourceORACLE);
		TableLog tableLog = new TableLog(jdbc);
		
		String[] hosts = {"host1", "host2", "host3", "host4"	};
		String[] eccezioni = {"", "ECI_ERR_RESPONSE_TIMEOUT", "ABEND", "ECI_ERR_SECURITY", "cartesio"	};
		
		String[] esiti = {Esito.OK.toString(), Esito.KO.toString()	};
		
		//String[] = {"", "", "", ""	};
		
		
		try {
		
		jdbc.open();
		
		int numLogs = 500;
		for (int i = 0; i < numLogs; i++) {
		
			
		System.out.println(numLogs - i);
			
		elapsed.start();
			
		Log log = new Log();
		log.setHostName(getRandom(hosts));
		
		log.setEsito(Esito.valueOf(getRandom(esiti)));
		
		log.setMetodo("Sono il metodo");
		log.setDescrizione("descrivo");
		log.setEccezione(getRandom(eccezioni));
		
		
		elapsed.end();
		log.setElapsedMs(elapsed.getMillis());
		
		tableLog.add(log);
		
		}
		
		
			
		} catch (Exception e) {
			
			System.out.println(e);
			
		} finally {
			jdbc.closeQuietly();
		}//
		
		System.out.println("THAT'S ALL FOLKS");
		
		
	}//main
	
	
	private static String getRandom(String[] arr) {
		int max = arr.length;
		int i = UtilNumber.getRandom(0, max - 1);
		return arr[i];
	} // 
	
	


}
