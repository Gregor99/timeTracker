var date = new Date();
var startTime;
var endTime;
var weekly_progress_done = 39;
var weekly_quota = 41.25;
var weekly_progress_remaining = weekly_quota - weekly_progress_done;
var username = 3;
var data;

$(document).ready(function(){ 
	$("h1").addClass("animated bounce");

	$.get("http://localhost:8080/track/" + username, function(getResponse) {
        handleResponse(getResponse[0]);
	}, "json");

    $("#date").html(moment(date).locale('sl').format('dddd DD.MM.YYYY'));

  
	$("#toggle").click(function(){

		if(!startTime) {

			$.post("http://localhost:8080/track/" + username, function(getResponse) {
                    handleResponse(getResponse[0]);
            	}, "json");

			$("#status").html("Prisoten");
			$("#toggle").html("Odhod");
		} else if(!endTime) {

			$.post("http://localhost:8080/track/" + username, function(getResponse) {
                    handleResponse(getResponse[0]);
                }, "json");

			$("#status").html("Končal za danes");
			//console.log((endTime.getTime() - startTime.getTime())/1000);
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

function handleResponse(data) {
            if(data) {
                //TODO koda za start in end time, če jo dobimo iz baze
                console.log("date response: " + data.date);
                date = new Date(data.date[0], data.date[1], data.date[2]);
                if(null != data.timeWorkStart) {
                    console.log("timeStart response: " + data.timeWorkStart);
                    startTime = new Date();
                    startTime.setHours(data.timeWorkStart[3]);
                    startTime.setMinutes(data.timeWorkStart[4]);
                    $("#startTime").html("Prihod ob " + formatHoursMinutes(startTime.getHours(), startTime.getMinutes()));
                    $("#status").html("Prisoten");
                    $("#toggle").html("Odhod");
                }
                if(null != data.timeWorkEnd) {
                    console.log("timeEnd response: " + data.timeWorkEnd);
                    endTime = new Date();
                    endTime.setHours(data.timeWorkEnd[3]);
                    endTime.setMinutes(data.timeWorkEnd[4]);
                    $("#endTime").html("Odhod ob " + formatHoursMinutes(endTime.getHours(), endTime.getMinutes()));
                    $("#status").html("Končal za danes");
                }
            }

}



