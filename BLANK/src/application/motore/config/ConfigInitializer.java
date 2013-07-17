package application.motore.config;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.naming.NameNotFoundException;

import org.apache.commons.configuration.PropertiesConfiguration;
import org.apache.commons.lang.StringUtils;

import application.motore.bean.BaseBean;
import application.motore.bean.Istituti;
import application.motore.bean.Istituto;
import application.motore.bean.Log;
import application.motore.bean.User;
import application.motore.bean.profili.Profilo;

import rsorrt.commons.util.UtilCrypt;

/*
 * Per ottenere un oggetto di tipo Config con le variabili settate i cui valori sono presi da un file
 */
public final class ConfigInitializer extends BaseBean {

    private static final int DEFAULT_dbase_visure_maxSize_mb = 0;
    private static final int DEFAULT_dbase_visure_sogliaAvvisoMaxSize_percentuale = 90; 

    private static final String SEPARATOR = "::";

    private static PropertiesConfiguration prop;

    
    private ConfigInitializer() {
    }
    
    public static Config get(String pathFile) throws Exception {
        
        try {
             
            File fileConfig = new File(pathFile);
            if  (!fileConfig.exists()) {
                throw new FileNotFoundException("Fileconfig non esiste: " + pathFile);
            } //    
            
            prop = new PropertiesConfiguration(pathFile);
            
            
            Config conf = new Config();

            conf.version = prop.getString("version");
            conf.author  = prop.getString("author"); 
            conf.isTest  = getProp_default("isTest", false);

            conf.isSimulazioneHost = getProp_default("isSimulazioneHost", false);
            conf.msgTest = prop.getString("msgTest");
            if (conf.isSimulazioneHost) {
                conf.msgTest += " SIMULAZIONE HOST";
            }
            
            conf.titolo = getProp_default("titolo", "VCWEB - Visure Camerali");
            
            try {
                conf.adminPassword = UtilCrypt.myDecode(prop.getString("admin_password"));    
            } catch (Exception e) {
                logger.warn("adminPassord: " + e);
            }
            
            
            // JNDI NAME
            conf.jndiNamePathFileConfig = getProp("jndi_name_pathFileConfig");
            
            conf.jndiNamePathFileLog4j  = getProp("jndi_name_pathFileLog4j");
            
            conf.jndiNamePathFileXSSO   = getProp("jndi_name_pathFileXSSO");
            conf.jndiNameUrlXSSO        = getProp("jndi_name_urlXSSO");
            
            conf.jndiNamePathDirDati    = getProp("jndi_name_pathDirDati");
            
            conf.jndiNameDataSourcePrefix  = getProp("jndi_name_dataSourcePrefix");
            conf.jndiNameDataSource        = getProp("jndi_name_dataSource");
            
            conf.jndiNameCicsPrefix        = getProp("jndi_name_cicsPrefix");
            
            conf.jndiNameMailSession       = getProp("jndi_name_mailSession");
            
            // XSSO
            conf.xssoIsCheckTimeStamp = getProp_default("xsso_isCheckTimeStamp", true);
            conf.xssoIsCheckTokenId   = getProp_default("xsso_isCheckTokenId", true);
        
            conf.xssoCrossSourceApplId = getProp("xsso_cross_sourceApplId"); 
            conf.xssoCrossReturnApplId = getProp("xsso_cross_returnApplId");
            
            conf.xssoCrossAnagrafeRicercaDestApplId     = getProp("xsso_cross_anagrafe_ricerca_destApplId"); 
            conf.xssoCrossAnagrafeRicercaDestFunction   = getProp("xsso_cross_anagrafe_ricerca_destFunction");
            conf.xssoCrossAnagrafeRicercaDestMenu       = getProp("xsso_cross_anagrafe_ricerca_destMenu");
            conf.xssoCrossAnagrafeRicercaReturnFunction = getProp("xsso_cross_anagrafe_ricerca_returnFunction");
        
            
            conf.hostIstitutiNomi = getPropList("host_istitutoList");
            conf.hostIstituti     = init_hostIstituti(conf.hostIstitutiNomi);    
                
            
            // CICS
            conf.ctgProgDriverNome = getProp("ctg_prog_driver_nome");
            
                
            conf.ctgProgProfiloNome = getProp("ctg_prog_profilo_nome");
            conf.ctgProgProfiloTransazione = getProp("ctg_prog_profilo_transazione");
                
            // PROFILI
            try {
                
            conf.profiloAdmin = init_profiloAdmin();
            conf.profiloAdminNome = conf.profiloAdmin.getNome();
                
            conf.profiliNomiSenzaAdmin = init_profiliNomiSenzaADMIN();
            conf.profiliSenzaAdmin     = init_profiliSenzaADMIN();
                
            conf.profiliNomi = init_profiliNomi(conf.profiloAdminNome, conf.profiliNomiSenzaAdmin); 
            conf.profili = init_profili(conf.profiloAdmin, conf.profiliSenzaAdmin);
    
            } catch (Exception e) {
                logger.error("PROFILI: " + e);
                throw e;
            }
        
            // LOGS
            //conf.logs = init_log();
            
            // SIMULAZIONE
            /*
            conf.simulazioneProfilazione = getProp_default("simulazione_profilazione", false);
            conf.simulazioneRicercaAnagraficaImpresa = getProp_default("simulazione_ricercaAnagraficaImpresa", false);
            conf.simulazioneRicercaAnagraficaPersona = getProp_default("simulazione_ricercaAnagraficaPersona", false);
            conf.simulazioneVisuraRicerca   = getProp_default("simulazione_visuraRicerca", false);
            conf.simulazioneVisuraControlli = getProp_default("simulazione_visuraControlli", false);
            */
            
            
            // MAIL
            conf.mailIsSend = getProp_default("mail_isSend", false);
            conf.mailSubject = prop.getString("mail_subject");
            conf.mailTo      = getPropListTrimmed("mail_to_list");
            
            
            // PROXY
            conf.proxyHost = getPropQuietly_default("proxy_host", "");
            conf.proxyPort = getPropQuietly_default("proxy_port", 0);
            
            /*
            if ( StringUtils.isNotBlank(conf.proxyHost) && conf.proxyPort > 0  ) {
                logger.debug("SETTO IL PROXY: " + conf.getProxyHost() + ":" + conf.getProxyPort());
                System.getProperties().put("proxySet","true");
                System.getProperties().put("proxyPort",conf.proxyHost + "");
                System.getProperties().put("proxyHost",conf.proxyPort + "");
            } //
            */
            
            
            return conf;
            
        
        } catch (Exception e) {
            logger.error("ConfigInitializer - eccezione: " + e);
            throw e;
        }
        
    } //get
    
    
    
    

    
    private  static Istituti init_hostIstituti(List<String> istitutiList) throws Exception {
        
        try {
        
            Istituti istituti = new Istituti();
            for (String istitutoList: istitutiList) {
                String[] arrIstituto = istitutoList.split(SEPARATOR);
                String nome = arrIstituto[0];
                String descrizione = arrIstituto[1];
                int abi = Integer.parseInt(arrIstituto[2]);
                String letteraIstituto = arrIstituto[3];
                /*
                String ambiente = "";
                if ( arrIstituto.length > 4) {
                     ambiente = arrIstituto[4];
                }
                */
                Istituto istituto = new Istituto(nome, descrizione, abi, letteraIstituto);
                istituti.add(istituto);
            } //for
            
        return istituti;
        
        } catch (Exception e) {
            logger.error("init_hostIstituti - istitutiList: " + istitutiList + " - eccezione: " + e);
            throw e;
        }
        
    } //init_hostIstituti

    
    
