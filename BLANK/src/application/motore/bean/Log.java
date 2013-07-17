package application.motore.bean;


public class Log {

	
	protected String host;
	protected String nome;
	protected String descrizione;
	protected String path;
		
	public Log() {
	}
	
	
	public Log(String nome, String path) {
		this.path = path;
	}
	
	
	public String toString() {
		return 
		" host: " + host +
	    " nome: " + nome +
		" descrizione: " + descrizione +
		" path: " + path
		;
	}
	
	public String getDescrizione() {
		return descrizione;
	}
	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}
	


	public String getHost() {
		return host;
	}


	public void setHost(String host) {
		this.host = host;
	}


	public String getNome() {
		return nome;
	}


	public void setNome(String nome) {
		this.nome = nome;
	}


	public String getPath() {
		return path;
	}


	public void setPath(String path) {
		this.path = path;
	}
	
	
}
