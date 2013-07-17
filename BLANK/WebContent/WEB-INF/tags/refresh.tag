<%@tag import="org.apache.commons.lang.StringUtils"%>
<%@ tag description="tag refresh" pageEncoding="UTF-8" dynamic-attributes="dynattrs"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ attribute name="refresh_seconds" required="true"%>
<!-- TAG REFRESH -->
<%
String tag_refresh_seconds = (String)jspContext.getAttribute("refresh_seconds");
if ( StringUtils.isBlank(tag_refresh_seconds) ) {
	tag_refresh_seconds = "50";
}
%>
<iframe id="frame_refresh" height="0px" width="0px" style="display:none;"></iframe>
<script type="text/javascript">
$(document).ready(function() {
//----------------------
var ifrm = document.getElementById('frame_refresh');
ifrm = (ifrm.contentWindow) ? ifrm.contentWindow : (ifrm.contentDocument.document) ? ifrm.contentDocument.document : ifrm.contentDocument;
ifrm.document.open();
ifrm.document.write( "<html><head><title></title><meta http-equiv='refresh' content='<%=tag_refresh_seconds%>'></head><body></body></html>" );
ifrm.document.close();
//----------------------
});
</script>
<!-- /TAG REFRESH -->