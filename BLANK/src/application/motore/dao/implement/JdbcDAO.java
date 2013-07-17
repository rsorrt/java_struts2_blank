package application.motore.dao.implement;

import java.util.List;

import javax.sql.DataSource;


import application.motore.bean.BaseBean;
import application.motore.bean.profili.Funzione;
import application.motore.config.Config;
import application.motore.dao.DAO;
import application.motore.dao.implement.log.Log;
import application.motore.dao.implement.log.TableLog;
import application.motore.dao.implement.profili.TableProfili;
import rsorrt.commons.dao.jdbc.Jdbc;
import rsorrt.commons.util.UtilString;

public class JdbcDAO extends BaseBean implements DAO {

    private Config config = null;
    
    private Jdbc jdbc;
    
    private TableProfili tableProfili;
    private TableLog tableLog;    
        
    
    public JdbcDAO(
            Config config,
            DataSource dataSourceIstituto,
            DataSource dataSource
        ) {
        
        this.config = config;

        jdbc = new Jdbc(dataSource);
        
    }
  
    
    
    
    //----------------------------------------------------------------------
    // PROFILI
    //----------------------------------------------------------------------
    public int delProfiloFunzioni(int abi, String nomeProfilo) throws Exception {
        try    {
            jdbc.open();
            return tableProfili.delProfiloFunzioni(abi, nomeProfilo);
        } finally {
            jdbc.close();
        }
    }

    public int addProfiloFunzioni(int abi, String nomeProfilo,    List<Funzione> funzioni) throws Exception {
        try    {
            jdbc.open();
            return tableProfili.addProfiloFunzioni(abi, nomeProfilo, funzioni);
        } finally {
            jdbc.close();
        }
    }

    public int updateProfiloFunzioni(int abi, String nomeProfilo, List<Funzione> funzioni) throws Exception {
        try    {
            jdbc.open();
            return tableProfili.updateProfiloFunzioni(abi, nomeProfilo, funzioni);
        } finally {
            jdbc.close();
        }
    }

    public int updateProfiloFunzioni(List<Integer> abiList, String nomeProfilo,
            List<Funzione> funzioni) throws Exception {
        try    {
            jdbc.open();
            return tableProfili.updateProfiloFunzioni(abiList, nomeProfilo, funzioni);
        } finally {
            jdbc.close();
        }
    }

    public List<Funzione> getProfiloFunzioni(int abi, String nomeProfilo)
            throws Exception {
        try    {
            jdbc.open();
            return tableProfili.getProfiloFunzioni(abi, nomeProfilo);
        } finally {
            jdbc.close();
        }
    }
    //----------------------------------------------------------------------    

    
    //----------------------------------------------------------------------
    // LOG
    //----------------------------------------------------------------------
    public void addLog(Log log) throws Exception {
        try {
            jdbc.open();
            tableLog.add(log);
        } finally {
            jdbc.close();
        }
        
    }

	
	
    
}
