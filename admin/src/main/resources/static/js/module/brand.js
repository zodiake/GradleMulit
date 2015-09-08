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
                    coverImg: item.src
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
            BrandService
                .delete(item)
                .success(function (data) {
                    if (data.data == 'success') {
                        item.state = item.state == 'ACTIVATE' ? 'DEACTIVATE' : 'ACTIVATE';
                    }
                }).error(function (err) {

                });
        };
    }
]);

brandModule.controller('BrandDetailController', ['$scope',
    '$http',
    'BrandService',
    function ($scope, $http, BrandService) {
        $scope.item = {};

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
                $scope.item.src = data[0];
            });
        };

        $scope.submit = function () {
            BrandService
                .save($scope.item)
                .success(function () {

                })
                .error(function (err) {

                });
        };
    }
]);

brandModule.controller('BrandViewController', ['$scope',
    'BrandService',
    'item',
    '$http',
    function ($scope, BrandService, item, $http) {
        $scope.item = item;

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

                })
                .error(function (err) {

                });
        };
    }
]);
