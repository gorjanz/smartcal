// setup global variables used for communication between angular fetched data and calendar data
var mainCalendarEvents = [];

var newEventStartTime = null;
var newEventEndTime = null;
var newEventCategory = null;

//jQuery calendar code
$(document).ready(function() {

	$('#newEventCategory li').on('click', function(){
		newEventCategory = $(this).text();
		console.log(newEventCategory);
		
		$('#newEventCategory').text(newEventCategory);
		
	});
	
	$('#addNewEventConfirmationButton').on('click', function(){
		var newEvent = {
				title: $('#newEventTitle').val(),
				description : $('#newEventDescription').val(),
				url: $('#newEventUrl').val(),
				start: newEventStartTime,
				startTime: newEventStartTime,
				end: newEventEndTime,
				endTime: newEventEndTime,
				category: newEventCategory
		}
		
		alert("new event: " + newEvent.start + " : " + newEvent.end);
		console.log("new event: " + newEvent);
		
		$('#calendar').fullCalendar('renderEvent', newEvent , true);
		$('#calendar').fullCalendar('unselect');
		
		$('#addNewEventModal').modal('hide');
	});
		
	$('#calendar').fullCalendar({
		// put your options and callbacks here

		events : mainCalendarEvents,
	    		
		fixedWeekCount: true,
		firstDay: 1,
		
	//	allDaySlot: false,
		allDayDefault: false,
		
		header : {
			left:   'title',
		    center: 'month,agendaWeek,agendaDay',
		    right:  'today prev,next'
		},
		
		selectable: true,

		// callback function executed each time a day is clicked in the calendar
//		dayClick: function(date, jsEvent, view) {
//		
//		},
	      	    
		eventRender: function (event, element) {
			
            element.attr('href', 'javascript:void(0);');
            element.click(function() {
                //set the modal values and open
                $("#startTime").html(moment(event.start).format('MMM Do h:mm A'));
                $("#endTime").html(moment(event.end).format('MMM Do h:mm A'));
                $('#modalTitle').html(event.title);
                $('#modalBody').html(event.description);
                $('#eventUrl').attr('href',event.url);
                $('#fullCalModal').modal();
            });
        },
		
	    select: function(start, end, allDay) {

	    	newEventStartTime = start;
	    	newEventEndTime = end;
	    	
	    	console.log("start: " + newEventStartTime.format("dddd, MMMM Do YYYY, h:mm:ss a"));
	    	console.log("end: " + newEventEndTime.format("dddd, MMMM Do YYYY, h:mm:ss a"));
	    	
	    	$('#addNewEventModal').modal();
	    	

	            /**
	             * ajax call to store event in DB
	             */
//	            jQuery.post(
//	                "event/new" // your url
//	                , { // re-use event's data
//	                    title: title,
//	                    start: start,
//	                    end: end,
//	                    allDay: allDay
//	                }
//	            );
	    	$('#calendar').fullCalendar('unselect');
	    },

		
		dayRender : function(date, cell) {
			
			// set the color for all days
			cell.css("background-color", "#f5f5f5");
			
			var now = moment();
			
			// override that color for all days but the current one
			if (date.isAfter(now) || date.isBefore(now)){
				cell.css("background-color", "#FF8330");
			}
			
		}

	});

})

// angular main module declaration and initialization
var mymodule = angular.module('smartcal', ['ui.bootstrap', 'factories', 'services']);
mymodule.run(function (EventsData, $log, $timeout){
	$timeout(function(){
		EventsData.init();
	}, 3000);
	
	$log.info(EventsData.getEvents());
});


mymodule.filter('showByDate', function() {
	  return function (events, selectedDate) {

		    var filtered_list = [];

		    var selectedTime = selectedDate.getTime();
		    
		    for (var i = 0; i < events.length; i++) {

		      if (events[i].startTime >= selectedTime) {
		        filtered_list.push(events[i]);
		      }
		    }
		    return filtered_list;
		  }
		});

