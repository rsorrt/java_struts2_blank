ciao sono PROVA.JSP

<%@ taglib uri="/rsorrtSecurity" prefix="rsorrtSecurity" %>



<rsorrtSecurity:secure role="ADMIN">
	<ul>
		<li>uno
		<li>due
		<li>tre
	</ul>
	msg=${msg} 
</rsorrtSecurity:secure>