<%@taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="myTag" tagdir="/WEB-INF/tags"%>
<s:if test="#APP_ISTITUTO != null">
	<a href="<s:url action="sceltaIstituto" namespace="/secure" />"><img src="<myTag:pathImg/>/logo/istituti/${APP_ISTITUTO.abi}.gif" border=0 title="Istituto abi ${APP_ISTITUTO.abi}"></a>
</s:if>