package application.motore.dao;


import java.util.List;

import application.motore.bean.profili.Funzione;
import application.motore.dao.implement.log.Log;

public interface DAO {


    //LOG
    public void addLog(Log log) throws Exception;
    
    
    public List<Funzione> getProfiloFunzioni(int abi, String nomeProfilo) throws Exception;
    
}
