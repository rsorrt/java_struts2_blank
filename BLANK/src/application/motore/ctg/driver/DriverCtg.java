package application.motore.ctg.driver;

import it.sgsbpvn.common.host.CCIConnectionFactory;
import rsorrt.commons.bean.User;

public class DriverCtg extends rsorrt.commons.ctg.DriverCtg{
	
	 
	
	public DriverCtg(
			String transazione,
			String progDriver,
			String progChiamato,
			User user,
			CCIConnectionFactory connectionFactory
			) {
		
		super(
			transazione,
			progDriver,
			progChiamato,
			user,
			connectionFactory
		);
		
		
	}
	
	

}
