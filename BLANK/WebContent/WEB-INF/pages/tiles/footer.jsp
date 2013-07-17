<%@page import="rsorrt.commons.util.UtilString"%>
<%@page import="application.resource.AppResources"%>
<%@page import="application.ConstantLog"%>
<%@page import="rsorrt.commons.util.UtilNet"%>
<%@page import="java.net.InetAddress"%>
<%@page import="java.io.File"%>
<%@page import="org.apache.commons.io.FileUtils"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<%@taglib prefix="myTag" tagdir="/WEB-INF/tags"%>
<!--FOOTER-->

<%
String hostName     = UtilString.defaultString(UtilNet.getHostName());
String urlAppLog    = UtilString.defaultString(ConstantLog.getUrlAppLog(hostName), "#");
String urlSystemLog = UtilString.defaultString(ConstantLog.getUrlSystemLog(hostName), "#");

String urlWasMap = "";
if (AppResources.getConfig().isTest()) {
	urlWasMap = "http://wasmapcoll.intranet.servizi";
} else {
	urlWasMap = "http://wasmap.intranet.servizi";
}
%>

<span id="menuAdminServer"><myTag:img pathRelative="varie/server.png" alt="server" title="server"/> <small><%=UtilNet.getHostName()%></small></span>
<s:if test="#APP_USER.isAdmin">
<div class="vs-context-menu" id="menuAdminServerContext">
	<ul style="text-align:left;">
		<li class="link"><a href="<%=urlWasMap%>" target="_blank" title="<%=urlWasMap%>">WasMap</a></li>
		<li class="link"><a href="<%=urlSystemLog%>" target="_blank" title="<%=urlSystemLog%>">SystemOut.log</a></li>
		<li class="link"><a href="<%=urlAppLog%>" target="_blank" title="<%=urlAppLog%>">vcweb.log</a></li>
	</ul>
</div>
</s:if>

<s:if test="#APP_USER.isAdmin">
	<!-- Version: ${APP_CONFIG.version}-->
	<%
	String info = "";
	try {
		String realPath = getServletContext().getRealPath("/");
		File fileInfo = new File(realPath + "/info.txt");
		info = FileUtils.readFileToString(fileInfo);	
	} catch( Exception e) {
		info = e.toString();
	}
	%>
	<div style="text-align:center; border:1px dotted gray;padding:5px;margin:10px;">
		Visibile solo all'admin:
		<s:if test="msgAdminOnly!=''">
			<div id="msgAdminOnly">${msgAdminOnly}</div>
		</s:if>
		<div><%=info%></div>
	</div>
</s:if>
<script type="text/javascript">
$(document).ready(function() {
   $('#menuAdminServer').vscontext({menuBlock:'menuAdminServerContext'});
});
</script>
<!--/FOOTER-->