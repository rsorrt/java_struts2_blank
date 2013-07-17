package application.struts2.interceptor.aware;

import application.motore.bean.Istituto;
import application.motore.bean.User;
import application.motore.config.Config;
import application.motore.dao.DAO;
import it.sgsbpvn.common.host.CCIConnectionFactory;


public interface AwareDati {

	public void setConfig(Config config);
	
	public void setUser(User user);
	public User getUser();
	
	public void setIstituto(Istituto istituto);
	public Istituto getIstituto();
	
	public void setCicsConnFactory( CCIConnectionFactory cciConnectionFactory);
	public void setDAO(DAO dao);
	
	
	//public void setAbi(int abi);
	//public int  getabi();
	
	    
	    
} // end of class
