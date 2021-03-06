var advertiseModule = angular.module('Advertise', []);
var validFailMessage = '校验失败';

advertiseModule.service('AdvertiseService', ['$http',
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
            return $http.get('/admin/advertisements', {
                params: opt
            });
        };

        this.save = function (item) {
            return $http({
                method: 'POST',
                url: '/admin/advertisements',
                transformRequest: transform,
                data: {
                    category: item.category,
                    url: item.url,
                    coverImg: item.cover
                },
                headers: header
            });
        };

        this.update = function (item) {
            return $http({
                method: 'POST',
                url: '/admin/advertisements/' + item.id,
                transformRequest: transform,
                data: {
                    category: item.category,
                    url: item.url,
                    coverImg: item.cover
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

        this.updateState = function (item) {
            var state = item.state == 'ACTIVATE' ? 'DEACTIVATE' : 'ACTIVATE';
            return $http({
                method: 'POST',
                url: '/admin/advertisements/' + item.id + '/state',
                transformRequest: transform,
                data: {
                    state: state
                },
                headers: header
            });
        };
    }
]);

advertiseModule.service('AdvertiseCategoryService', ['$http',
    function ($http) {
        this.findAll = function () {
            return $http.get('/admin/advertise/category');
        };
    }
]);

advertiseModule.controller('AdvertiseController', ['$scope', 'AdvertiseService', '$modal',
    function ($scope, AdvertiseService, $modal) {
        $scope.page = 1;
        $scope.size = 15;
        $scope.state;

        function init(opt) {
            AdvertiseService
                .findAll(opt)
                .success(function (data) {
                    $scope.items = data.content;
                    $scope.total = data.totalElements;
                }).error(function () {

                });
        }

        $scope.search = function () {
            init({
                page: $scope.page,
                size: $scope.size,
                state: $scope.state
            });
        };

        init({
            page: $scope.page,
            size: $scope.size,
            state: $scope.state
        });

        $scope.create = function () {
            $modal.open({
                templateUrl: '/admin/advertise?create',
                size: 'sm',
                controller: 'CreateAdvertiseController',
                resolve: {
                    category: function (AdvertiseCategoryService) {
                        return AdvertiseCategoryService.findAll();
                    }
                }
            });
        };

        $scope.view = function (item) {
            $modal.open({
                templateUrl: '/admin/advertise?create',
                size: 'sm',
                controller: 'AdvertiseDetailController',
                resolve: {
                    category: function (AdvertiseCategoryService) {
                        return AdvertiseCategoryService.findAll();
                    },
                    item: function () {
                        return item;
                    }
                }
            });
        };

        $scope.updateState = function (item) {
            var modalInstance = $modal.open({
                animation: $scope.animationsEnabled,
                templateUrl: 'AdvModal.html',
                controller: 'AdvModalCtrl',
                resolve: {
                    item: function () {
                        return item;
                    }
                }
            });
        };
    }
]);

advertiseModule.controller('AdvModalCtrl', [
    '$scope',
    '$modalInstance',
    'item',
    'AdvertiseService',
    function ($scope, $modalInstance, item, AdvertiseService) {
        $scope.item = item;
        $scope.ok = function () {
            AdvertiseService
                .updateState(item)
                .success(function (data) {
                    if (data == 'success') {
                        item.state = item.state == 'ACTIVATE' ? 'DEACTIVATE' : 'ACTIVATE';
                        $modalInstance.dismiss();
                    }
                })
                .error(function (err) {

                });
        };

        $scope.cancel = function () {
            $modalInstance.dismiss();
        };
    }
]);

advertiseModule.controller('CreateAdvertiseController', ['$scope', 'category', 'AdvertiseService', '$http','$modalInstance',
    function ($scope, category, AdvertiseService, $http,$modalInstance) {
        $scope.categories = category.data;
        $scope.item = {};
        $scope.alerts = [];

        $scope.submit = function () {
            if ($scope.advForm.$valid) {
                AdvertiseService
                    .saveOrUpdate($scope.item)
                    .success(function (data) {
                        $scope.item.id = data.id;
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
            } else {
                $scope.alerts.push({
                    msg: validFailMessage
                });
            }
        };
        $scope.closeModal = function(){
        	$modalInstance.dismiss();
        };

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

        $scope.closeAlert = function (index) {
            $scope.alerts.splice(index, 1);
        };
    }
]);

advertiseModule.controller('AdvertiseDetailController', ['$scope',
    'category',
    'item',
    'AdvertiseService',
    '$http','$modalInstance',
    function ($scope, category, item, AdvertiseService, $http,$modalInstance) {
        $scope.categories = category.data;
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

        $scope.submit = function () {
            if ($scope.advForm.$valid) {
                AdvertiseService
                    .update($scope.item)
                    .success(function () {
                        $scope.alerts.push({
                            type: 'success',
                            msg: '保存成功',
                        });
                    }).error(function (err) {
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
        
        $scope.closeModal = function(){
        	$modalInstance.dismiss();
        }
    }
]);
