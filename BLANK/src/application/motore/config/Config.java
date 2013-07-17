package application.motore.config;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;



import org.apache.commons.lang.StringUtils;

import application.motore.bean.Istituti;
import application.motore.bean.Log;
import application.motore.bean.profili.Profilo;


public class Config  {

    String version = "";
    String author = ""; 

    boolean isTest = true;
    String msgTest = "";
    
    // SIMULAZIONE HOST
    boolean isSimulazioneHost = false;

    String titolo = ""; 
        
    String adminPassword = "";
    
    //JNDI
    String jndiNamePathFileConfig = "";
    String jndiNamePathFileLog4j = "";
    String jndiNamePathFileXSSO = "";
    
    String jndiNameUrlXSSO = "";
    
    String jndiNamePathDirDati = "";

    String jndiNameDataSourcePrefix = "";
    String jndiNameDataSource = "";

    String jndiNameCicsPrefix = "";
    
    String jndiNameMailSession = "";
    
// XSSO 
    
    boolean xssoIsCheckTimeStamp = true;
    boolean xssoIsCheckTokenId   = true;
    
    String xssoCrossSourceApplId = "";
    String xssoCrossReturnApplId = "";
  
     // identificano la ricercaAnagrafe
    String xssoCrossAnagrafeRicercaDestApplId = "";
    String xssoCrossAnagrafeRicercaDestFunction = "";
    String xssoCrossAnagrafeRicercaDestMenu = "";
    String xssoCrossAnagrafeRicercaReturnFunction = "";
    
            
// HOST
    List<String> hostIstitutiNomi = new ArrayList<String>();
    Istituti hostIstituti = new Istituti();
        
    
// CTG
    
    String ctgProgDriverNome = "";
        
    String ctgProgProfiloNome = "";
    String ctgProgProfiloTransazione = "";
        
    
    //PROFILI
     /**
     * Ritorna il profilo ADMIN
     * 
     * @return profilo ADMIN
     */
    Profilo profiloAdmin = new Profilo();    
    
    String profiloAdminNome = "";
     
    
    /**
     * Lista nomi profili UTENTE.
     * Attenzione: Il nome del profilo ADMIN non ne fa parte
     *
     * @return Lista nomi profilo
     */
    List<String> profiliNomiSenzaAdmin = new ArrayList<String>();
     
    /**
     * Lista  Profili ESCLUSO quello ADMIN
     *
     * @return Lista Profili
     */
    //formato NOME : DESCRIZIONE
    List<Profilo> profiliSenzaAdmin = new ArrayList<Profilo>();
    
    
     /**
     * Lista nomi profili COMPRESO quello ADMIN
     *
     * @return Lista nomi profili
     */
    List<String> profiliNomi = new ArrayList<String>(); 
    
     /**
     * Lista Profili COMPRESO quello ADMIN
     *
     * @return Lista Profili
     */
        //formato NOME : DESCRIZIONE
    List<Profilo> profili = new ArrayList<Profilo>();
    
    
    List<Log> logs = new ArrayList<Log>();
        
    
    
    
    // MAIL
    boolean mailIsSend = false;
    List<String> mailTo;
    String mailSubject = "";
    
    //PROXY
    String proxyHost = "";
    int proxyPort;

    // MSG INFO
    // associa ad una sottostringa di un messaggio una descrizione
    // in questo modo posso mettere a video suggerimento per l'utente
    Map<String, String>  msgInfo;
    
    //-----------------------------------------------
    // VERIFICA IL FILE CONFIG
    //-----------------------------------------------
        public String getErrors() {
            return ConfigVerifier.getErrors(this);
        } //
            
        public boolean getIsErrors() {
            return ConfigVerifier.getIsErrors(this);
        } //
    //-----------------------------------------------    
    
    
    
