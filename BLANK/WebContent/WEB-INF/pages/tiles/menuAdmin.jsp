<%@taglib prefix="s" uri="/struts-tags"%>
<s:url id="urlImgAdmin" value="/pages/img/profili/admin.png"/>
<s:url id="urlImgDebug" value="/pages/img/varie/debug.png"/>
<span id="menuAdmin"><img src="${urlImgAdmin}" border="0" title="Menu Admin"/></span>
<div class="vs-context-menu" id="menuAdminContext">
	<ul style="text-align:left;">
		<!-- li class="home seprator"><a href="<s:url action="loginAdmin" namespace=""/>" id="menu_1">Login</a></li-->
		<!-- li class="admin"><a href="<s:url action="admin" namespace="/secureAdmin"/>" id="openPageAdmin">Admin</a></li-->
		
		<li class="admin"><a href="<s:url action="admin" namespace="/secureAdmin" />"    target="_blank">Admin</a></li>
		<li class="debug"><a href="<s:url action="index" namespace="/config-browser" />" target="_blank">Debug</a></li>
	
		
		<li class="home seprator"><a href="<%=request.getContextPath()%>/adminLoginXSSO">LoginXSSO simulatore</a></li>
	
		<!--li class="paste seprator"><a href="javascript:paste();" id="menu_3">Paste</a></li-->
		<!--li class="edit"><a href="javascript:edit();" id="menu_4">Edit</a></li-->
		<!--li class="delete"><a href="javascript:del();" id="menu_5">Delete</a></li-->
	</ul>
</div>
<script type="text/javascript">
$(document).ready(function() {
	$('#menuAdmin').vscontext({menuBlock: 'menuAdminContext'});
	$("#openPageAdmin").click(function(){
		openPopupAdmin();
	});
});
var adminWindow;
function openPopupAdmin() {
	if ( adminWindow != null ) {
		if ( ! adminWindow.closed){
			adminWindow.focus();
			return false;	
		}
	}
	//alert("apro il popup..");
	var url = '<%=request.getContextPath()%>/secureAdmin/admin';
	var width = 900;
	var height = 600;
	var left = (screen.width/2)-(width/2);
	var top  = (screen.height/2)-(height/2) - 50;
	var position = ' top=' + top + ',left=' + left;
	var parameters = 'height=600,width=900';
	parameters += ',resizable=yes,scrollbars=yes,status=yes';
	parameters += ',' + position ;
	adminWindow=window.open(url,'Admin',parameters);
	if (window.focus) {adminWindow.focus()}
	return false;
} //openPopupAdmin

</script>
	