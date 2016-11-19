jQuery(document).ready(function($) {
    $("#thermostatSlider").slider({
		tooltip: 'always'
	});
	
	fetchLatestSensorData();
	
//	$(".sensor-value").each(function() {
//		var id = $(this).attr("id");
//		sensorAjaxCall(id);
//	});
	
	$("input[data-toggle='toggle']").change(function(e, fromJs) {
		debugger;
		var id = $(this).attr("id");
		var attributeName = id.replace("Switch","");
		var state = $(this).prop("checked");
		switcherAjaxCall(attributeName, state);
	});
	
	debugger;
	var socket = new SockJS('/stomp');
	var stompClient = Stomp.over(socket);
    stompClient.connect({ }, function(frame) {
		// subscribe to the /topic/message endpoint
		stompClient.subscribe("/topic/message", function(data) {
			debugger;
			var attribute = jQuery.parseJSON( data.body );
			addLabel(attribute.name,attribute.value);
		});
    });
});

function switcherAjaxCall($attribute, $state){
	var $url = 'command';
	return $.ajax({
		  method: "POST",
	      contentType: "application/json; charset=utf-8",
		  url: $url,
		  data: '{"name":"' + $attribute+'", "value": "'+$state +'"}'
		})
		.done(function() {
			console.log("SUCCES: "+$attribute +" => "+$state);
		})
		.fail(function() {
			console.log("FAILED: "+$attribute +" => "+$state);
		});
}

function fetchLatestSensorData(){
	var $url = 'attribute/fetchLatest';
	$.ajax({
		method: "GET",
		url: $url,
		dataType: "json",
		success: function (data) {
			debugger;
			jQuery.each(data.attributeValues, function(index, attribute) {
				addLabel(attribute.name,attribute.value);
			});
			
		}
	});
}

function addLabel(id, value){
	if(id=="targetTemperature"){
		$("#thermostatSlider").slider('setValue', value);
	}
	debugger;
	if(id.includes("State")){
		$("#"+id+"Switch").prop("checked",value).change();
	}else{
		$("#"+id+"Value").text(value);
	}
	
}