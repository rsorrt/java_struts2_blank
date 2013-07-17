<%@tag import="application.motore.bean.profili.Funzioni"%>
<%@tag import="application.motore.bean.User"%>
<%@ tag import="application.motore.bean.User"%>
<%@ tag import="application.resource.AppResources"%>
<%@ tag import="application.motore.bean.profili.Funzioni"%>
<%@ tag description="put the tag description here" pageEncoding="UTF-8" dynamic-attributes="dynattrs"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!-- %@attribute name="activeIndex" required="true"% -->
<div class="mainMenu">
	<ul class="dropdown">

		<li>
			<a href="#" title="PRINCIPALE">PRINCIPALE</a>
			<ul class="sub_menu">
				 <li><a href="<s:url action="menu" namespace="/secure" />" title="HOME">HOME</a></li>
				 <li><a href="<s:url action="Logout" namespace="/secure" />" title="ESCI">ESCI</a></li>
			</ul>
		</li>
		
<s:if test="#APP_USER.hasFunzione_visualizzazioneVisura()">
		<li>
		<a href="#" title="VISUALIZZAZIONE VISURA">VISUALIZZAZIONE VISURA</a>
		<ul class="sub_menu">
				<li><a href="visualizzazione?tipo=O" title="ORDINARIA">ORDINARIA</a></li>
				<s:if test="#APP_USER.hasFunzione_visualizzazioneVisuraStorica()">
				<li><a href="visualizzazione?tipo=S" title="STORICA">STORICA</a></li>
				</s:if>
			</ul>
		</li>
</s:if>

<s:if test="#APP_USER.hasFunzione_ricercaAnagrafica()">
		<li>
			<a href="#" title="RICERCA ANAGRAFICA">RICERCA ANAGRAFICA</a>
			<ul class="sub_menu">
				<li><a href="ricercaAnagraficaPersona" title="PERSONA">PERSONA</a></li>
				<li><a href="ricercaAnagraficaImpresa" title="IMPRESA">IMPRESA</a></li>
			</ul>
		</li>
</s:if>
		

<s:if test="#APP_USER.hasFunzione_monitoraggio()">
		<li>
			<a href="#" title="MONITORAGGIO">MONITORAGGIO</a>
			<ul class="sub_menu">
				<li><a href="monitoraggioNominativiGestione" title="GESTIONE NOMINATIVI">GESTIONE NOMINATIVI</a></li>
				<li><a href="monitoraggioNominativiVisualizzazione" title="VISUALIZZAZIONE NOMINATIVI">VISUALIZZAZIONE NOMINATIVI</a></li>
				<li><a href="monitoraggioVariazioni" title="monitoraggioVariazioni">VISUALIZZAZIONE VARIAZIONI</a></li>
			</ul>
		</li>
</s:if>


</ul>

</div>
<div class="clear"></div>


