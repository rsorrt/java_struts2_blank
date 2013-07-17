package application.motore.bean.profili;


import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class Profilo implements Comparable<Profilo>  {

	protected String nome = "";
	protected String  descrizione = "";
	protected List<Funzione> funzioni = new ArrayList<Funzione>();
		
	public Profilo() {
	}
	
	public Profilo(String nome) {
		this.nome = nome;
	}

	public Profilo(String nome, String descrizione) {
		this.nome = nome;
		this.descrizione = descrizione;
	}

	

	
	public boolean add(Funzione funzione) {
		if (hasFunzione(funzione)) {
			return false;
		} // 
		funzioni.add(funzione);
		Collections.sort(funzioni);
		return true;
		
	} //
	
	public boolean hasFunzione(Funzione funzione) {
		
		int id = funzione.getId();
		for(Funzione f: funzioni) {
			if (f.getId() == id ) {
				return true;
			} //
		} //
		return false;
	} //
	
	public boolean hasFunzione(int id) {
		for(Funzione f: funzioni) {
			if (f.getId() == id ) {
				return true;
			} //
		} //
		return false;
	} //


	
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	

	public String getDescrizione() {
		return descrizione;
	}


	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}
	
	public String toString() {
		return
			" nome: " + nome +
			" descrizione: " + descrizione +
			" capabilites:" +  funzioni 
		;
		
	}


	public List<Funzione> getFunzioni() {
		return funzioni;
	}

	public int compareTo(Profilo profilo) {
		return nome.compareTo(profilo.getNome());
	}

	public void setFunzioni(List<Funzione> funzioni) {
		this.funzioni = funzioni;
	}

	
// END OF FILE	
}
