<%@ taglib prefix="s" uri="/struts-tags"%>

<h2>Profilo Utente non valido</h2>

<s:if test="%{isMsg}">
	<div class="msg img error"><div><s:property value="msg"/></div></div>
</s:if>