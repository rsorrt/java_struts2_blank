package application.struts2.action;


import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;


@Action("Test")

@Namespace(value="")
@ParentPackage(value="my-default")

@Results({
	@Result(name="success", location="test.tiles",  type="tiles")
})
public class TestAction extends BaseAction {

	private static final long serialVersionUID = -1808397478458881860L;
	
	private String msg = "";
	
	public String execute() {
	
		msg = "ciao sono il messaggio";
		return SUCCESS;
		
	} //

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}
	
	
	
} // END OF FILE
