var brandModule = angular.module('Brand', []);
var validFailMessage = '校验失败';

brandModule.service('BrandService', ['$http',
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
            return $http.get('/admin/brands', {
                params: opt
            });
        };

        this.delete = function (item) {
            var state = item.state == 'ACTIVATE' ? 'DEACTIVATE' : 'ACTIVATE';
            return $http({
                method: 'POST',
                url: '/admin/brands/' + item.id + '/activate',
                transformRequest: transform,
                data: {
                    state: state
                },
                headers: header
            });
        };

        this.save = function (item) {
            return $http({
                method: 'POST',
                url: '/admin/brands?form',
                transformRequest: transform,
                data: {
                    name: item.title,
                    coverImg: item.cover,
                    href: item.href
                },
                headers: header
            });
        };

        this.update = function (item) {
            return $http({
                method: 'POST',
                url: '/admin/brands/' + item.id,
                transformRequest: transform,
                data: {
                    name: item.title,
                    coverImg: item.cover,
                    href: item.href
                },
                headers: header
            });
        };

        this.saveOrUpdate = function (item) {
            if (item.id)
                return this.update(item);
            else
                return this.save(item);
        };
        
        this.findByShowOnIndex = function(opt){
        	return $http.get('/admin/brands/showOnIndex');
        };
        
        this.showOrHide = function(item){
        	return $http({
                method: 'POST',
                url: '/admin/brands/' + item.id + '/showOnIndex',
                transformRequest: transform,
                headers: header
            });
        }
    }
]);

brandModule.controller('BrandController', ['$scope', '$modal', 'BrandService',
    function ($scope, $modal, BrandService) {
        $scope.page = 1;
        $scope.size = 15;

        $scope.create = function () {
            $modal.open({
                templateUrl: '/admin/brandDetail',
                size: 'sm',
                controller: 'BrandAddController'
            });
        };
        
        $scope.showOnIndex = function(){
        	BrandService.findByShowOnIndex().success(function(data){
        		$scope.items = data;
        		$scope.total = 1;
        	}).error(function(data){
        		
        	});
        }

        $scope.view = function (item) {
            $modal.open({
                templateUrl: '/admin/brandDetail',
                size: 'sm',
                controller: 'BrandViewController',
                resolve: {
                    item: function () {
                        return item;
                    }
                }
            });
        };
        
        function init(opt) {
            BrandService.findAll(opt).success(function (data) {
                $scope.items = data.content;
                $scope.total = data.totalElements;
            }).error(function (err) {

            });
        }

        init({
            page: $scope.page,
            size: $scope.size
        });

        $scope.delete = function (item) {
            var modalInstance = $modal.open({
                animation: $scope.animationsEnabled,
                templateUrl: 'brandModal.html',
                controller: 'BrandModalCtrl',
                resolve: {
                    item: function () {
                        return item;
                    }
                }
            });
        };

        $scope.search = function (current) {
            init({
                page: current,
                size: $scope.size,
            });
        };
        $scope.alerts=[];
        $scope.showOrHide = function(item){
         	BrandService.showOrHide(item).success(function(data){
         		console.log(data);
         		if(data=="success"){
         			if(item.show=="DEACTIVATE")
       				 item.show = "ACTIVATE";
         			else
       				 item.show="DEACTIVATE";
         			$scope.alerts.push({
         				type: 'success',
                        msg: '操作成功',
                    });
         		}
         	}).error(function(data){
         		
         	});
         };
         $scope.closeAlert = function (index) {
             $scope.alerts.splice(index, 1);
         };
    }
]);

brandModule.controller('BrandModalCtrl', [
    '$scope',
    '$modalInstance',
    'item',
    'BrandService',
    function ($scope, $modalInstance, item, BrandService) {
        $scope.item = item;
        $scope.ok = function () {
            BrandService
                .delete(item)
                .success(function (data) {
                    if (data.data == 'success') {
                        item.state = item.state == 'ACTIVATE' ? 'DEACTIVATE' : 'ACTIVATE';
                        $modalInstance.dismiss();
                    }
                }).error(function (err) {

                });
        };

        $scope.cancel = function () {
            $modalInstance.dismiss();
        };
    }
]);

brandModule.controller('BrandAddController', ['$scope',
    '$http',
    'BrandService','$modalInstance',
    function ($scope, $http, BrandService,$modalInstance) {
        $scope.item = {};
        $scope.alerts = [];

        $scope.upload = function (event) {
            var file = event.target.files[0];
            var fd = new FormData();
            var reader = new FileReader();

            fd.append('file', file);

            $http.post('/admin/img/upload', fd, {
                withCredentials: true,
                headers: {
                    'Content-Type': undefined
                },
                transformRequest: angular.identity
            }).success(function (data) {
                $scope.item.cover = data[0];
            });
        };
        
        $scope.closeModal = function(){
        	$modalInstance.dismiss();
        };

        $scope.submit = function () {
            if ($scope.brandForm.$valid) {
                BrandService
                    .saveOrUpdate($scope.item)
                    .success(function (data) {
                        $scope.item.id = data.id;
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
            } else {
                $scope.alerts.push({
                    msg: validFailMessage
                });
            }
        };

        $scope.closeAlert = function (index) {
            $scope.alerts.splice(index, 1);
        };
    }
]);

brandModule.controller('BrandViewController', ['$scope',
    'BrandService',
    'item',
    '$http','$modalInstance',
    function ($scope, BrandService, item, $http,$modalInstance) {
        $scope.item = item;
        $scope.alerts = [];

        $scope.upload = function (event) {
            var file = event.target.files[0];
            var fd = new FormData();
            var reader = new FileReader();

            fd.append('file', file);

            $http.post('/admin/img/upload', fd, {
                withCredentials: true,
                headers: {
                    'Content-Type': undefined
                },
                transformRequest: angular.identity
            }).success(function (data) {
                $scope.item.cover = data[0];
            });
        };
        
        $scope.closeModal = function(){
        	$modalInstance.dismiss();
        }

        $scope.submit = function () {
            if ($scope.brandForm.$valid) {
                BrandService
                    .update($scope.item)
                    .success(function () {
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
            } else {
                $scope.alerts.push({
                    msg: validFailMessage
                });
            }
        };

        $scope.closeAlert = function (index) {
            $scope.alerts.splice(index, 1);
        };
    }
]);
