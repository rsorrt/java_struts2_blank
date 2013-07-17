package application.resource.login;

import java.util.List;
import java.util.Map;

import javax.sql.DataSource;



import org.apache.commons.lang.StringUtils;

import application.motore.bean.Istituti;
import application.motore.bean.Istituto;
import application.motore.bean.User;
import application.motore.bean.profili.Profilazione;
import application.motore.bean.profili.Profilo;
import application.motore.config.Config;
import application.motore.dao.DAO;
import application.motore.factory.FactoryDAO;
import application.motore.factory.FactoryProfilo;
import application.resource.AppResources;

public class Login {

    private String msg = "";

    private Map<String, Object> session;
    private Profilazione profilazione;

    public Login(
            Map<String, Object> session,
            Profilazione profilazione
            ) {
        this.session = session;
        this.profilazione = profilazione;
    }

    public void loggaUtente(
            User user,
            String abi) throws Exception {
    
         int abiNumerico = 0 ;
         try {
             abiNumerico = Integer.parseInt(abi);
         } catch (Exception e) {
             msg = "Abi non numerico: '" + abi + "'";
             throw new Exception(msg);
         }
        
         loggaUtente(user, abiNumerico);
         
    }
        
    
    /* configura le risorse per il login utente
     * 
     */
    public void loggaUtente(
        User user,
        int abi) throws Exception {
        
        AppResources appResources = AppResources.get(session); 
        Config config = appResources.getConfig();
        Istituti istituti = config.getHostIstituti();
            
        Istituto istituto = istituti.get(abi);
        if (istituto == null ) {
            msg = "Istituto null per abi: '" + abi + "'";
            throw new Exception(msg);
        } //

        DataSource dataSourceIstituto = AppResources.getDataSourceIstituto(abi);
        DataSource dataSource         = AppResources.getDataSource();
        DAO dao                       = FactoryDAO.get(AppResources.getConfig(), dataSourceIstituto, dataSource);
        
        // se l'utente non è ADMIN allora reperisco il profilo 
        // relativo all'abi scelto
        //-----------------------------------------------------------------------------------
        
        //if ( ! user.isAdmin()) {
            
            // REPERIMENTO PROFILO UTENTE
            //--------------------------------------------------            
            //Profilo profiloAdmin  = config.getProfiloAdmin();
            List<String> nomiProfili = config.getProfiliNomi();
        
            String nomeProfilo = profilazione.getNomeProfilo(user, abi, nomiProfili);
            
            if ( StringUtils.isEmpty(nomeProfilo) ) {
                msg = "Profilo non disponibile su abi: " + abi;
                throw new Exception(msg);
            }//
            
            Profilo profilo = FactoryProfilo.get(config, user, abi, nomeProfilo, dao);
            
            if ( profilo == null ) {
                //logger.error(loggerId,"Nome profilo: " + nomeProfilo + " abi: " + abi + " Impossibile reperire il Profilo");
                msg = "Profilo non reperibile su abi: " + abi;
                throw new Exception(msg);
            } //
            //---------------------------
            user.setProfilo(profilo);
            
        //} // if
        
        //-----------------------------------------------------------------------------------
        
        // recupero gli istituti abilitati per l'utente
        // e li salvo in SESSION
        //-------------------------------------------------------------
        //Istituti istitutiAmmessi = FactoryIstitutiAmmessi.get(user, istitutiPredefiniti);
        //Istituti istitutiAmmessi = profilazione.getIstitutiAmmessi(user, nomiProfili, istitutiPredefiniti);
        Istituti istitutiAmmessi = config.getHostIstituti();
            
        //ResourceIstituto.save(session, istituto);
        //appResource save
        appResources.setIstituto(istituto);
        appResources.setIstitutiAmmessi(istitutiAmmessi);
        
        appResources.setDao(dao);
        appResources.setUser(user);
        appResources.save(session);
        
    } //loggaUtente

    
    public String getMsg() {
        return msg;
    }
    
    
} //END OF CLASS