    private static  Profilo init_profiloAdmin() throws Exception {
        try {
            
            String profiloAdminString = prop.getString("profilo_admin");
            String nome = StringUtils.substringBefore(profiloAdminString, SEPARATOR).trim();
            String descrizione = StringUtils.substringAfter(profiloAdminString, SEPARATOR).trim();
            return new Profilo(nome, descrizione);
        
        } catch (Exception e) {
            logger.error("init_profiloAdmin: " + e);
            throw e;
        }
    } //init_profiloAdmin
    
    
    private static  List<Profilo> init_profili(Profilo profiloAdmin, List<Profilo> profiliSenzaADMIN) throws Exception {
        try {
            
            List<Profilo> profili = new ArrayList<Profilo>();
            profili.add(profiloAdmin);
            profili.addAll(profiliSenzaADMIN);
            return profili;
            
        } catch (Exception e) {
            logger.error("init_profili - profiloAdmin " +  profiloAdmin + " profiliSenzaADMIN: " + profiliSenzaADMIN + " - eccezione: " + e);
            throw e;
        }
    
    }
    
    
     /**
     * Lista nomi profili COMPRESO quello ADMIN
     *
     * @return Lista nomi profili
     */
    private static  List<String> init_profiliNomi(String profiloAdminNome, List<String> profiliNomiSenzaADMIN) throws Exception {
        try {
        
            List<String> profiliNomi = new ArrayList<String>();
            profiliNomi.add(profiloAdminNome);
            profiliNomi.addAll(init_profiliNomiSenzaADMIN());
            return profiliNomi;
        } catch (Exception e) {
            logger.error("init_profiliNomi - profiloAdminNome: " +  profiloAdminNome + " profiliNomiSenzaADMIN: " + profiliNomiSenzaADMIN + " - eccezione: " + e);
            throw e;
        }
    }
    
    
    