    public String toString() {
        
    
        String ret = 
        "\n--------------------------" 
        + "\n CONFIG "
        + "\n--------------------------" +
        
            "\nversion: " + getVersion() +
            "\nauthor: " + getAuthor() +
        
            "\nisTest " + isTest() +
        
            "\nisSimulazioneHost " + isSimulazioneHost() + 
        
            "\nmsgTest " + getMsgTest() +
            "\ntitolo " + getTitolo() +
            "\nadminPassword " + StringUtils.substring(getAdminPassword(), 0,1) + "..." +
                 
            "\n\nJNDI NAMES: " +
            "\n--------------------------" +
            "\npathFileConfig: " + getJndiNamePathFileConfig() +
            "\npathFileLog4j: "  + getJndiNamePathFileLog4j() +
            "\npathFileXSSO: "   + getJndiNamePathFileXSSO() +
        
            "\nurlXSSO: "     + getJndiNameUrlXSSO() +
                    
            "\npathDirDati: "    + getJndiNamePathDirDati() +
            "\ndataSourcePRefix: "  + getJndiNameDataSourcePrefix() +
            "\ncicsPrefix:"      + getJndiNameCicsPrefix() +
            
            "\nmailSession:"      + getJndiNameMailSession() + 

             "\n\nXSSO: " +
	             "\nxssoIsCheckTimeStamp: " + xssoIsCheckTimeStamp + 
	             "\nxssoIsCheckTokenId: "   + xssoIsCheckTokenId +
	            
	             "\nxssoCrossSourceApplId: " + xssoCrossSourceApplId +
	             "\nxssoCrossReturnApplId: " + xssoCrossReturnApplId +
	             
	             "\nxssoCrossAnagrafeRicercaDestApplId: "     + xssoCrossAnagrafeRicercaDestApplId +
	             "\nxssoCrossAnagrafeRicercaDestFunction: "   + xssoCrossAnagrafeRicercaDestFunction +
	             "\nxssoCrossAnagrafeRicercaDestMenu: "       + xssoCrossAnagrafeRicercaDestMenu +
	             "\nxssoCrossAnagrafeRicercaReturnFunction: " + xssoCrossAnagrafeRicercaReturnFunction +
	             
	            
             "\n\nCTG: " +
             "\n--------------------------" + 
             	"\nctgProgDriverNome: " + ctgProgDriverNome +
             	"\n" +
                "\nctgProgProfiloNome: " + ctgProgProfiloNome +
                "\nctgProgProfiloTransazione: " + ctgProgProfiloTransazione +
                "\n" +                
                 
                "\n\nIstitutiNomi: " +
                "\n--------------------------" + 
                 toString(getHostIstitutiNomi()) +
                "\n" +
                "\nIstituti: " +
                "\n--------------------------" +
                getHostIstituti() +

                
                "\n\nProfili Nomi: " +
                "\n--------------------------" + 
                 toString(getProfiliNomi()) +
                 "\n" +
                "\nProfili: " +
                "\n--------------------------"  +
                toString(getProfili()) +
        
                
                "\n\nLog: " +
                "\n--------------------------" + 
                 toString(getLogs()) + 
        
        
                "\n\nSMTP: " +
                "\n--------------------------" +
                toStringSmtpMail() +
                "\n--------------------------" +
        
                "\n\nPROXY: " +
                "\n--------------------------" +
                "\nproxyHost: " + getProxyHost() +
                "\nproxyPort: " + getProxyPort() +
                "\n--------------------------" 
            ;
        
        return ret;
    
    } //toString
    
    
    
    private String toStringSmtpMail() {
        
        String ret = 
                "\n\nmail_send: " + getMailIsSend() +
                "\nmail_subject: " + getMailSubject();
        
        ret += "\nMAIL TO: ";

        for(String mailTo: getMailTo()) {
            ret += "\nmail_to: " + mailTo;
        }// 
        
        return ret;
        
    }//
    
    /*
    private String toStringSimulazioni() {
        
        return 
                "\nsimulazione_profilazione: " + getSimulazioneProfilazione() +  
                
                "\nsimulazione_ricercaAnagraficaImpresa: " + getSimulazioneRicercaAnagraficaImpresa() +
                "\nsimulazione_ricercaAnagraficaPersona: " + getSimulazioneRicercaAnagraficaPersona() +
                
                "\nsimulazione_visuraRicerca: "   + getSimulazioneVisuraRicerca() +
                "\nsimulazione_visuraControlli: "   + getSimulazioneVisuraControlli()
             
                
        ;

    } //
    */
    
    
    
    
    
    
    public String getVersion() {
        return version;
    }
    public String getAuthor() {
        return author;
    }
    
