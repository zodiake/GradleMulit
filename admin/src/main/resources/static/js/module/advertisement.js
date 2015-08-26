var advertiseModule = angular.module('Advertise', []);

advertiseModule.service('AdvertiseService', ['$http', function ($http) {
    this.findAll = function (opt) {
        return $http.get('/admin/advertisements', {
            params: opt
        });
    };
}]);

advertiseModule.controller('AdvertiseController', ['$scope',
    'AdvertiseService',
    function ($scope, AdvertiseService) {
        $scope.page = 1;
        $scope.size = 15;
        $scope.state;

        function init(opt) {
            AdvertiseService
                .findAll(opt)
                .success(function (data) {
                    $scope.items = data.content;
                })
                .error(function () {

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
    }
]);

advertiseModule.controller('CreateAdvertiseController', ['$scope', function ($scope) {

}]);

advertiseModule.controller('AdvertiseDetailController', ['$scope', function ($scope) {

}]);
