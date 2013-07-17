package application.motore.dao.implement.log;

import java.sql.Connection;
import java.sql.PreparedStatement;

import rsorrt.commons.dao.jdbc.query.QueryBaseORACLE;
import rsorrt.commons.util.UtilDateTime;

public class SQLLog extends QueryBaseORACLE {
    
    private static final String sql_report_mesi_esiti =
        ",COUNT(ESITO) AS ESITI "
        + ",Sum(case when ESITO = 0 then 1 else 0 end) AS ESITI_KO "
        +  ",Sum(ESITO) AS ESITI_OK";
    
    private static final String sql_report_mesi_eccezioni =
        ",Sum(case when (ECCEZIONE='' OR ECCEZIONE is NULL) then 1 else 0 end) AS EMPTY "
        + ",Sum(case when Instr(ECCEZIONE, 'ECI_ERR_NO_CICS')>0 then 1 else 0 end)          AS CICS_NO "
        + ",Sum(case when Instr(ECCEZIONE, 'ECI_ERR_RESPONSE_TIMEOUT')>0 then 1 else 0 end) AS CICS_TIMEOUT "
        + ",Sum(case when Instr(ECCEZIONE, 'ECI_ERR_SECURITY_ERROR')>0 then 1 else 0 end)   AS CICS_SECURITY "
        + ",Sum(case when Instr(ECCEZIONE, 'Abend')>0 then 1 else 0 end) AS CICS_ABEND "
        + ",Sum(case when Instr(ECCEZIONE, 'CartesioSpcrvd')>0 then 1 else 0 end) AS CERVED_CARTESIOSPCRVD "
        + ",Sum(case when Instr(ECCEZIONE, 'MondoElios')>0 then 1 else 0 end)     AS CERVED_MONDOELIOS ";
    
    public static final String sql_insert = 
        "INSERT INTO LOG "
        + "(DATA, DATA_ANNO, DATA_MESE, DATA_GIORNO, DATA_ORA, HOSTNAME, METODO, MSG, DESCRIZIONE, ECCEZIONE, LOGGERID, ESITO, ELAPSED_MS) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?)";

    public static final String sql_report_tot =
        " SELECT "
        + " Min(DATA) as DATA "
        + sql_report_mesi_esiti
        + sql_report_mesi_eccezioni
        + " FROM LOG"; 
    
    public static final String sql_report_mesi =
        " SELECT" 
        + " CONCAT(DATA_ANNO, DATA_MESE) as DATA "
        + sql_report_mesi_esiti
        + sql_report_mesi_eccezioni
        + " FROM LOG "
        + " GROUP BY DATA_ANNO, DATA_MESE" 
        + " ORDER BY" 
        + " DATA_ANNO DESC, DATA_MESE DESC";

    
    public static final String sql_report_giorni =
         "SELECT"
       + " DATA"
       +  sql_report_mesi_esiti
       +  sql_report_mesi_eccezioni
       + " FROM LOG "
       + " WHERE DATA_ANNO=?anno? AND DATA_MESE=?mese?"
       + " GROUP BY DATA  ORDER BY DATA DESC";
    
    
    public static final String sql_report_giorno =
       " SELECT  *"
       + " FROM LOG "
       + " WHERE DATA=?data?" 
       + " ORDER BY DATA_ORA DESC";
    
    public static final String sql_report_giorno_where =
       " SELECT *"
       + " FROM LOG"
       + " WHERE DATA=?data? AND ?where?"
       + " ORDER BY DATA_ORA DESC";
    
    public static PreparedStatement insert(Connection conn, Log log) throws Exception {
        
        PreparedStatement ps = conn.prepareStatement(sql_insert);
        
        /*
        int anno = 2012; //UtilNumber.getRandom(2009, 2012);
        int mese = UtilNumber.getRandom(1,12);
        int giorno = UtilNumber.getRandom(1,31);
        int ora = UtilNumber.getRandom(0, 24);
        int minuti = UtilNumber.getRandom(1, 59);
        int secondi = UtilNumber.getRandom(1, 59);
        ps.setInt(1, Integer.parseInt(anno + UtilDateTime.formatMM(mese) + UtilDateTime.formatDD(giorno) ));
        ps.setInt(2, anno);
        ps.setInt(3, mese);
        ps.setInt(4, giorno);
        ps.setInt(5, Integer.parseInt(ora + "" + minuti + "" + secondi));
        */
        
        ps.setInt(1, UtilDateTime.getYYYYMMDD());
        ps.setInt(2, UtilDateTime.getYYYY());
        ps.setInt(3, UtilDateTime.getMM());
        ps.setInt(4, UtilDateTime.getDD());
        ps.setInt(5, UtilDateTime.getHHMMSS());
           
        
        ps.setString(6, log.getHostName());
        
        ps.setString(7, log.getMetodo());
        
        ps.setString(8, normalizeValue(log.getMsg(), TableLog.FIELD_LENGTH_MSG));
        ps.setString(9, normalizeValue(log.getDescrizione(), TableLog.FIELD_LENGTH_DESCRIZIONE));
        
        ps.setString(10, normalizeValue(log.getEccezione(), TableLog.FIELD_LENGTH_ECCEZIONE));
        
        ps.setString(11, normalizeValue(log.getLoggerId(), TableLog.FIELD_LENGTH_LOGGERID));
        
        ps.setInt(12, log.getEsito().getValue()); 
        ps.setLong(13, log.getElapsedMs());
        
        return ps;
        
    } //insert
    
    
    public static String reportTot() throws Exception {
        return sql_report_tot;
    }
    
    public static String reportMesi() throws Exception {
        return sql_report_mesi;
    }
    
    public static String reportGiorni(int anno, int mese) throws Exception {
        return sql_report_giorni.replace("?anno?", String.valueOf(anno)).replace("?mese?", mese+"");
    }
    
    public static String reportGiorno(int data) throws Exception {
        return sql_report_giorno.replace("?data?", String.valueOf(data));
    }
    
    public static String reportGiorno(int data, String where) throws Exception {
        return sql_report_giorno_where.replace("?data?", String.valueOf(data)).replace("?where?", where);
    }
    

} //END OF CLASS
