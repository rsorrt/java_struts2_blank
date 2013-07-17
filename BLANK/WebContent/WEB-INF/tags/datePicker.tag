<%@ tag description="DatePicker Tag" pageEncoding="UTF-8" dynamic-attributes="dynattrs"%>
<%@ tag import="org.apache.commons.lang.StringUtils"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ attribute name="datepicker_id"  required="true"%>
<!--DATE PICKER TAG-->
<script type="text/javascript">  
$(document).ready(function(){  
	$('#${datepicker_id}').datepicker({
		changeMonth: true,
		changeYear: true,
		yearRange: '1900:2100', 
		inline: true
	});
});
</script>
<!--/DATE PICKER TAG-->