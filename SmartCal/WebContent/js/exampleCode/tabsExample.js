angular.module('ui.bootstrap.demo').controller('TabsController', function ($scope) {
  
  $scope.tab1 = {title : "My Schedule"};
  $scope.tab2 = {title : "Up-Coming Events"};
  $scope.tab3 = {title : "My Prefferences"};
  $scope.tab4 = {title : "Friend activity"};

  $scope.alertMe = function() {
    setTimeout(function() {
      alert('You\'ve selected the alert tab!');
    });
  };
});