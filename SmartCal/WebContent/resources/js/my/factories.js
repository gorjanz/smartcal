(function(angular) {

    var factories = angular.module('factories', ['services']);

    factories.factory('EventsData', function($http, $log){
    	var events;
    	
    	return {
    		init : function(load){
    			if (!angular.isUndefined(load)) {
                    events = load.events;
                    return;
                }
    			$http.get('/smartcal/events').success(function(data, status, headers, config) {
                    events = data;
                }).error(function(data, status) {
                        $log.info('The error data is: ' + data);
                });
    		},
    		getEvents : function(){
    			return events;
    		}
    	}
    });
    
    
    factories.factory('ImagesData', function($http, $log) {
        var images;
        return {
            init : function(load) {
                if (!angular.isUndefined(load)) {
                    images = load.instance;
                    return;
                }
                $http.get('rest/images').success(function(data, status, headers, config) {
                    images = data.instance;
                }).error(function(data, status) {
                        $log.info('The error data is: ' + data);
                    });
            },
            getImages : function() {
                return images;
            }
        }
    });
    factories.factory('ServersData', function($http, $log) {
        var servers;
        return {
            init : function(load) {
                if (!angular.isUndefined(load)) {
                    servers = load.instance;
                    return;
                }
                $http.get('rest/servers').success(function(data, status, headers, config) {
                    servers = data.instance;
                }).error(function(data, status) {
                        $log.info('The error data is: ' + data);
                    });
                angular.forEach(servers, function (server) {
                  server.spinnerView = false;
                  server.serverActionsDisabled = false;
                  server.serverDetailsDisabled = false;
                  server.showStatus = false;
                });
            },
            applyServer: function (data) {
                // data holds info for only one server
                // we should iterate through the servers and find the right one to refresh
                $log.info(data);
                var serverId = data.id;
                var server = this.getServer(serverId);
                server.status = data.status;
            },
            getServer: function (id) {
                var returnServer;
                angular.forEach(servers, function (server) {
                    if (angular.equals(id, server.id)) {
                        returnServer =  server;
                    }
                });
                return returnServer;
            },
            prepareServerAction: function (id) {
                var server = this.getServer(id);
                server.showStatus = true;
                this.setDetailsEnabled(id,true);
                this.setActionsEnabled(id,true);
                this.setSpinnerView(id, true);
            },
            postServerAction: function (id) {
                var server = this.getServer(id);
                server.showStatus = false;
                this.setDetailsEnabled(id,false);
                this.setActionsEnabled(id,false);
                this.setSpinnerView(id, false);
            },
            getServers : function() {
                return servers;
            },
            setServers : function(newState) {
                servers = newState;
            },
            setSpinnerView : function(id, value) {
              var server = this.getServer(id);
              server.spinnerView = value;
            },
            setDetailsEnabled : function(id, value) {
              var server = this.getServer(id);
              server.serverDetailsDisabled = value;
            },
            setActionsEnabled : function(id, value) {
              var server = this.getServer(id);
              server.serverActionsDisabled = value;
            }
        }
    });

    
    factories.factory('ProjectData', function($http, $log) {
        var userData;
        var projectData;
        return {
            initProject: function (load) {
                if (!angular.isUndefined(load)) {
                    projectData = load;
                    return;
                }
            },
            initUser: function (load) {
                if (!angular.isUndefined(load)) {
                    userData = load;
                    return;
                }
            },
            init : function() {
                $http.get('rest/user').success(function(data, status, headers, config) {
                    userData = data;
                }).error(function(data, status) {
                        $log.info('The error data is: ' + data);
                    });
                $http.get('rest/project').success(function(data, status, headers, config) {
                    projectData = data;
                }).error(function(data, status) {
                        $log.info('The error data is: ' + data);
                    });
            },
            getUserData : function() {
                return userData;
            },
            getProjectData : function() {
                return projectData;
            }
        }
    });
    
    factories.factory('QuotaData',function($http,$log){
      var quotaData = {maxRam: '', maxInstances: '', maxCores: ''};
      return{
        init: function(){
          $http.get('rest/limits').success(function(data,status,headers,config){
            quotaData=data.instance[0];
          }).error(function(data,status){
            $log.info('The error data is : '+ quotaData);
          });
        },
        getQuotaData : function(){
          return quotaData;
        },
        getMaxRam: function () {
          return quotaData.maxRam;
        },
        getMaxInstances: function () {
          return quotaData.maxInstances;
        },
        getMaxCores: function () {
          return quotaData.maxCores;
        }
      }
    });
    
    factories.factory('AllResources', function($http, ServersData, ImagesData, FlavorsData, ProjectData, QuotaData, DescriptiveUnits, $log) {
      var startUp;
      return {
        init: function() { 
          startUp=true;
          $http.get('rest/resources').success(function(data, status, headers, config) {
            ProjectData.initUser(data.userData);
            ProjectData.initProject(data.projectData);
            FlavorsData.init(data.flavorsData);
            ServersData.init(data.serversData);
            ImagesData.init(data.imagesData);
            DescriptiveUnits.init(data.unitsData);
            QuotaData.init();
            startUp=false;
          }).error(function(data, status) {
            $log.info('The error data is: ' + data);
          });
        },
        getUserData : function() {
          return ProjectData.getUserData();
        },
        getProjectData : function() {
          return ProjectData.getProjectData();
        },
        getFlavors : function() {
          return FlavorsData.getFlavors();
        },
        getServers: function() {
          return ServersData.getServers();
        },
        getImages : function() {
          return ImagesData.getImages();
        },
        getStartUp : function() {
          return startUp;
        },
        getQuota : function(){
          return QuotaData.getQuotaData();
        }
      }
    });
}(window.angular))