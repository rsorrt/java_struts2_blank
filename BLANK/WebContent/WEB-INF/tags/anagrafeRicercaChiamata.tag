<%@ tag description="Anagrafe Chiamata Tag" pageEncoding="UTF-8" dynamic-attributes="dynattrs"%>
<%@ tag import="org.apache.commons.lang.StringUtils"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ attribute name="formId"     required="false"%>
<%@ attribute name="formAction" required="false"%>
<%@ attribute name="divToCleanId" required="false"%>
<%@ attribute name="jsFunction" required="false"%>
<!-- ANAGRAFE RICERCA CHIAMATA TAG -->
<%
String anagrafeRicercaChiamata_formId=(String)jspContext.getAttribute("formId");
String anagrafeRicercaChiamata_divToCleanId=(String)jspContext.getAttribute("divToCleanId");
String anagrafeRicercaChiamata_jsFunction=(String)jspContext.getAttribute("jsFunction");
%>
<a href="anagrafeRicercaChiamata" id="anagrafeRicerca" rel="windowCallUnload"><img title="Chiama Anagrafe" src="<%=request.getContextPath()%>/pages/img/azioni/chiamaAnagrafe.png" border="0"/></a>

<script type="text/javascript">
$(document).ready(function() {
//----------------------
	
	var profile = {
		windowCallUnload:
		{
			height:600,
			width:800,
			center:1,
			onUnload:unloadcallback
		}
	};
	// sblocca il blockUI chudendo il popup
	function unloadcallback(){
		$.unblockUI();
		<%if (StringUtils.isNotBlank(anagrafeRicercaChiamata_jsFunction)){%>
			${jsFunction}();
		<%}%>
	};
	$("#anagrafeRicerca").popupwindow(profile);
	
	$("#anagrafeRicerca").click(function(){
		$.blockUI({ message: 'Applicazione Anagrafe aperta<br>per reperimento ndg' });
	});
	
//----------------------
});  
// per aggiornare NDG su ritorno CROSS anagrafe
function aggiornaNdg(ndg)  {
	$("#ndg").val(ndg);
	
	<%if (StringUtils.isNotBlank(anagrafeRicercaChiamata_formId)){%>
		$('#${formId}').attr('action','${formAction}');
		$('#${formId}').submit();
		wait();
	<%}%>
	
	<%if (StringUtils.isNotBlank(anagrafeRicercaChiamata_divToCleanId)){%>
		$('#${divToCleanId}').html('');
	<%}%>

	
}
</script>
<!-- /ANAGRAFE RICERCA CHIAMATA TAG -->
