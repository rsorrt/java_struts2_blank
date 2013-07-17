<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="myTag" tagdir="/WEB-INF/tags"%>
<%@ page import="application.resource.AppResources"%>
<%@ page language="java"  contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<myTag:varGlobal />
<html>
	<head>
		<META http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<!--
		<META http-equiv="Expires" content="-1">
		<META http-equiv="Cache-Control" content="no-store">
		<META http-equiv="Cache-Control" content="no-cache">
		<META http-equiv="Pragma" content="no-cache">
		 -->
	    <!-- block back browser button -->
		<!-- script type="text/javascript" language="javascript">
	        javascript:window.history.forward(1);
		</script-->
	
		<title><tiles:insertAttribute name="title" ignore="true" /></title>
		<!--meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1"/-->
		<s:include value="include/css.jsp" />
		<s:include value="include/jquery.jsp" />
	</head>

	<body>
	
		<div id="container">
			<div id="header">
				
				<div id="istitutoInfo"><tiles:insertAttribute name="istitutoInfo" /></div>
			
				<div id="titoloApplicazione_box">
					<div id="titoloApplicazione_inner">
						<div id="titoloApplicazione_sx">&nbsp;</div>
						<div id="titoloApplicazione_center">
							${APP_CONFIG.titolo}
							<s:if test="#APP_CONFIG.test">
								<span id="msgTest">${APP_CONFIG.msgTest}</span>
							</s:if> 
						</div>
						<div id="titoloApplicazione_dx"></div>
						<div class="clear"></div>
					</div>
				</div>
				
				<div id="user_box">
					<div id="user_inner">
						<div id="user_sx">&nbsp;</div>
						<div id="user_center">
							<s:if test="#APP_USER.isAdmin()"><tiles:insertAttribute name="menuAdmin" /></s:if><tiles:insertAttribute name="userInfo" />
						</div>
						<div id="user_dx">&nbsp;</div>
					</div>
					<div class="clear"></div>
				</div>
				
				<div class="clear"></div>
			</div>
		
			<div id="content">
				<div class="mainMenu"><tiles:insertAttribute name="menu" /></div>
				<div id="content_inner">
					<tiles:insertAttribute name="msg" />
					<tiles:insertAttribute name="content" />
				</div>
			</div>
			
			<div id="footer"><tiles:insertAttribute name="footer" /></div>
		</div>
		
	</body>
</html>