     /**
     * Lista  Profili ESCLUSO quello ADMIN
     *
     * @return Lista Profili
     */
    //formato NOME : DESCRIZIONE
    private static List<Profilo> init_profiliSenzaADMIN() throws Exception {
        try {
            String nome = "";
            String descrizione = "";
            Profilo profilo = null;
            List<Profilo> profili = new ArrayList<Profilo>();
            List<String> profiliString = getPropList("profilo_utenteList");
            for (String profiloString: profiliString) {
                nome = StringUtils.substringBefore(profiloString, SEPARATOR).trim();
                descrizione = StringUtils.substringAfter(profiloString, SEPARATOR).trim();
                profilo = new Profilo(nome);
                profilo.setDescrizione(descrizione);
                profili.add(profilo);
            } //for
            return profili;
        } catch (Exception e) {
            logger.error("init_profiliSenzaADMIN - eccezione: " + e);
            throw e;
        }
    } //init_profiliSenzaADMIN
    
    
     /**
     * Lista nomi profili ESCLUSO quello ADMIN
     *
     * @return Lista nomi profili
     */
    private  static List<String> init_profiliNomiSenzaADMIN() throws Exception {
        
        try {
            
        String nome = "";
        String descrizione = "";
        List<String> profiliNomi = new ArrayList<String>();
        
        List<String> profiliString = getPropList("profilo_utenteList");
        for (String profiloString: profiliString) {
            nome = StringUtils.substringBefore(profiloString, SEPARATOR).trim();
            profiliNomi.add(nome);
        } //for
        return profiliNomi;
        
        } catch (Exception e) {
            logger.error("init_profiliNomiSenzaADMIN - eccezione: " + e);
            throw e;
        }
    
    } //init_profiliNomiSenzaADMIN
    
    
    
