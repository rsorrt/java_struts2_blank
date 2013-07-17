package application.motore.constant;

public enum Esito {
	
	KO(0),
	OK(1);

private final int value;    

private Esito(int value) { 
	this.value = value;    
} 
	

public static Esito valueOf(int i) {
	 switch (i) {
	case 0:
		return KO;
	
	case 1:
		return OK;
	
		
	default:
		throw new IllegalArgumentException("Valore non valido");
} 
}//


public int getValue() {
	return value;
}//


}
