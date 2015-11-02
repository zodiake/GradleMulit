var userModule = angular.module('User', []);

userModule.service('CommonUserService', ['$http',
    function ($http) {

        function transform(obj) {
            var str = [];
            for (var p in obj)
                str.push(encodeURIComponent(p) + "=" + encodeURIComponent(obj[p]));
            return str.join("&");
        }

        var header = {
            'Content-Type': 'application/x-www-form-urlencoded'
        };

        this.findAll = function (opt) {
            return $http.get('/admin/CommonUsers', {
                params: opt
            });
        };

        this.findOne = function (item) {
            return $http.get('/admin/CommonUsers/' + item.id);
        };

        this.updateScore = function (item) {
            return $http({
                method: 'POST',
                url: '/admin/CommonUsers/' + item.id + '/score',
                transformRequest: transform,
                data: {
                    score: item.score,
                },
                headers: header
            });
        };
    }
]);

userModule.service('ProviderService', ['$http',
    function ($http) {
        this.findAll = function (opt) {
            return $http.get('/admin/providers', {
                params: opt
            });
        };
        this.findOne = function (id) {
            return $http.get('/admin/providers/' + id);
        };
        this.authenticate = function (item) {
            return $http.put('/admin/providers/' + item.id + '/authority');
        };
    }
]);

userModule.controller('CommonUserController', ['$scope',
    'CommonUserService',
    '$modal',
    function ($scope, CommonUserService, $modal) {
        $scope.page = 1;
        $scope.size = 15;

        function init(opt) {
            CommonUserService.findAll(opt).success(function (data) {
                $scope.items = data.content;
                $scope.total = data.totalElements;
            }).error(function (err) {

            });
        }

        init({
            page: $scope.page,
            size: $scope.size
        });

        $scope.seach = function () {
            init({
                page: $scope.page,
                size: $scope.size
            });
        };

        $scope.showDetail = function (item) {
            CommonUserService
                .findOne(item)
                .success(function (data) {
                    $modal.open({
                        templateUrl: '/admin/commonUserDetail',
                        size: 'sm',
                        controller: 'CommonUserDetailController',
                        scope: $scope,
                        resolve: {
                            item: function () {
                                return data;
                            }
                        }
                    });
                }).error(function (err) {

                });
        };

        $scope.$on('success', function () {
            console.log(22);
        });
    }
]);

userModule.controller('ProviderController', ['$scope',
    'ProviderService',
    '$modal',
    function ($scope, ProviderService, $modal) {
        $scope.page = 1;
        $scope.size = 15;

        function init(opt) {
            ProviderService.findAll(opt).success(function (data) {
                $scope.items = data.content;
                $scope.total = data.totalElements;
            }).error(function (err) {

            });
        }

        init({
            page: $scope.page,
            size: $scope.size,
            auth: $scope.auth
        });

        $scope.search = function () {
            init({
                page: $scope.page,
                size: $scope.size,
                auth: $scope.auth
            });
        };

        $scope.showDetail = function (item) {
            ProviderService
                .findOne(item.id)
                .success(function (data) {
                    $modal.open({
                        templateUrl: '/admin/providerDetail',
                        controller: 'ProviderDetailController',
                        size: 'lg',
                        scope: $scope,
                        resolve: {
                            item: function () {
                                return data;
                            }
                        }
                    });
                })
                .error(function (err) {

                });
        };
    }
]);

userModule.controller('CommonUserDetailController', ['$scope',
    'item',
    'CommonUserService','$modalInstance',
    function ($scope, item, CommonUserService,$modalInstance) {
        $scope.item = item;
        $scope.alerts = [];
        
        $scope.closeModal = function(){
        	$modalInstance.dismiss();
        };

        $scope.updateScore = function (item) {
            CommonUserService
                .updateScore(item)
                .success(function () {
                    $scope.$emit('success', item);
                    $scope.alerts.push({
                        type: 'success',
                        msg: '保存成功',
                    });
                })
                .error(function () {
                    $scope.alerts.push({
                        msg: '保存失败',
                    });
                });
        };
        
        $scope.closeAlert = function (index) {
            $scope.alerts.splice(index, 1);
        };
    }
]);

userModule.controller('ProviderDetailController', ['$scope',
    'item',
    'ProviderService','$http','$modalInstance',
    function ($scope, item, ProviderService,$http,$modalInstance) {
        $scope.item = item;
        $scope.alerts = [];

        $scope.authenticate = function (item) {
            ProviderService
                .authenticate(item)
                .success(function (data) {
                    $scope.alerts.push({
                        type: 'success',
                        msg: '保存成功',
                    });
                })
                .error(function (err) {
                    $scope.alerts.push({
                        msg: '保存失败',
                    });
                });
        };

        $scope.closeAlert = function (index) {
            $scope.alerts.splice(index, 1);
        };
        
        $scope.closeModal = function(){
        	$modalInstance.dismiss();
        }
        
        $scope.batchProducts = function(event){
            var file = event.target.files[0];
            console.log(file);
            if(file.size<1048576){
	            var fd = new FormData();
	            var reader = new FileReader();
	            var providerId = event.target.id;
	            
	            fd.append('file', file);
	            
	            $http.post('/admin/providers/'+providerId+'/products?batch', fd, {
	                withCredentials: true,
	                headers: {
	                    'Content-Type': undefined
	                },
	                transformRequest: angular.identity
	            }).success(function (data) {
	            	console.log(data);
	            	$scope.alerts.push({
	            		type: 'success',
	                    msg: data,
	                });
	            });
            }else{
            	$scope.alerts.push({
                    msg: "文件过大",
                });
            }
        };
    }
]);
