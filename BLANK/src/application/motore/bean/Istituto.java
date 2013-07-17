package application.motore.bean;

public class Istituto  {

	private String nome;
	private String descrizione;
	private int abi;
	private String letteraIstituto;
	
	
	public Istituto(String nome, String descrizione, int abi, String letteraIstituto ) {
		this.nome = nome;
		this.descrizione = descrizione;
		this.abi = abi;
		this.letteraIstituto = letteraIstituto;
	}
	
	
	public String toString() {
		return 
		"nome: " + nome +
		" descrizione: " + descrizione + 
		" abi: " + abi + 
		" letteraIstituto: " + letteraIstituto 
	;
	
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
	
	public int getAbi() {
		return abi;
	}
	
	
	public void setAbi(int abi) {
		this.abi = abi;
	}
	
	public String getLetteraIstituto() {
		return letteraIstituto;
	}
	public void setLetteraIstituto(String letteraIstituto) {
		this.letteraIstituto = letteraIstituto;
	}

	
	
}
