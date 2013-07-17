<%@page import="application.Constant"%>
<%@page import="application.resource.AppResources"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<script type="text/javascript" src="<%=request.getContextPath()%>/pages/include/js/jquery/jquery.js"></script>
<!-- ALL jQuery Tools. No jQuery library -->
<!--script src="http://cdn.jquerytools.org/1.2.5/jquery.tools.min.js"></script-->
<%
AppResources appResources = AppResources.get(session);
String theme = appResources.getTheme();
if ( theme == null ) {
	theme = Constant.theme;
}
%>
<!-- JQUERY-UI -->
<link type="text/css" href="<%=request.getContextPath()%>/pages/include/js/jquery/jquery-ui/css/<%=theme%>/jquery-ui.css" rel="Stylesheet" />	
<!--
Per i css di JQUERY
usando il link diretto c'è il problema del protocollo.
Se sono in https ed uso il link http allora le immagini non si vedono perchè sul foglio css 
gli indirizzi sono in http. Occorrerebbe mettere dinamicamente il protocollo. 
link type="text/css" href="http://ajax.googleapis.com/ajax/libs/jqueryui/1.8.21/themes/<%=theme%>/jquery-ui.css" rel="Stylesheet" /
-->
<!--link type="text/css" href="<%=request.getContextPath()%>/pages/include/js/jquery/jquery-ui/jquery-ui-custom.css" rel="Stylesheet" /-->
<script type="text/javascript" src="<%=request.getContextPath()%>/pages/include/js/jquery/jquery-ui/jquery-ui.js"></script>


<!-- JQUERY-DATEPICKER -->
<script type="text/javascript" src="<%=request.getContextPath()%>/pages/include/js/jquery/jquery-ui/jquery.ui.datepicker-it.js"></script>


<!-- JQUERY-PLUGIN -->
<link type="text/css" media="screen"  href="<%=request.getContextPath()%>/pages/include/js/jquery/plugin/vsContext/css/vscontext.css" rel="stylesheet"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/pages/include/js/jquery/plugin/vsContext/vscontext.jquery.js"></script>

<script type="text/javascript" src="<%=request.getContextPath()%>/pages/include/js/jquery/plugin/jquery.dropdownPlain.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/pages/include/js/jquery/plugin/jquery.blockUI.js"></script>

<!-- jquery.tablesorter.js -->
<script type="text/javascript" src="<%=request.getContextPath()%>/pages/include/js/jquery/plugin/tablesorter/jquery.tablesorter.js"></script>
<link type="text/css" media="screen"  href="<%=request.getContextPath()%>/pages/include/js/jquery/plugin/tablesorter/style.css" rel="stylesheet"></script>
	


<!-- 	
<link   type="text/css" media="screen"  href="<%=request.getContextPath()%>/pages/include/js/jquery/plugin/jquery.notice/jquery.notice.css" rel="stylesheet" />
<script type="text/javascript" src="<%=request.getContextPath()%>/pages/include/js/jquery/plugin/jquery.notice/jquery.notice.js"></script>
-->


	
<script type="text/javascript" src="<%=request.getContextPath()%>/pages/include/js/jquery/plugin/jquery-form.js"></script>

	
<!-- INPUT MASK  -->
<script type="text/javascript" src="<%=request.getContextPath()%>/pages/include/js/jquery/plugin/maskedInput.js"></script>

<!--  TOOLTIP -->
<!-- 
<<link   type="text/css" media="screen"  href="<%=request.getContextPath()%>/pages/include/js/jquery/plugin/tipsy/tipsy.css" rel="stylesheet" />
<script type="text/javascript" src="<%=request.getContextPath()%>/pages/include/js/jquery/plugin/tipsy/jquery.tipsy.js"></script>
-->
	
<!--  POPUPWINDOWS -->
<!--script type="text/javascript" src="<%=request.getContextPath()%>/pages/include/js/jquery/plugin/popup/popupWindow.js"></script-->
<script type="text/javascript" src="<%=request.getContextPath()%>/pages/include/js/jquery/plugin/popup/jquery.popupwindow.js"></script>
	
<!--  JQUERY CORNER -->
<script type="text/javascript" src="<%=request.getContextPath()%>/pages/include/js/jquery/plugin/jquery.corner.js"></script>
	
	
<script type="text/javascript" src="<%=request.getContextPath()%>/pages/include/js/jquery/jquery.rob.js"></script>
	
