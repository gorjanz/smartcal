(function(angular) {

    var services = angular.module('services', []);
    services.factory('load', function ($log, $http, $q, $timeout) {
      var timeoutFlag;
 
      return {
        makeCall : function(destinationURL) {
          var deffered = $q.defer();
          var tmt = $timeout( function(){
            $log.info('timeout ended. starting timeout f() execution');
            if(timeoutFlag){
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
            var returnObjet, openstackStatus;
            if (angular.isUndefined(data.instance)) {
                returnObjet = data;
            } else {
                returnObjet = data.instance;
            }

            if (angular.isArray(returnObjet)) {
              $log.info(angular.isUndefined(data.instance[0]));
              if(angular.isUndefined(data.instance[0])){
                data.message = 'An error occured. Undefined returned object.';
                data.success=false;
              } else {
                openstackStatus = angular.lowercase(data.instance[0].status);
                if(angular.equals(openstackStatus,'build')){
                    openstackStatus= 'created';
                }
                if(angular.equals(openstackStatus,'reboot')){
                    openstackStatus= 'rebooted';
                }
                if(angular.equals(openstackStatus,'shutoff')){
                    openstackStatus= 'stopped';
                }

                if(angular.equals(data.responseCode,404)){
                  data.message='Server not found.';
                  data.success=false;
                } else if(angular.equals(data.responseCode,409)){
                  data.message='Server is '+openstackStatus+', unable to process request.';
                  data.success=false;
                } else if(angular.equals(data.responseCode,400)){
                  data.message='Server is '+openstackStatus+', unable to process request.';
                  data.success=false;
                } else if(angular.equals(data.responseCode,202)){
                  data.message='Server with id : '+data.instance[0].id+' is '+openstackStatus+'.';
                  data.success=true;
                } else if(angular.equals(data.responseCode,200)){
                  data.message = 'Action completed successfully.';
                  data.success=true;
                } else if(angular.equals(data.responseCode,500)){
                  data.message = 'Error when executing request on openstack.';
                  data.success=false;
                } 
                else {
                  data.message = 'An error occured. Unkwnown error status.';
                  data.success=false;
                }
              }
            }
            deffered.resolve(data);
          }).error(function (data) {
            $log.info('the error is: ' + data);
            $log.info('timeout activation set!')
            
            timeoutFlag = true;
          });
          return deffered.promise;
        }
      }
    });
    
}(window.angular))