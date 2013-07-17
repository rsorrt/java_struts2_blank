package tag;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.BodyContent;
import javax.servlet.jsp.tagext.BodyTagSupport;

public class SecurityTag extends BodyTagSupport {

	
	private static final long serialVersionUID = 1L;
	
	private BodyContent bodyContent;  
	private String role = "";
	
	
	 public void setBodyContent(BodyContent bodyContent) {
		 System.out.println("setBodyContent");
		 this.bodyContent = bodyContent;
	 }
	

/*	 	
	public int doStartTag() {
		
		if (role == null || role.trim().length() == 0) {
			System.out.println("No role name " + " found");
			return SKIP_BODY;
		}

		System.out.println("OK ROLE FOUND:" + role);
		
		return EVAL_BODY_BUFFERED;
		
	}
*/	
	
	public int doAfterBody() throws JspException {
		
		//System.out.println ("--> doAfterBody...");
		
		try {
			
			if (bodyContent == null) {
				System.out.println("---> BodyContent is null");
				return SKIP_BODY;
			}
			
		    String   bodyString  = bodyContent.getString();
		    if (bodyString == null || bodyString.trim().length() == 0) {
				System.out.println("-->bodyString is blank");
				return SKIP_BODY;
			}
			
		   // System.out.println ("--> elaboro body...");
		    
		    JspWriter   out = bodyContent.getEnclosingWriter();
			
		    out.print (
		    		"<br>SECURITY ZONE FOR ROLE: " + role 
		    		+ "<br>"
		    		+ bodyString
		    );
		    
		    //bodyContent.clear(); // empty buffer for next evaluation

		} catch (IOException e) {
			// System.out.println ("--> EXCEPTION: " + e);
			throw new JspException("Error:" + e.getMessage());
		}
		
		// System.out.println ("--> doAfterBody return");
		
		return SKIP_BODY;
	}

	
	
	 public int doEndTag() throws JspException {  
	   
		 /*
		 try {  
	         if(bodyContent != null) {  
	           bodyContent.writeOut(  
	           bodyContent.getEnclosingWriter());  
	         }  
	      } catch(IOException e) {  
	        throw new JspException(  
	        "Error: " + e.getMessage());  
	      }  
	      */
	      return EVAL_PAGE;
	     
	        
	   }  
	
	
	public void setRole(String role) {
		this.role = role;
	}

	public String getRole() {
		return role;
	}
	
	// end of class
}
