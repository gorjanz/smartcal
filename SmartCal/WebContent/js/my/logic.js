// setup global variables used for communication between angular fetched data and calendar data
var mainCalendarEvents = [];

//jQuery calendar code
$(document).ready(function() {

	$('#calendar').fullCalendar({
		// put your options and callbacks here

		events : mainCalendarEvents,
		
		fixedWeekCount: true,
		firstDay: 1,
		
		header : {
			left:   'title',
		    center: 'month,agendaWeek,agendaDay',
		    right:  'today prev,next'
		},
		
		dayRender : function(date, cell) {
			
			// set the color for all days
			cell.css("background-color", "#f5f5f5");
			
			var now = moment();
			
			// override that color for all days but the current one
			if (date.isAfter(now) || date.isBefore(now)){
				cell.css("background-color", "#FF8330");
			}
			
		},

		// callback function executed each time a day is clicked in the calendar
		dayClick : function(date) {
			alert(data + "a day has been clicked!" + date.format('YYYY MM DD'));
		}

	});

})

// angular main module declaration and initialization
var mymodule = angular.module('ui.bootstrap.demo', ['ui.bootstrap', 'factories', 'services']);
mymodule.run(function (EventsData, $log, $timeout){
	$timeout(function(){
		EventsData.init();
	}, 3000);
	
	$log.info(EventsData.getEvents());
});

// angular controller for the tabs component
mymodule.controller('TabsController', function($scope, $log, EventsData, $timeout, $http) {
  
	//global data variables
	$scope.friends = [];
	$scope.events = [];
	
	// set tabs titles
	$scope.tab1 = {
		title : "My Schedule"
	};
	$scope.tab2 = {
		title : "Up-Coming Events"
	};
	$scope.tab3 = {
		title : "My Prefferences"
	};
	$scope.tab4 = {
		title : "Friend activity"
	};	

//***************************** tab1 settings ********************************
	
	
	
	$scope.events = [];
	
	$http.get('/smartcal/events').success(function(data, status, headers, config) {
		$scope.events = data; 
		$log.info($scope.events);
		var events = [];
		for(e in $scope.events){
			var event = {
				title: e.title,
				start: e.startTime,
				end: e.endTime,
				url: e.url
			}
			events.push(event);
		}
		$log.info(events);
		mainCalendarEvents = events;
		$('#calendar').fullCalendar( 'rerenderEvents' );
    }).error(function(data, status) {
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

	$scope.toggleMin = function() {
		$scope.minDate = $scope.minDate ? null : new Date();
	};
	
	$scope.toggleMin();
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
  	$scope.items = [ 'One time per week', 'Two times per week',
			'Three times per week' ];

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
	
	$http.get('/smartcal/users/1/friends').success(function(data, status, headers, config) {
		$scope.friends = data; 
		$log.info($scope.friends);
//		for (var int = 0; int < $scope.friends.length; int++) {
//			$http.get("/smartcal/users/"+ $scope.friends[int].userId +"/past").success(function(data, status, headers, config) {
//				$log.info($scope.friends[int].userId);
//				$scope.friends[int].userId.events = [];
//				$scope.friends[int].userId.events = data;
//				$log.info($scope.friends[int].userId.events);
//			}).error(function(data, status) {
//		        $log.info('The error data is: ' + data);
//		    });
//		}
    }).error(function(data, status) {
        $log.info('The error data is: ' + data);
    });
	
});