    public boolean isTest() {
        return isTest;
    }
    public boolean getIsTest() {
        return isTest;
    }
    public String getMsgTest() {
        return msgTest;
    }
    
    
    public boolean isSimulazioneHost() {
        return isSimulazioneHost;
    }
    public boolean getIsSimulazioneHost() {
        return isSimulazioneHost;
    }
    
    
    public String getTitolo() {
        return titolo;
    }
    public String getAdminPassword() {
        return adminPassword;
    }

    
    public String getJndiNamePathFileConfig() {
        return jndiNamePathFileConfig;
    }
    public String getJndiNamePathFileLog4j() {
        return jndiNamePathFileLog4j;
    }
    public String getJndiNamePathFileXSSO() {
        return jndiNamePathFileXSSO;
    }
    public String getJndiNameUrlXSSO() {
        return jndiNameUrlXSSO;
    }
    public String getJndiNamePathDirDati() {
        return jndiNamePathDirDati;
    }
    public String getJndiNameDataSourcePrefix() {
        return jndiNameDataSourcePrefix;
    }
    public String getJndiNameDataSource() {
        return jndiNameDataSource;
    }
    public String getJndiNameCicsPrefix() {
        return jndiNameCicsPrefix;
    }
    public String getJndiNameMailSession() {
        return jndiNameMailSession;
    }

    public boolean getXssoIsCheckTimeStamp() {
        return xssoIsCheckTimeStamp;
    }
    public boolean getXssoIsCheckTokenId() {
        return xssoIsCheckTokenId;
    }
    public String getXssoCrossSourceApplId() {
        return xssoCrossSourceApplId;
    }
    public String getXssoCrossReturnApplId() {
        return xssoCrossReturnApplId;
    }
    
  
    public String getXssoCrossAnagrafeRicercaDestApplId() {
        return xssoCrossAnagrafeRicercaDestApplId;
    }
    public String getXssoCrossAnagrafeRicercaDestFunction() {
        return xssoCrossAnagrafeRicercaDestFunction;
    }
    public String getXssoCrossAnagrafeRicercaDestMenu() {
        return xssoCrossAnagrafeRicercaDestMenu;
    }
    public String getXssoCrossAnagrafeRicercaReturnFunction() {
        return xssoCrossAnagrafeRicercaReturnFunction;
    }


    
    
    
    public List<String> getHostIstitutiNomi() {
        return hostIstitutiNomi;
    }
    public Istituti getHostIstituti() {
        return hostIstituti;
    }
    public String getCtgProgDriverNome() {
        return ctgProgDriverNome;
    }

    public String getCtgProgProfiloNome() {
        return ctgProgProfiloNome;
    }
    public String getCtgProgProfiloTransazione() {
        return ctgProgProfiloTransazione;
    }
    
  
    /*
    public String getCervedUsername() {
        return cervedUsername;
    }
    public String getCervedPassword() {
        return cervedPassword;
    }
    */
    
    public Profilo getProfiloAdmin() {
        return profiloAdmin;
    }
    public String getProfiloAdminNome() {
        return profiloAdminNome;
    }
    public List<String> getProfiliNomiSenzaAdmin() {
        return profiliNomiSenzaAdmin;
    }
    public List<Profilo> getProfiliSenzaAdmin() {
        return profiliSenzaAdmin;
    }
    public List<String> getProfiliNomi() {
        return profiliNomi;
    }
    public List<Profilo> getProfili() {
        return profili;
    }
    public List<Log> getLogs() {
        return logs;
    }
    
    

    
    
    public boolean getMailIsSend() {
        return mailIsSend;
    }
    public List<String> getMailTo() {
        return mailTo;
    }
    public String getMailSubject() {
        return mailSubject;
    }


    public String getProxyHost() {
        return proxyHost;
    }
    public int getProxyPort() {
        return proxyPort;
    }
    
    
    private String toString(List list) {
        String ret = "";
        for (Object obj: list) {
            ret += "\n" + obj;
        } //
        return ret;
    } //

    


    
    
// END OF CLASS
}
