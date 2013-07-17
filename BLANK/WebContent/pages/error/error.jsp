<%@ taglib prefix="s" uri="/struts-tags"%>

<div class="msg img error">
<div>
Si e' verificato un errore.
<br>
<s:if test="%{isMsg}">
	<s:property value="msg"/>
</s:if>
</div>
</div>	

<s:if test="%{isEccezione}">
	<s:if test="#APP_USER.isAdmin()">
		<div style="margin-top:15px; margin-bottom:15px;padding:5px; border:1px solid #FFBF18;">
			<div style="border-bottom:1px dotted gray; width:90%;">visibile solo all'admin:</div>
			<div style="margin-top:5px;padding:5px;" id="dialog-eccezione_text">${eccezione}</div>
		</div>
	</s:if>
</s:if>
