package application.motore.factory;

import javax.sql.DataSource;

import application.motore.config.Config;
import application.motore.dao.DAO;
import application.motore.dao.implement.JdbcDAO;

import rsorrt.commons.factory.FactoryLogger;
import rsorrt.commons.log.MyLogger;

public class FactoryDAO {
	
	private static final MyLogger logger = FactoryLogger.getLogger();
	
	
	public static DAO get(
			Config config,
			DataSource dataSourceIstituto, DataSource dataSource 
			) throws Exception {

			//JdbcDB2DAO     istitutoDAO    = new JdbcDB2DAO(dataSource);
			//JdbcORACLEDAO  daoORACLE      = new JdbcORACLEDAO(dataSource);
		
			return new JdbcDAO(config,
					dataSourceIstituto, dataSource
			);
		
		/*
		if ( dsDB2 == null ) {
			logger.warn("dataSourceDB2 is null");
			error = true;
		} //

		if ( oracleDAO == null ) {
			logger.fatal("oracleDAO is null");
			error = true;
		} //

		if ( error) {
			throw new NullPointerException(
					"db2DAO and/or db2ORACLE null" +
					" oracleDAO=" + oracleDAO +
					" db2DAO=" + db2DAO
			);
		*/
			
	} //get
	
	
// end of file		
} //
	
	