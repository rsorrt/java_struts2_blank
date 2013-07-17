<%@ tag description="put the tag description here" pageEncoding="UTF-8" dynamic-attributes="dynattrs"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="myTag" tagdir="/WEB-INF/tags"%>
<%@ attribute name="formInDialog_formId" required="true"%>
<!-- PDF DIALOG -->
<div id="formInDialog_dialogId" style="display:none; text-align: center;">
	<div style="padding:10px;" id="formInDialog_boxId">
		<iframe 
			id="formInDialog_iframeId" 
			name="formInDialog_iframeName" 
			width="800" height="400" 
			frameborder="no" 
			scrolling="auto"
		></iframe>
	</div>
</div>
<script type="text/javascript">
$(document).ready(function() {
//----------------------
var iframeContent =	'<iframe id="formInDialog_iframeId" name="formInDialog_iframeName" width="800" height="400" frameborder="no" scrolling="auto"></iframe>';

	$("#formInDialog_dialogId").dialog({
		title: 'Documento Pdf',
		modal: true,
		autoOpen: false,
		height: 500,
		width: 900,
		resizable:true,
		draggable:true
	});
	$("#formInDialog_dialogId").dialog({
		open : function(event, ui) {  },
		close: function(event, ui) { $("#formInDialog_boxId").html(iframeContent); }
	});
	
	$("#${formInDialog_formId}").submit(function(){
		
		var msgWaiting = 
			'<div style="margin-left:35%; margin-top:50px;width:250px;text-align:center;"><img src="<%=request.getContextPath()%>/pages/img/progress/progress_1.gif"><br>generazione pdf in corso... attendere</div>';
		var ifrm = document.getElementById('formInDialog_iframeId');
		ifrm = (ifrm.contentWindow) ? ifrm.contentWindow : (ifrm.contentDocument.document) ? ifrm.contentDocument.document : ifrm.contentDocument;
		ifrm.document.open();
		ifrm.document.write( msgWaiting );
		ifrm.document.close();
		
		$("#${formInDialog_formId}").attr("target","formInDialog_iframeName");
		$("#${formInDialog_formId}").dialog('open');
	 	//$("#formInDialog_formId").submit();
		return false;
	});
	
//----------------------
});  
</script>
<!-- /PDF DIALOG -->




