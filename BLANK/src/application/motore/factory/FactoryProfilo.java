package application.motore.factory;

import java.util.List;



import org.apache.commons.lang.StringUtils;

import application.motore.bean.BaseBean;
import application.motore.bean.User;
import application.motore.bean.profili.Funzione;
import application.motore.bean.profili.Profilo;
import application.motore.config.Config;
import application.motore.dao.DAO;


public abstract class FactoryProfilo extends BaseBean {
	
	public static Profilo get(
			Config config,
			User user, int abi, String nomeProfilo,
			DAO dao 
	)  {
		
		if ( StringUtils.isBlank(nomeProfilo)) {
			logger.warn("nomeProfilo is null");
			return null;
		} //
		
		String profiloAdminNome = config.getProfiloAdminNome();
		
		// controllo se il profilo è AMMINISTRATIVO
		if ( nomeProfilo.equalsIgnoreCase(profiloAdminNome) ) {
			Profilo profilo = config.getProfiloAdmin();
			user.setIsAdmin(true);
			return profilo;
		}//if
		
		
		List<Profilo> profili = config.getProfiliSenzaAdmin();
		
		if ( profili == null) {
			logger.error("Lista profili null");
			return null;
		}//
		
		if ( profili.size() == 0) {
			logger.error("Lista profili vuota");
			return null;
		}//
		
		
		Profilo profilo = null;
		for ( Profilo p: profili ) {
			try {
				if (p.getNome().equalsIgnoreCase(nomeProfilo)) {
					profilo = new Profilo();
					profilo.setNome(nomeProfilo);
					profilo.setDescrizione(p.getDescrizione());
					break;
				}//if
				
			} catch (Exception e) {
				logger.error("Cerco profilo: " + nomeProfilo + " Errore confrontandolo con profilo: " + p);
			}
		}//for
	
		if ( profilo == null) {
			logger.error("Profilo: " + nomeProfilo + " non trovato tra i profili disponibili");
			return null;
		}//
		
		List<Funzione> funzioni = getFunzioni(abi, nomeProfilo, dao);
		profilo.setFunzioni(funzioni);		
		user.setIsAdmin(false);
		return profilo;
		
		
	}
	
	/*
	// controllo se il profilo è è AMMINISTRATIVO 
	public static Profilo get(
			User user, int abi, String nomeProfilo, Profilo profiloAdmin,
			DAO dao 
	)  {
		
		if (nomeProfilo == null) {
				logger.warn("nomeProfilo is null");
				return null;
		} //
		
		Profilo profilo = new Profilo(nomeProfilo);
		user.setIsAdmin(false);
		
		// controllo se il profilo è AMMINISTRATIVO
		if ( nomeProfilo.equalsIgnoreCase(profiloAdmin.getNome()) ) {
			profilo = profiloAdmin;
			user.setIsAdmin(true);
			return profilo;
		}//if

		List<Funzione> funzioni = getFunzioni(abi, nomeProfilo, dao);
		profilo.setFunzioni(funzioni);		
		return profilo;
		
	} // 
	 */
	
	
	private static  List<Funzione> getFunzioni (int abi, String nomeProfilo,
			DAO dao) {

		//REPERISCO LE FUNZIONI
		List<Funzione> funzioni = null;
		try {
			
			funzioni = dao.getProfiloFunzioni(abi, nomeProfilo);
				
			} catch (Exception e) {
				logger.warn(
					 "Errore dao.getProfiloFunzioni with " +
					 " abi:" + abi + " nomeProfilo:" + nomeProfilo +
					 " " + e
				);
				return null;
				
			} //try

		
		return funzioni;
	} //
	
	
	
// end of class	
}
