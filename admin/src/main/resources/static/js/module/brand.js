var brandModule = angular.module('Brand', []);

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
                    coverImg: item.cover
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
                controller: 'BrandDetailController'
            });
        };

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

        $scope.search = function () {
            init({
                page: $scope.page,
                size: $scope.size,
            });
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

brandModule.controller('BrandDetailController', ['$scope',
    '$http',
    'BrandService',
    function ($scope, $http, BrandService) {
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

        $scope.submit = function () {
            $scope.disable = true;
            BrandService
                .saveOrUpdate($scope.item)
                .success(function (data) {
                    $scope.disable = false;
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
        };

        $scope.closeAlert = function (index) {
            $scope.alerts.splice(index, 1);
        };
    }
]);

brandModule.controller('BrandViewController', ['$scope',
    'BrandService',
    'item',
    '$http',
    function ($scope, BrandService, item, $http) {
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
        };

        $scope.closeAlert = function (index) {
            $scope.alerts.splice(index, 1);
        };
    }
]);
