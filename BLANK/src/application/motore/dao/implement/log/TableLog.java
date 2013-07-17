package application.motore.dao.implement.log;

import java.sql.PreparedStatement;

import rsorrt.commons.dao.jdbc.Jdbc;
import rsorrt.commons.dao.jdbc.JdbcTable;



public class TableLog extends JdbcTable {
    
    public static final String NAME = "LOG";

    public static final int FIELD_LENGTH_MSG    = 255;
    public static final int FIELD_LENGTH_DESCRIZIONE = 255;
    public static final int FIELD_LENGTH_ECCEZIONE   = 500;
    public static final int FIELD_LENGTH_LOGGERID    = 100;

    public TableLog(Jdbc jdbc) {
        super(NAME, jdbc);
    }
    
    
    public void add(Log log) throws Exception {
        PreparedStatement ps = null;
        try {
            ps = SQLLog.insert(jdbc.getConn(), log);
            ps.execute();
        } catch (Exception e) {
            logger.error(loggerId, e);
            throw e;
        } finally {
            jdbc.close(ps);
        }
    }
    

} //END OF CLASS
