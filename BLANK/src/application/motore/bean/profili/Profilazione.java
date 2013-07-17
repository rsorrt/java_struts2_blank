package application.motore.bean.profili;


import java.util.List;

import application.motore.bean.Istituti;
import application.motore.bean.User;


public interface Profilazione {
	
	
	public Istituti getIstitutiAmmessi(
			User user,
			List<String> nomiProfili,
			Istituti istitutiPredefiniti
	)  throws Exception;
	
	
	public String getNomeProfilo(
			User user,
			int abi,
			List<String> nomiProfili
	) throws Exception;

	
}
