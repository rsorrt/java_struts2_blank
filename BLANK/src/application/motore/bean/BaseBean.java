
package application.motore.bean;


import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import rsorrt.commons.factory.FactoryLogger;
import rsorrt.commons.log.MyLogger;


import application.motore.BaseInterface;

import com.thoughtworks.xstream.annotations.XStreamOmitField;
/**
 * The Class Base.
 * 
 * Modifica il metodo toString per stampare i membri un automatico usano
 * Reflection Aggiunge il metodo toXML per stampare la rappresentazione XML
 * dell'oggetto
 * 
 */
public abstract class BaseBean implements BaseInterface {

     /**
     * Oggetto xmlBase per funzionalità XML
     * 
     * @see xml.XMLBase
     * 
     */

    /*
     * Stampa i membri della classe usando Reflection
     */

	//nel caso in cui usi XStream in una classe che estende BaseBean
	//devo omettere dall'xml 
	@XStreamOmitField
    protected static MyLogger logger = FactoryLogger.getLogger();
	
	@XStreamOmitField
    protected String loggerId = "";

	
    public String toString() {
        return ToStringBuilder.reflectionToString
        		(this, ToStringStyle.MULTI_LINE_STYLE);
    }

	
    public String toStringComplete() {
        return 
              "\n------------------------------------------"
            + "\n" + super.toString()
            + "\nclass: " + this.getClass().getName()
            + "\n---------- FIELDS by REFLECTION ----------" 
            + "\n" + ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE)
            + "\n------------------------------------------";
    }
    
    public static String toString(Object obj) {
        return ToStringBuilder.reflectionToString(obj,
                ToStringStyle.MULTI_LINE_STYLE);
    }

    
    public String getReference() {
        return (new StringBuilder()).append(getClass().getName()).append("@").append(Integer.toHexString(hashCode())).toString();
    }
    
    
    public String trim(String s) {
        if (s != null) {
            return s.trim();
        } else {
            return null;
        } //
    }

    public String getLoggerId() {
        return loggerId;
    }

    public void setLoggerId(String loggerId) {
        this.loggerId = loggerId;
    }




    
    
    
//END OF CLASS
}
