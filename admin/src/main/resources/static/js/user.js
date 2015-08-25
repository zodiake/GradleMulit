var userModule = angular.module('User', []);

userModule.service('CommonUserService', ['$http', function ($http) {
    this.findAll = function (opt) {
        return $http.get('/admin/CommonUsers', {
            params: opt
        });
    };
}]);

userModule.service('ProviderService', ['$http', function ($http) {
    this.findAll = function (opt) {
        return $http.get('/admin/providers', {
            params: opt
        });
    };
}]);

userModule.controller('CommonUserController', ['$scope',
    'CommonUserService',
    function ($scope, CommonUserService) {
        $scope.page = 1;
        $scope.size = 15;

        function init(opt) {
            CommonUserService
                .findAll(opt)
                .success(function (data) {
                    $scope.items = data.content;
                    $scope.total = data.totalElements;
                })
                .error(function (err) {

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
    }
]);

userModule.controller('ProviderController', ['$scope',
    'ProviderService',
    function ($scope, ProviderService) {
        $scope.page = 1;
        $scope.size = 15;

        function init(opt) {
            ProviderService
                .findAll(opt)
                .success(function (data) {
                    $scope.items=data.content;
                    $scope.total=data.totalElements;
                })
                .error(function (err) {

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
    }
]);
