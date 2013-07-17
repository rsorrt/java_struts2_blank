<%@ taglib prefix="s" uri="/struts-tags"%>

<h2>Login Error</h2>

<s:if test="%{isMsg}">
	<div class="msg img error"><s:property value="msg"/></div>
</s:if>