package application.motore.bean;


import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;


public class Istituti implements Iterable<Istituto> {

	private Map<Integer, Istituto> istituti = new TreeMap<Integer, Istituto>();
	
	
	public Iterator<Istituto> iterator() {
		return istituti.values().iterator();
	}

	public void order() {
		
	} //
	
	public int size() {
		return istituti.size();
	} //
	
	
	
	public void add(Istituto istituto) {
		istituti.put(istituto.getAbi(), istituto);
	} //
			
	public Istituto get(String abi) {
		return istituti.get(Integer.parseInt(abi));
	} //

	public Istituto get(int abi) {
		return istituti.get(abi);
	} //
	
	public List<Integer> getAbiList() {
		List<Integer> abiList = new ArrayList<Integer>();
		for (Istituto istituto: istituti.values()) {
			abiList.add(istituto.getAbi());
		} //for
		Collections.sort(abiList);
		return abiList;
	} //
	
	
	
	
	public String toString() {
		String ret = "";
		for ( Istituto istituto: istituti.values()) {
			ret += "\n" + istituto;
		} //for
		return ret;
	} //
	
}



