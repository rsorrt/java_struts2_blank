package application.motore.dao.implement.profili;


import java.util.List;


import org.apache.commons.lang.StringUtils;

import application.motore.bean.profili.Funzione;

import rsorrt.commons.dao.jdbc.Jdbc;
import rsorrt.commons.dao.jdbc.JdbcTable;

public class TableProfili extends JdbcTable {

    public static final String NAME = "PROFILI_FUNZIONI";
    
    public TableProfili(Jdbc jdbc) {
        super(NAME, jdbc);
    }
   
    public int delProfiloFunzioni(int abi, String nomeProfilo) throws Exception {
        String query = SQLProfili.delFunzioni(abi, nomeProfilo);
        int esito = jdbc.executeUpdate(query);
        return esito;
    }


    public int addProfiloFunzioni(int abi, String nomeProfilo,
        List<Funzione> funzioni) throws Exception {
        
        String stringFunzioni = ProfiliUtil.getStringFromFunzioni(funzioni);
        String query = SQLProfili.addFunzioni(abi, nomeProfilo, stringFunzioni);
        
        jdbc.autocommitDisable();
        int esito = jdbc.executeUpdate(query);
        
        if (esito == 1) {
            jdbc.commit();
        } else {
            logger.warn(loggerId, "DBASE - ROLLBACK - ProfiloFunzione non aggiunto per abi:" + abi + " nomeProfilo: " + nomeProfilo);
            jdbc.rollback();
        } //
        
        return 0;
    }
    

    public int updateProfiloFunzioni(int abi, String nomeProfilo,
            List<Funzione> funzioni) throws Exception {
        
        
    String stringFunzioni = ProfiliUtil.getStringFromFunzioni(funzioni);
        
    
        int esito = 0;
        String query = SQLProfili.profiloFunzioniExists(abi, nomeProfilo);
        
            
        
        int count = jdbc.getCount(query);
        
        if (stringFunzioni == null || StringUtils.isBlank(stringFunzioni)) {

            if (count > 0) {
                query = SQLProfili.delFunzioni(abi, nomeProfilo);
            } else {
                query = null;
            } //
            
        } else {
            
            if (count == 0) {
                query = SQLProfili.addFunzioni(abi, nomeProfilo, stringFunzioni);
            } else {
                query = SQLProfili.updateFunzioni(abi, nomeProfilo, stringFunzioni);
            } //
            
        } //
        
        
        if (query != null) {
        
            jdbc.autocommitDisable();
            esito = jdbc.executeUpdate(query);
            
            if (esito == 1) {
                jdbc.commit();
            } else {
                logger.warn(loggerId, "Update Funzioni fallito query: " + query);
                jdbc.rollback();
            } //
            
        } //
        
        
    
        
        return esito;
        
        
        
        
    }

    public int updateProfiloFunzioni(List<Integer> abiList, String nomeProfilo,
            List<Funzione> funzioni) throws Exception {
        String stringFunzioni = ProfiliUtil.getStringFromFunzioni(funzioni);
        int esito = 0;
        
        
        //TRANSACTION BEGIN
        jdbc.autocommitDisable();
        
        String query = SQLProfili.delFunzioni(nomeProfilo);
        jdbc.executeUpdate(query);
            
        for (int abi: abiList) {
            query = SQLProfili.addFunzioni(abi, nomeProfilo, stringFunzioni);
            esito += jdbc.executeUpdate(query);
        } //for
        
        //TRANSACTION COMMIT/ROLLBACK
        if (esito == abiList.size()) {
            jdbc.commit();
        } else {
            logger.warn(loggerId, "DBASE - ROLLBACK - Funzioni su tutti gli abi fallito. Esito: " + esito + " invece di: " + abiList.size());
            jdbc.rollback();
        } //
        
        
        return esito;
    }

    public List<Funzione> getProfiloFunzioni(int abi, String nomeProfilo)
            throws Exception {
        String stringFunzioni = "";
        String query = "";
        
        query = SQLProfili.getFunzioni(abi, nomeProfilo); 
        Object row = jdbc.getSingleResult(query);
        
            // se il profilo ha delle funzioni associate le estraggo
            if (row != null) {
                // ci vuole la concatenazion row+"" altrimenti se il contenuto di row è un numero viene Integer come tipo e 
                // il cast String fallisce
                stringFunzioni = (String) (row + "");
            } //
        
        
        List<Funzione> funzioni = ProfiliUtil.getFunzioniFromString(stringFunzioni);
        return funzioni;
    }
    
      
} //END OF CLASS
