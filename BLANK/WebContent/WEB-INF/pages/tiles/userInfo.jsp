<%@page import="application.Constant"%>
<%@page import="org.apache.commons.lang.StringUtils"%>
<%@page import="application.resource.AppResources"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="myTag" tagdir="/WEB-INF/tags"%>
<%
//ATTENZIONE 
//questa è una pagina caricata sempre quindi se si usa nei campi INPUT html un ID 
// usato da una pagina che continete questa è un PROBLEMA.
// usare allora in questa pagina ID particolari, ad esempio per l'HIDDEN INPUT 'azione'
%>
	<img id="userInfo_toggle" src="<myTag:pathImg/>/varie/info.png" border=0 title="info">
	${APP_USER.nome} ${APP_USER.cognome}
	<div>[${APP_USER.username}] - UFF: ${APP_USER.codiceUfficio}</div>

	<STYLE TYPE="text/css" MEDIA=screen>
	<!--
	#userInfo_box {
		border:1px solid gray;
		background-color:#D5DCE2;
		margin-left:-90px;
		margin-top:20px;
		padding:10px;
		width:320px;
		font-size:10px;
		text-align:left;
		
		position: absolute;
		z-index: 99;
	}
	
	.userInfo_title {
		margin:3px;
		padding:3px;
	}
	
	.userInfo_item {
		background:orange;
		border:1px solid gray;
		margin:3px;
		padding:3px;
	}
	
	.userInfo_adminOnly {
		background:yellow;
		border:1px solid gray;
		margin:3px;
		padding:3px;
	}
	
	-->
	</STYLE>


		<div id="userInfo_box" style="display:none">
			<div id="userInfo_title">User Info</div>
			<div class="userInfo_item">${APP_USER.username}</div>
			<div class="userInfo_item">Istituto: ${APP_ISTITUTO.nome} - ${APP_ISTITUTO.abi}</div>
		
			<div class="userInfo_item">
				Profilo: ${APP_USER.profilo.descrizione} <s:if test="#APP_USER.isAdmin()"> - ${APP_USER.profilo.nome}</s:if>
				<!-- funzioni  -->
				<!-- <div class="userInfo_adminOnly">
						<s:iterator status="stat" value="#APP_USER.profilo.funzioni">
					 	${id} - ${nome} - ${descrizione}				 
						</s:iterator>
					</div>
				-->
			</div>
			
		</div><!--userInfo_box-->
	
		
<script type="text/javascript">
	//$(document).ready(function() {
	$("#userInfo_toggle").click(function(){
		$("#userInfo_box").toggle();
	});
//});
</script>
	