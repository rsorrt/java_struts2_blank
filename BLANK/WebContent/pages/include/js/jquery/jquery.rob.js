

function showPdf(){
	
	var width = 900;
	var height = 600;
	var left = (screen.width/2)-(width/2);
	var top  = (screen.height/2)-(height/2) - 60;
	var position = ' top=' + top + ',left=' + left;
	
	var parameters = 'height=600,width=900';
	parameters    += ',directories=no,location=no,menubar=no,scrollbars=yes,status=no,toolbar=no,resizable=yes';
	parameters    += ',' + position ;
	
	popup = window.open('','popupPdf',parameters);
	popup.document.title = "Creazione Pdf in corso..."; 
	
	document.getElementById('formPdf').submit();
}


var msgWaiting = '<div id="progressBox"><div id="progressImg"></div></div>';


function blockUI() {
	$.blockUI({ message: msgWaiting });
}


function blockUI() {
	$.blockUI();
}

function unblockUI() {
	$.unblockUI();
}
	

function submitAndWait(form) {
	wait();
	form.submit();
}

	
function wait() {
	// SE NON USO IL TAG IMG BLOCKUI NON VISUALIZZA IMMAGINI CON IE6 / IE8 COMPATIBILITA
	$.blockUI({
		message: '<div id="progressImg"><img src="/vcweb/pages/img/progress/progress.gif" /></div>',
		css: { 
		        padding:        0, 
		        margin:         0, 
		        width:          '200px',
		        height:         '170px',
		        top:            '35%', 
		        left:           '37%', 
		        textAlign:      'center', 
		        color:          '#ffffff', 
		        border:         '5px solid  #5A7894', 
		        backgroundColor:'#fff', 
		        cursor:         'wait' 
		}
	});

}

function submit(form) {
	form.submit();
}


		
// AJAX CALL
/*
 $(document).ajaxStart(function() {
	 //$.blockUI({ message: msgWaiting });
     $.blockUI({ message: '<h1><img src="../images/layout/busy.gif" /> Just a moment...</h1>' });  
  });
 $(document).ajaxStop($.unblockUI);    
  
function ajax(indicatorId, divId, url, data) {
	
	//$("#stringsToCheck").html(indicator);
	$('#' + indicatorId).show(); 
	
		$.ajax({   
		type: "POST",   
		url: url,   
		data: data,   
		dataType: "html",   
		success: function(html){
					//alert(html);
					$('#' + indicatorId).hide();		
					$('#' + divId).html(html); 
				 },
			error:	 function (xhr, ajaxOptions, thrownError){
	               alert(xhr.status);
	               alert(thrownError);
	             }    
		}); 
			
}  // stringsToCheck
*/	
	

//$(document).ready(function() {
$('.toggle').next().hide();
// funzione toggle
$('.toggle').click( function() {
	$(this).next().toggle('slow');
	return false;
});

// rende il campo di input editabile solo con numeri
$("input.inputNumerico").keypress(function(event) {
	var code = event.charCode;
	var isNotNumeric = code && (code < 48 || code > 57);
	if (isNotNumeric) {
		event.preventDefault();
	} // if
});

function inputOnlyNumbers(event) {
	// Allow only backspace and delete
    if ( event.keyCode == 46 || event.keyCode == 8 ) {
        // let it happen, don't do anything
    }
    else {
    	// Ensure that it is a number and stop the keypress
        if ((event.keyCode < 48 || event.keyCode > 57) && (event.keyCode < 96 || event.keyCode > 105 )) {
        	event.preventDefault(); 
        	alert("Sono ammessi solo numeri");
        } //if
    } //if
} //inputOnlyNumbers
	

// chiama una funzione javascript usando il suo nome
function callFunctionByName(functionName) {
	var functionToCall = window[functionName];
	if (functionToCall == null) {
		alert("La funzione " + functionName + " non esiste");
	} else {
		window[functionName]();
	}
} //callFunctionByName



// trim 
//--------------------------------------
function trimByClass(className) {
$('.' + className).each( 
		function() {
	    	var obj = $(this);
	    	var trimmedVal = $.trim(obj.val());
	    	obj.val(trimmedVal);
		});
} //trimByClass
 