// angular controller for the tabs component
mymodule.controller('TabsController', function($scope, $log, EventsData, $timeout, $http, load) {
  
	//global data variables
	$scope.friends = [];
	$scope.events = [];
	
	// set tabs titles
	$scope.tabs = [
	               { title : "My Schedule" },
	               { title : "Up-Coming Events" },
	               { title : "My Prefferences" },
	               { title : "Friend activity"
	}]	

//***************************** tab1 settings ********************************
	
	
	
	$scope.events = [];
	
	load.makeCall('/smartcal/events').then(function(data) {
		$scope.events = data;
		$log.info($scope.events);
		var events = [];
		for(var i = 0; i<$scope.events.length; i++){
			$log.info($scope.events[i].startTime);
			var event = {
				id : $scope.events[i].eventId,
				title: $scope.events[i].title,
				start: (2*60*60*1000) + $scope.events[i].startTime, //+2hours cause of default locale
				end: (2*60*60*1000) + $scope.events[i].endTime,
				url: $scope.events[i].url,
				category: $scope.events[i].category,
				color: '#00FF00' // defaults to "sports" category - green color
			}
			// set event color to red if it is an event from the "nightlife" category
			if(event.category == 'nightlife'){
				event.color = '#FF0000';
			}
			// set event color to blue if it is an event from the "education" category
			if(event.category == 'education'){
				event.color = '#0000FF';
			}

			$log.info(event);
			events.push(event);
		}
		$log.info(events);
		mainCalendarEvents = events;
		$('#calendar').fullCalendar( 'removeEvents', events );
		$('#calendar').fullCalendar( 'addEventSource', events );
		$('#calendar').fullCalendar('rerenderEvents');
	}, function(data){
		$log.info('The error data is: ' + data);
	});
	
	
	
//***************************** tab2 settings ********************************
	
	// date-picker options and settings
	$scope.today = function() {
		$scope.dt = new Date();
	};
	$scope.today();

	$scope.clear = function() {
		$scope.dt = null;
	};

	// Disable weekend selection
	$scope.disabled = function(date, mode) {
		return (mode === 'day' && (date.getDay() === 0 || date.getDay() === 6));
	};

	$scope.minDate = new Date("2000-01-01");
	
	$scope.open = function($event) {
		$event.preventDefault();
		$event.stopPropagation();

		$scope.opened = true;
	};

	$scope.dateOptions = {
		formatYear : 'yy',
		startingDay : 1
	};

	$scope.formats = [ 'dd-MMMM-yyyy', 'yyyy/MM/dd','dd.MM.yyyy', 'shortDate' ];
	$scope.format = $scope.formats[1];
	

//***************************** tab3 settings ********************************
	
	// drop-down options
  	$scope.items = [ 'One per week', 'Two per week',
			'Three per week' ];

	$scope.status = {
		isopen : false
	};

	$scope.toggled = function(open) {
		console.log('Dropdown is now: ', open);
	};

	$scope.toggleDropdown = function($event) {
		$event.preventDefault();
		$event.stopPropagation();
		$scope.status.isopen = !$scope.status.isopen;
	};

//***************************** tab4 settings ********************************
	
	// accordion settings
	$scope.oneAtATime = true;

	$scope.friends = [];
	$scope.friendEvents = [];
	var addFriendEvents = function(data){
		$scope.friendEvents.push(data);
		$scope.friends.sort(function(friend1,friend2){
			if(friend1.userId < friend2.userId)
				return -1;
			if(friend1.userId < friend2.userId)
				return 1;
			return 0;
		});
		$scope.friendEvents.sort(function(events1,events2){
			if(events1.friendId < events2.friendId)
				return -1;
			if(events1.friendId < events2.friendId)
				return 1;
			return 0;
		});
	}
	
	load.makeCall('/smartcal/users/1/friends').then(function(data) {
		$scope.friends = data; 
		$log.info("fetched friends");
		$log.info($scope.friends);
		for (var int = 0; int < $scope.friends.length; int++) {
			$scope.getEventsForFriend($scope.friends[int].userId);
		}
	}, function(data){
		$log.info('The error data is: ' + data);
	});
	

	$scope.getEventsForFriend = function(friendId){
		load.makeCall("/smartcal/users/"+ friendId +"/past").then(function(data) {
			$log.info(data);
			var eventWithFriendIdAttached = {
					events: data,
					id: friendId
			};
			addFriendEvents(eventWithFriendIdAttached);
		}, function(data) {
			$log.info('The error data is: ' + data);
		});
		
	}
	
});