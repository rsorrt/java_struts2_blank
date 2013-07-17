<%@page import="rsorrt.commons.util.UtilNet"%>
<%@page import="application.resource.AppResources"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="myTag" tagdir="/WEB-INF/tags"%>
<!-- MSG SECTION -->


<s:if test="%{isMsg}">

	<div id="dialog-msg" title="Messaggio" style="display: none;">
	
		<div style="margin:10px;">
			<a href="#" onclick="viewUlterioriInfo()" style="display: inline;"><myTag:img pathRelative="varie/info.png" /></a>
			<span id="dialog-msg_text">${msg}</span>
		</div>
		
		<div class="ulterioriInfo" style="display: none;  font-size:10px; color:gray;">
			host:<span id="dialog-msg_host"><%=UtilNet.getHostName()%></span>
		 	id:<span id="dialog-msg_id">${loggerId}</span>
		</div>
		
		<br>
		<s:if test="%{isMsgSegnalazione}">
			<div style="margin-top:10px; margin-bottom:10px; line-height: 40px;"><a href="#" id="dialog-segnalazioneLink" sourceId="dialog-msg"  style="font-weight: bold; color:red;"><myTag:img pathRelative="msg/warn.png"/>Segnala il problema</a></div>
		</s:if>
		 
		 </div>
	<script type="text/javascript">
	$(document).ready(function() {
		$('#dialog-msg').dialog({
					position: ['center','center'],
					modal: true,
					draggable: true,
					resizable:true,
					show: "blind",
					width: 650
					/*hide: "explode"*/
				});
	});
	</script>
</s:if><!-- isMsg -->

<s:if test="%{isEccezione}">
	<div id="dialog-eccezione" title="Errore" style="display: none;">
		<div style="align-text:left;margin:5px;padding:5px;">
			
			<div style="margin:10px;">
				<a href="#" onclick="viewUlterioriInfo()" style="display: inline;"><myTag:img pathRelative="varie/info.png" /></a>
				<span>Si e' verificato un errore, l'operazione non e' stata eseguita</span>
			</div>
			
			<div class="ulterioriInfo" style="display: none; font-size: 10px; color:gray; ">
				host:<span id="dialog-msg_host"><%=UtilNet.getHostName()%></span>
		 		id:<span id="dialog-eccezione_id">${loggerId}</span>
			</div>
			
			<div style="margin-top:10px; margin-bottom:10px; line-height: 40px;"><a href="#" id="dialog-segnalazioneLink" sourceId="dialog-eccezione" style="font-weight: bold; color: red;"><myTag:img pathRelative="msg/warn.png"/>Segnala il problema</a></div>
			
			<s:if test="#APP_USER.isAdmin()">
				<div style="margin-top:15px; margin-bottom:15px;padding:5px; border:1px solid #FFBF18;">
					<div style="border-bottom:1px dotted gray; width:90%;">visibile solo all'admin:</div>
					<div style="margin-top:5px;padding:5px;" id="dialog-eccezione_text">${eccezione}</div>
				</div>
			</s:if>
			<s:else>
				<div style="display:none;" id="dialog-eccezione_text">${eccezione}</div>
			</s:else>
			
			

		</div>
	</div>
<script type="text/javascript">
$(document).ready(function() {
	$('#dialog-eccezione').dialog({
		position: ['center','center'],
		modal: true,
		draggable: true,
		resizable:true,
		show: "blind",
		width: 650
	});
});
</script>
</s:if><!-- isEccezione -->


<s:if test="%{isMsgSegnalazione || isEccezione}">
	<!-- SEGNALAZIONE -->
	<div id="dialog-segnalazione" title="Invia Segnalazione di Errore" style="display: none;">
		<form action="<%=request.getContextPath()%>/secure/Segnalazione" id="form-segnalazione" method=post target="NEW">
			<input type=hidden name="host" id="form-segnalazione_host" value="<%=UtilNet.getHostName()%>">
			<input type=hidden name="id"   id="form-segnalazione_id">
			<input type=hidden name="text" id="form-segnalazione_text">
			<table class="tableInfo">
				<tr>
					<th>hostName</th>
					<td><span id="dialog-segnalazione_host" style="font-size:10px;color:gray;"><%=UtilNet.getHostName()%></span></td>
				</tr>
				<tr>
					<th>id</th>
					<td><span id="dialog-segnalazione_id" style="font-size:10px;color:gray;">${loggerId}</span></td>
				</tr>
				<tr>
					<th>Testo</th>
					<td><span id="dialog-segnalazione_text" style="font-size:10px;color:gray;"></span></td>
				</tr>
				<tr>
					<th>Informazioni aggiuntive</th><td><textarea id="dialog-segnalazione_info" name="info" rows="8" cols="50"></textarea></td>
				</tr>
			</table>
		</form>
	</div>
	
	<div id="dialog-segnalazione-result" title="Esito invio segnalazione di Errore" style="display: none;"></div>
	
	<script type="text/javascript">
	$(document).ready(function() {
	
		$("#dialog-segnalazioneLink").click(function(){
			var sourceId = $(this).attr('sourceId');
			
			$('#dialog-segnalazione_text').html($('#'+sourceId+'_text').html());
			$('#'+sourceId).dialog('close');
			
			$('#dialog-segnalazione').dialog({
				position: ['center','top'],
				width:  550, 
				height: 400,
				buttons: [
				          {text: "Invia la segnalazione",click: function() { segnalazioneInvio(); }},
				          {text: "Non inviare, chiudi finestra",click: function() { $('#dialog-segnalazione').dialog('close'); }}
				          ],
				open: function(event, ui) { 
					$('#dialog-segnalazione-result').html('');
					$('#dialog-eccezione').dialog("close"); 
				}          
			});
			
			
		});
	
		$('#dialog-segnalazione-result').dialog({
			autoOpen: false,
			bgiframe: true, 
			position: ['center','top'],
			width:  550, 
			height: 400
		});
		
	});
	function segnalazioneInvio() {
		$('#form-segnalazione_id').val($('#dialog-segnalazione_id').html());
		$('#form-segnalazione_text').val($('#dialog-segnalazione_text').html());
		
		var id = $('#form-segnalazione_id').val();
		id = id.replace("'", "");
		var host = $('#form-segnalazione_host').val();
		host = host.replace("'", "");
		var text = $('#form-segnalazione_text').val();
		text = text.replace("'", "");
		var info = $('#dialog-segnalazione_info').val();
		info = info.replace("'", "");
		
		var randomNumber = Math.floor(Math.random()*10001);
		var url2go = '<s:url action="Segnalazione" />';
		url2go = url2go + '?random=' + randomNumber;
		
		var parameters = "";
		parameters = parameters + "id=" + id;
		parameters = parameters + "&host=" + host;
		parameters = parameters + "&text=" + text;
		parameters = parameters + "&info=" + info;    
		
		$.ajax({
		  url: url2go,
		  type: 'POST',
		  data: parameters,  
		  success: function( data ) {
		  	$("#dialog-segnalazione-result").html(data);
		  	$('#dialog-segnalazione-result').dialog('open');
		    $('#dialog-segnalazione').dialog("close");
		  }
		});
	}	
	
	function viewUlterioriInfo() {
		$('.ulterioriInfo').toggle();
	}
	
	</script>
	<!-- /SEGNALAZIONE -->
</s:if>


<!-- /MSG SECTION -->