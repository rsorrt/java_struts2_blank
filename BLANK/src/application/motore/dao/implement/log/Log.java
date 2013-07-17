package application.motore.dao.implement.log;


import application.motore.constant.Esito;
import rsorrt.commons.util.UtilDateTime;

public class Log {

	//yyyymmdd
	private int data = 0;
	//hhmmss
	private int ora = 0;
	
	private String metodo = "";
	
	private String loggerId ="";	
	private String hostName="";	
	
	private String descrizione="";	
	private String eccezione="";
	private String msg="";
	
	private Esito esito = Esito.KO;
	private long  elapsedMs = 0;
	
	public Log() {
		this.data = UtilDateTime.getYYYYMMDD();
		this.ora  = UtilDateTime.getHHMMSS();
	}
	
	public Esito getEsito() {
		return esito;
	}

	public void setEsito(Esito esito) {
		this.esito = esito;
	}

	public long getElapsedMs() {
		return elapsedMs;
	}

	public void setElapsedMs(long elapsedMs) {
		this.elapsedMs = elapsedMs;
	}


	
	public int getData() {
		return data;
	}
	public void setData(int data) {
		this.data = data;
	}
	public int getOra() {
		return ora;
	}
	public void setOra(int ora) {
		this.ora = ora;
	}
	
	public String getHostName() {
		return hostName;
	}
	public void setHostName(String hostName) {
		this.hostName = hostName;
	}
	public String getDescrizione() {
		return descrizione;
	}
	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}
	public String getEccezione() {
		return eccezione;
	}
	public void setEccezione(String eccezione) {
		this.eccezione = eccezione;
	}
	public void setEccezione(Exception e) {
		this.eccezione = e.toString();
	}



	public String getLoggerId() {
		return loggerId;
	}

	public void setLoggerId(String loggerId) {
		this.loggerId = loggerId;
	}	

	public String getMetodo() {
		return metodo;
	}

	public void setMetodo(String metodo) {
		this.metodo = metodo;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}
	
}
