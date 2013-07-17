package application.struts2.action;




import org.apache.commons.lang.StringUtils;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;

import rsorrt.commons.factory.FactoryLogger;
import rsorrt.commons.log.MyLogger;


import application.resource.AppResources;
import application.struts2.Tiles;
import application.struts2.interceptor.aware.AwareAppResources;

import com.opensymphony.xwork2.ActionSupport;

@Namespace(value="/")
@ParentPackage(value="my-default")

@Results({
	@Result(name="error", location=Tiles.ERROR,  type="tiles")
})
public abstract class BaseAction extends ActionSupport	
implements AwareAppResources {
	

	private static final long serialVersionUID = 1L;


	protected static final MyLogger logger = FactoryLogger.getLogger();
	
	// msg che compare nella pagina di risposta jsp
	protected String msg = "";
    // se è di segnalazione compare finestra apposita per inviare
	// la mail di segnalazione
	protected boolean isMsgSegnalazione;
	
	protected String eccezione;
	
	// QUESTA VAR viene settata in AwareInterceptor
	// e viene usata nel jsp del msg utente
	protected String loggerId = "";
	
	protected AppResources appResources;
	
	/*
	public void prepare() throws Exception {
		ActionContext ctx = ActionContext.getContext();
		String metodoInvocato = ctx.getActionInvocation().getInvocationContext().getName();
		System.out.println("\n\n============>\nMetodoInvocato: " + metodoInvocato);
		//this.loggerId = getLoggerId();
		//System.out.println("\n\n------>BASE ACTION loggerId=" + loggerId + "\n\n");
	}
	 */
	
	/*
	public String getLoggerId() {
		
		String ret = Util.getTimeStamp();
		
		String sessionId = "";
		try {
			sessionId = ServletActionContext.getRequest().getSession().getId();
			if ( StringUtils.isNotBlank(sessionId) ) {
				ret += "-" + sessionId ;	
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		try {
			if ( appResources != null )  {
				if ( appResources.getUser() != null ) {
					if ( StringUtils.isNotBlank(appResources.getUser().getUsername()) ) {
						ret += "-" + appResources.getUser().getUsername() ;
					}//
				}//
			}//if appResources != null
			return ret;
		} catch (Exception e) {
			logger.error(loggerId, e);
			return "";
		}
	}
	*/

	public String _resultERROR() {
	//	if (appResources != null && appResources.isServizio()) {
	//		return "errorServizi";
	//	} else {
			return "error";
	//	}	
	}	
	
	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}
	
	public boolean getIsMsg() {
		return StringUtils.isNotBlank(msg);
	}
	
	public boolean getIsEccezione() {
		return StringUtils.isNotBlank(eccezione);
	}

	public String getEccezione() {
		return eccezione;
	}

	public void setEccezione(String eccezione) {
		this.eccezione = eccezione;
	}
	
	
	public String getEccezioneUser() {
		
		
		if ( StringUtils.isNotBlank(eccezione) ) {
			String userMsg = "Si e' verificato un errore, l'operazione non e' stata eseguita";
			//if ( logger.isLevelDEBUG()) {
	         //   return userMsg + "\n\n" + eccezione;
			//}  else {
				return userMsg;
			//} //
		} //if

		return "";
	} //getEccezioneUser
	
	
	public String whoami() {
		return this.getClass().getName();
	} //

	public void setAppResources(AppResources appResources) {
		this.appResources = appResources;
	}



	public boolean isMsgSegnalazione() {
		return isMsgSegnalazione;
	}
	public boolean getIsMsgSegnalazione() {
		return isMsgSegnalazione;
	}
	public void setIsMsgSegnalazione(boolean isMsgSegnalazione) {
		this.isMsgSegnalazione = isMsgSegnalazione;
	}


	public String getLoggerId() {
		return loggerId;
	}


	public void setLoggerId(String loggerId) {
		this.loggerId = loggerId;
	}

	

/*
	public Locale  getLocale() {
		return ActionContext.getContext().getLocale();
	}
*/
}
