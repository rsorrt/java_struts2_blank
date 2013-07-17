package application.motore.bean;

import java.util.Date;

import application.motore.bean.profili.Funzione;
import application.motore.bean.profili.Funzioni;
import application.motore.bean.profili.Profilo;

public class User {
	
	// univoco quindi chiave
	protected String username;
	
	protected boolean isAdmin = false;
	
	protected String nome;
	protected String cognome;
	protected String codiceUfficio;
	protected String abi;
	protected String password;
	protected String email;
	protected Date   dateLogin;
	
	
	// attenzione profilo deve esistere
	protected Profilo profilo = new Profilo();
	
	
	
	public User() {
	}
	
	public User(String username, String password) {
		this.username = username;
		this.password = password;
	}
	
	public User(String username) {
		this.username = username;
	}

	
	
	public boolean hasFunzione(Funzione funzione) {
		return profilo.hasFunzione(funzione) || isAdmin;
	} //
	
	public boolean hasFunzione(int id) {
		return profilo.hasFunzione(id) || isAdmin;
	} //
	
	public static final Funzione  VISUALIZZAZIONE_VISURA           = new Funzione(1, "VISUALIZZAZIONE VISURA", "Visualizzazione Visura - link menu");
	public static final Funzione  VISUALIZZAZIONE_VISURA_STORICA   = new Funzione(2, "VISUALIZZAZIONE VISURA STORICA", "Checkbox tipo visura storica"); 
	
	// RICERCA ANAGRAFICA
	public static final Funzione  RICERCA_ANAGRAFICA = new Funzione(3, "RICERCA ANAGRAFICA", "Ricerca Anagrafica - Link menu");
	
	// MONITORAGGIO
	public static final Funzione  MONITORAGGIO  = new Funzione(4, "MONITORAGGIO", "Monitoraggio - Link menu");
	public static final Funzione  MONITORAGGIO_RIACQUISTA_VISURA = new Funzione(5, "MONITORAGGIO RIACQUISTA VISURA", "Riacquista Visura");
	
	//------------------------------------------
	public boolean hasFunzione_visualizzazioneVisura() {
		return hasFunzioneOrIsAdmin(Funzioni.VISUALIZZAZIONE_VISURA);
	} //
	public boolean hasFunzione_visualizzazioneVisuraStorica() {
		return hasFunzioneOrIsAdmin(Funzioni.VISUALIZZAZIONE_VISURA_STORICA);
	} //
	public boolean hasFunzione_ricercaAnagrafica() {
		return hasFunzioneOrIsAdmin(Funzioni.RICERCA_ANAGRAFICA);
	} //
	public boolean hasFunzione_monitoraggio() {
		return hasFunzioneOrIsAdmin(Funzioni.MONITORAGGIO);
	} //
	public boolean hasFunzione_monitoraggioRiacquistaVisura() {
		return hasFunzioneOrIsAdmin(Funzioni.MONITORAGGIO_RIACQUISTA_VISURA);
	} //
	
	
	private boolean hasFunzioneOrIsAdmin(Funzione funzione) {
		return profilo.hasFunzione(funzione) || isAdmin;
	}
	
	//------------------------------------------
	
	public String toString() {
		return 
			"\n-------------------------------------" +
			"\nclass: " + this.getClass().getName() +
			"\n-------------------------------------" +
			"\nusername: " + username +
			"\nisAdmin: " + isAdmin +
			"\nnome: " + nome +
			"\ncognome: " + cognome +
			"\ncodiceUfficio: " + codiceUfficio +
			"\nabi: " + abi +
			"\nprofilo: " +
			profilo +
			"\n-------------------------------------"
		;
	}
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public String getPasswordTrunc() {
		if ( password != null) {
			return password.substring(0,2);
		} else {
			return "null";
		} //

	}
	
	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public Date getDateLogin() {
		return dateLogin;
	}


	public void setDateLogin(Date dateLogin) {
		this.dateLogin = dateLogin;
	}


	public String getNome() {
		return nome;
	}


	public void setNome(String nome) {
		this.nome = nome;
	}


	public String getCognome() {
		return cognome;
	}


	public void setCognome(String cognome) {
		this.cognome = cognome;
	}


	public String getCodiceUfficio() {
		return codiceUfficio;
	}


	public void setCodiceUfficio(String codiceUfficio) {
		this.codiceUfficio = codiceUfficio;
	}


	public String getAbi() {
		return abi;
	}


	public void setAbi(String abi) {
		this.abi = abi;
	}


	public Profilo getProfilo() {
		return profilo;
	}


	public void setProfilo(Profilo profilo) {
		this.profilo = profilo;
	}

	
	
	public boolean getIsAdmin() {
		return isAdmin;
	}
	public boolean isAdmin() {
		return isAdmin;
	}
	
	public void setIsAdmin(boolean isAdmin) {
		this.isAdmin = isAdmin;
	}
	

}
