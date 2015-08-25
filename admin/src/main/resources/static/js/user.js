var userModule = angular.module('User', []);

userModule.service('CommonUserService', ['$http',
    function ($http) {
        this.findAll = function (opt) {
            return $http.get('/admin/CommonUsers', {
                params: opt
            });
        };
        this.findOne = function (id) {
            return $http.get('/admin/CommonUsers/' + id);
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
    }
]);

userModule.controller('CommonUserController', ['$scope', 'CommonUserService',
    function ($scope, CommonUserService) {
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
            CommonUserService.findOne().success(function (data) {

            }).error(function (err) {

            });
        };
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
            size: $scope.size
        });

        $scope.seach = function () {
            init({
                page: $scope.page,
                size: $scope.size
            });
        };

        $scope.showDetail = function (item) {
            ProviderService
                .findOne(item.id)
                .success(function (data) {
                    $scope.item = data;
                    $modal.open({
                        templateUrl: '/admin/providerDetail',
                        size: 'lg'
                    });
                })
                .error(function (err) {

                });
        };
    }
]);
