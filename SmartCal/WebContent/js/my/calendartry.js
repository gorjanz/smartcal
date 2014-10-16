/**
 * 
 */

$(document).ready(function() {

	$('#calendar').fullCalendar({
		// put your options and callbacks here

		events : [ {
			title : 'event1',
			start : '2014-10-11'
		}, {
			title : 'event2',
			start : '2014-10-11',
			end : '2014-10-11'
		}, {
			title : 'event3',
			start : '2014-11-11T12:30:00',
			allDay : false
		// will make the time show
		} ],
		
		fixedWeekCount: true,
		firstDay: 1,
		
		header : {
			left:   'title',
		    center: 'month,agendaWeek,agendaDay',
		    right:  'today prev,next'
		},
		
		dayRender : function(date, cell) {
			
			cell.css("background-color", "#f5f5f5");
			
			var now = moment();
			
			if (date.isAfter(now) || date.isBefore(now)){
				cell.css("background-color", "#FF8330");
			}
			
		},

		dayClick : function() {
			alert("a day has been clicked!");
		}

	});

})