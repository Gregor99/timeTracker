var date = new Date();
var startTime;
var endTime;
var weekly_progress_done = 39;
var weekly_quota = 41.25;
var weekly_progress_remaining = weekly_quota - weekly_progress_done;
var username = 1;
var data;

$(document).ready(function(){ 
	$("h1").addClass("animated bounce"); 
	$("#date").html(moment(date).locale('sl').format('dddd DD.MM.YYYY'));

	$.get("http://localhost:8080/track/" + username, function(response) {
	    data = response[0];
	    startTime = response[0].timeWorkStart;
	    console.log("data: " + response[0].timeWorkStart);
	}, "json");


  
	$("#toggle").click(function(){

		if(!startTime) {
			startTime = new Date();
			$("#startTime").html("Prihod ob " + formatHoursMinutes(startTime.getHours(), startTime.getMinutes()));
			$("#status").html("Prisoten");
			$("#toggle").html("Odhod");
		} else {
			endTime = new Date();
			$("#endTime").html("Odhod ob " + formatHoursMinutes(endTime.getHours(), endTime.getMinutes()));
			$("#status").html("Končal za danes");
			console.log((endTime.getTime() - startTime.getTime())/1000);
		}
	}); 

	$("#progress_bar_done").css("width", ((100 * weekly_progress_done/weekly_quota) + "%"));
	$("#progress_bar_remaining").css("width", ((100 * weekly_progress_remaining/weekly_quota) + "%"));

	console.log(moment().locale());
});

function updateClock()
{
  var currentTime = new Date ( );

  var currentHours = currentTime.getHours ( );
  var currentMinutes = currentTime.getMinutes ( );
  var currentSeconds = currentTime.getSeconds ( );

  // Pad the minutes and seconds with leading zeros, if required
  currentMinutes = ( currentMinutes < 10 ? "0" : "" ) + currentMinutes;
  currentSeconds = ( currentSeconds < 10 ? "0" : "" ) + currentSeconds;

  // Choose either "AM" or "PM" as appropriate
  var timeOfDay = ( currentHours < 12 ) ? "AM" : "PM";

  // Compose the string for display
  var currentTimeString = currentHours + ":" + currentMinutes + ":" + currentSeconds;// + " " + timeOfDay;

  // Update the time display
  document.getElementById("clock").firstChild.nodeValue = currentTimeString;
}

function formatHoursMinutes(hours, minutes) {
	
	var formatedHours = ( hours < 10 ? "0" : "" ) + hours;
	var formatedMinutes = ( minutes < 10 ? "0" : "" ) + minutes;
	
	return formatedHours + ":" + formatedMinutes;
}



