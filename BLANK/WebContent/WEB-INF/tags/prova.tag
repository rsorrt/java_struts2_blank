<%@ tag description="Img" pageEncoding="UTF-8" dynamic-attributes="dynattrs"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ attribute name="nome" required="false"%>
<%@ attribute name="var_3" required="false"%>
<%@ attribute name="isError" required="false"%>
<%
	String provaTag_nome = (String)jspContext.getAttribute("nome");
	String provaTag_isError = (String)jspContext.getAttribute("isError");
%>
<div>
Tag 
<ul>
	<li>nome: $='${nome}'</li>
	<li>#attr['nome']='<s:property value="#attr['nome']"/>'</li>
	<li>#param['nome']='<s:property value="#param['nome']"/>'</li>
	<li>#request['nome']='<s:property value="#request['nome']"/>'</li>
</ul>
	
	<li>%{#attr.jspContext.getAttribute('nome')}=' <s:property value="%{#attr.jspContext.getAttribute('nome')}"/>'
	<li> (String)jspContext.getAttribute("nome")='<%=provaTag_nome%>'</li>
	<li> isError: '<%=provaTag_isError%>'</li>

	<li> (String)jspContext.getAttribute("var_3")='<%=(String)jspContext.getAttribute("var_3")%>'</li>
	
</ul>
</div>

 