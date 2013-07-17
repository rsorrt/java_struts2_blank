<%@ tag description="ajaxForm Tag" pageEncoding="UTF-8" dynamic-attributes="dynattrs"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ attribute name="ajaxForm_formId"  required="true"%>
<%@ attribute name="ajaxForm_url"     required="true"%>
<%@ attribute name="ajaxForm_indicatorId"  required="true"%>
<%@ attribute name="ajaxForm_divId"  required="true"%>
<!--AJAX-FORM-TAG-->
<script type="text/javascript">  
$(document).ready(function(){  
	var ajaxReportMeseOptions = { 
			target:       '#${ajaxForm_divId}', 
	        beforeSubmit: function() {$('#${ajaxForm_divId}').html(""); $('#${ajaxForm_indicatorId}').show();},  
	        success:      function() {$('#${ajaxForm_indicatorId}').hide();},
	        url:          '${ajaxForm_url}'
	 }; 
	// ATTENZIONE: la var options deve precedere 
	$('#${ajaxForm_formId}').ajaxForm(ajaxReportMeseOptions);
});
</script>
<!--/AJAX-FORM-TAG-->