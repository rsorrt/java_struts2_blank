package application.motore.bean.profili;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import application.motore.bean.BaseBean;


public class Funzioni extends BaseBean implements Iterable<Funzione> {

	
	
	// FUNZIONI
	
	// VISUALIZZAZIONE STORICA
	public static final Funzione  VISUALIZZAZIONE_VISURA           = new Funzione(1, "VISUALIZZAZIONE VISURA", "Visualizzazione Visura - link menu");
	public static final Funzione  VISUALIZZAZIONE_VISURA_STORICA   = new Funzione(2, "VISUALIZZAZIONE VISURA STORICA", "Checkbox tipo visura storica"); 
	
	// RICERCA ANAGRAFICA
	public static final Funzione  RICERCA_ANAGRAFICA = new Funzione(3, "RICERCA ANAGRAFICA", "Ricerca Anagrafica - Link menu");
	
	// MONITORAGGIO
	public static final Funzione  MONITORAGGIO       = new Funzione(4, "MONITORAGGIO", "Monitoraggio - Link menu");
	public static final Funzione  MONITORAGGIO_RIACQUISTA_VISURA = new Funzione(5, "MONITORAGGIO RIACQUISTA VISURA", "Riacquista Visura");
	
	
	
	
	private static Map<Integer, Funzione> funzioni = new HashMap<Integer, Funzione>();
	
	static {
		
		//-------------------------------------------
		add(VISUALIZZAZIONE_VISURA);
		add(VISUALIZZAZIONE_VISURA_STORICA);
		
		add(RICERCA_ANAGRAFICA);
		
		add(MONITORAGGIO);
		add(MONITORAGGIO_RIACQUISTA_VISURA);
		
		
	} //
	
	/*
	private static void add(int id, String nome, String descrizione) {
		funzioni.put(id, new Funzione(id, nome, descrizione));
	} //
	*/
	
	private static void add(Funzione funzione)  {
		if ( funzioni.containsKey(funzione.getId())) {
			logger.warn("Funzione: " + funzione + " NON INSERITA. La chiave: " + funzione.getId() + " della Funzione da inserire esiste");
		} //
		
		funzioni.put(funzione.getId(), funzione);
		
	} //


	public boolean exists(int id) {
		return funzioni.containsKey(id);
	} //
	
	
	
	
	public List<Funzione> get() {
		ArrayList<Funzione> list = new ArrayList<Funzione>(funzioni.values());
		Collections.sort(list);
		return list;
	} // 
	
	public Funzione get(int id) {
		return funzioni.get(id);
	} // 
	
	public Funzione get(String id) {
		try {
			return funzioni.get(Integer.parseInt(id));
		} catch (Exception e) {
			return null;
		}
	} // 
	
	
	
	public String toString() {
		String ret = "";
		int count = 0;
		for (int id: funzioni.keySet()) {
			count ++;
			if ( count > 1 ) {
				ret += "\n";
			} //
			ret += "id: " + id + " name: " + funzioni.get(id);
		} //
		return ret;
		
	}
	

	public String toXml() {
		String xml ="<capabilities>" ;
		
		for (Integer key: funzioni.keySet()) {
			xml += "\n<value>" + key + "</value>";
		} //
			
		xml += "\n</capabilites>";
		
		return xml;
	} //


	public int size() {
		return funzioni.size();
	}
	

	public Iterator<Funzione> iterator() {
		return funzioni.values().iterator();
	}

	
}
