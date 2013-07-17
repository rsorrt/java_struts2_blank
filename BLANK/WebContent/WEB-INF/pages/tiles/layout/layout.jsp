<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java"  contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="myTag" tagdir="/WEB-INF/tags"%>
<myTag:varGlobal />
<html>
	<head>
		<title><tiles:insertAttribute name="title" ignore="true" /></title>
		<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1"/>
		<s:include value="include/css.jsp" />
		<s:include value="include/jquery.jsp" />
	</head>
	<body>
		<div id="container">
		
			<div id="istitutoInfo">&nbsp;</div>
			
			<div id="header">
				<div id="titoloApplicazione_box">
					<div id="titoloApplicazione_inner">
						<div id="titoloApplicazione_sx">&nbsp;</div>
						<div id="titoloApplicazione_center">${APP_CONFIG.titolo}</div>
						<div id="titoloApplicazione_dx">&nbsp;</div>
						<div class="clear"></div>
					</div>
				</div>
				<div class="clear"></div>
			</div>
		
			<div id="content">
				<div id="content_inner">
					<tiles:insertAttribute name="msg" />	
					<tiles:insertAttribute name="content" />					 
				</div>
			</div>
			
			<div id="footer"><tiles:insertAttribute name="footer" /></div>
		</div>
	</body>
</html>


