(function(angular) {

	var services = angular.module('services', []);
	services.factory('load', function($log, $http, $q, $timeout) {
		var timeoutFlag;

		return {
			makeCall : function(destinationURL) {
				var deffered = $q.defer();
				var tmt = $timeout(function() {
					$log.info('timeout ended. starting timeout f() execution');
					if (timeoutFlag) {
						$log.info('error message changed by timeout f()');
						deffered.reject('Could not connect to server.');
						$log.info('promise rejected!');
					} else {
						$log.info('everything ok in timeout f()');
					}
				}, 11000);
				$log.info('timeout f() started counting');
				$http.get(destinationURL).success(function(data) {
					timeoutFlag = false;
					$log.info('success! the data returned is: ' + data);
					deffered.resolve(data);
				}).error(function(data) {
					$log.info('the error is: ' + data);
					$log.info('timeout activation set!')

					timeoutFlag = true;
				});
				return deffered.promise;
			}
		}
	});

}(window.angular))

//angular.isUndefined(data.instance)
//angular.isArray(returnObjet)
//angular.equals(openstackStatus,'shutoff')