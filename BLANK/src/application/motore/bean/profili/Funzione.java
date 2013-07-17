package application.motore.bean.profili;

import java.io.Serializable;

public class Funzione implements Comparable<Funzione>, Serializable {

	private static final long serialVersionUID = 1L;
	
	private int id;
	private String nome;
	private String descrizione; 
	
	public Funzione(int id, String nome, String descrizione) {
		this.id = id;
		this.nome = nome;
		this.descrizione = descrizione;
	}

	
	public String toString() {
		return "id: " + id + " nome: " + nome + " descrizione: " + descrizione;
	}
	
	public int compareTo(Funzione funzione) {
		return id - funzione.getId(); 
	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


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
	
	
	
}
