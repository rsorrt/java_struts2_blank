<%@ tag description="put the tag description here" pageEncoding="UTF-8" dynamic-attributes="dynattrs"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ tag import="application.resource.AppResources"%>
<%
//AppResources appResource = AppResources.get(session);
//if ( appResource != null ) {
 //request.setAttribute("APP_CONFIG"  , appResource.getConfig()); 
 //request.setAttribute("APP_USER"    , appResource.getUser());
 //request.setAttribute("APP_ISTITUTO",  appResource.getIstituto());
 //request.setAttribute("APP_THEMES",  appResource.getThemes());
//}
%>
<s:set name="APP_CONFIG"   value="@application.resource.AppResources@getConfig()"/>
<s:set name="APP_USER"     value="#session.APP_RESOURCES.user"/>
<s:set name="APP_ISTITUTO" value="#session.APP_RESOURCES.istitutoQuietly"/>
<s:set name="APP_THEMES" value="#session.APP_RESOURCES.themes"/>