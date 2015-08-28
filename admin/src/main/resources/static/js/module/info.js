var infoModule = angular.module('Info', []);

infoModule.service('InfoService', ['$http', function ($http) {
    this.findAll = function (opt) {
        $http.get('/admin/information', {
            params: opt
        });
    };
}]);

infoModule.controller('InfoController', ['$scope',
    'InfoService',
    function ($scope, InfoService) {
        $scope.page = 1;
        $scope.size = 15;
        $scope.state = "";

        function init(opt) {
            InfoService
                .findAll(opt)
                .success(function (data) {
                    $scope.items = data.content;
                })
                .error(function (err) {

                });
        }

        init({
            page: $scope.page,
            size: $scope.size,
            state: $scope.state
        });
    }
]);