    private static  List<Log> init_log() throws Exception {
        
        //formato:
        // host : name : descrizione : url
            
            List<Log> logs = new ArrayList<Log>();
            
            try {
                            
            List<String> urlList =  getPropList("log_list");
            for (String logString : urlList) {
                
                String[] logArr = logString.split(SEPARATOR);
                
                try {
                    
                    String host = logArr[0].trim();
                    String nome = logArr[1].trim();
                    String descrizione = logArr[2].trim();
                    
                    String path = logArr[3].trim();
                    //if ( ! urlS.startsWith("http://")) {
                    //    urlS = "file://" + urlS;
                    //} //
                    
                    
                    Log log = new Log(nome, path);
                    log.setNome(nome);
                    log.setDescrizione(descrizione);
                    log.setHost(host);
                    
                    logs.add(log);
                    
                } catch (Exception e) {
                    logger.warn(e);
                }
                
            } //for
            
            return logs;
            
            } catch (Exception e) {
                logger.error("init_log - eccezione: " + e);
                throw e;
            }
            
        } //init_log
    
    
    
    private static Map<String, String> init_msgInfo() throws Exception {
        try {
            String nome = "";
            String descrizione = "";
            Map<String, String> map = new HashMap<String, String>();
            
            List<String> msgInfoString = getPropList("msgInfo_list");
            for (String s: msgInfoString) {
                nome = StringUtils.substringBefore(s, SEPARATOR).trim();
                descrizione = StringUtils.substringAfter(s, SEPARATOR).trim();
                map.put(nome, descrizione);
            } //for
            return map;
        } catch (Exception e) {
            logger.error("init_msgInfo - eccezione: " + e);
            throw e;
        }
    } //init_profiliSenzaADMIN

    
    
    
    
    private static int getPropQuietly_default(String nome, int defaultValue) {
        try {
            return prop.getInt(nome);    
        } catch (Exception e) {
            return defaultValue;
        }
    }
    
    private static String getPropQuietly_default(String nome, String defaultValue) {
        try {
            return prop.getString(nome);    
        } catch (Exception e) {
            return defaultValue;
        }
    }
    
    
    private static int getProp_default(String nome, int defaultValue) {
        try {
            return prop.getInt(nome);
        } catch (Exception e) {
            logger.warn("getProp_default: " + nome + " eccezione: " + e);
            return defaultValue;
        }
    } //getProp_default
    
    
    private  static boolean getProp_default(String nome, boolean defaultValue) {
        try {
            return prop.getBoolean(nome);
        } catch (Exception e) {
            logger.warn("getProp_default: " + nome + " eccezione: " + e);
            return defaultValue;
        }
    } //getProp_default
    
    private  static String getProp_default(String nome, String defaultValue) {
        try {
            String value = prop.getString(nome);
            if (StringUtils.isBlank(value)) {
                return defaultValue;
            } else {
                return value;
            }
        } catch (Exception e) {
            logger.warn("getProp_default: " + nome + " eccezione: " + e);
            return defaultValue;
        }
    } //getProp_default
    
    
    
    private  static String getProp(String name) throws Exception {
        
        try {
            name = StringUtils.trimToEmpty(name);
            String value = prop.getString(name);
            if (StringUtils.isBlank(name)) {
                throw new NameNotFoundException("property name: " + name + " isBlank");
            } 
            return value;
        
        } catch (Exception e) {
            logger.error("getProp: " + name + " - Eccezione: " + e);
            throw e;
        }
        
    } //getProp
    
    

    
    private  static List<String> getPropListTrimmed(String name) throws Exception {
        
        try {
            
            name = StringUtils.trimToEmpty(name);
            List<String> list = getPropList(name);
            List<String> esito = new ArrayList<String>();
            
            for (String value: list) {
                if (!StringUtils.isBlank(value)) {
                    esito.add(value);
                } //
            } //
            
            return esito;
        
        } catch (Exception e) {
            logger.error("getPropListTrimmed: " + name + " - Eccezione: " + e);
            throw e;
        }
        
    } //getPropListTrimmed
    
    
    //@SuppressWarnings({ "unchecked", "rawtypes" })
    private static List<String> getPropList(String name) {
        return (List) prop.getList(name);
    } //
    
} //END OF CLASS
