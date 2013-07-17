package application.motore.dao.implement.profili;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


import org.apache.commons.lang.StringUtils;

import application.motore.bean.profili.Funzione;
import application.motore.bean.profili.Funzioni;

import rsorrt.commons.util.UtilString;

public class ProfiliUtil {

	public static String getStringFromFunzioni(List<Funzione> funzioni) {
		String funzioniString = "";
		Collections.sort(funzioni);
		for(Funzione funzione: funzioni) {
			if (funzioniString.length() == 0) {
				funzioniString = String.valueOf(funzione.getId());
			} else {
				funzioniString += "," + funzione.getId();
			} //if
		} //for
		return funzioniString;
	} // 
	
	public static List<Funzione> getFunzioniFromString(String stringFunzioni) {
		
		stringFunzioni = UtilString.trimToEmpty(stringFunzioni);
		
		Funzioni funzioniPredefinite = new Funzioni();
		List<Funzione> funzioni = new ArrayList<Funzione>();
		
		String[] arrFunzioni= stringFunzioni.split(",");
	
		for(String id : arrFunzioni) {
			if (StringUtils.isNotBlank(id)) {
				funzioni.add(funzioniPredefinite.get(id));
			} //
		} //for
		
		Collections.sort(funzioni);
		
		return funzioni;
	} // 
	

	
	
}
