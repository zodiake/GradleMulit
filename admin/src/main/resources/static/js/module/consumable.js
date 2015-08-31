var module = angular.module('Consumable', []);

module.service('ConsumableService', ['$http', function ($http) {
    this.findAll = function (opt) {
        return $http.get('/admin/consumables', {
            params: opt
        });
    };
}]);

module.controller('ConsumableController', ['$scope',
    'ConsumableService',
    function ($scope, ConsumableService) {
        $scope.page = 1;
        $scope.size = 15;

        function init(opt) {
            ConsumableService
                .findAll(opt)
                .success(function (data) {
                    $scope.items = data;
                })
                .error(function (err) {

                });
        }

        init(opt);
    }
]);
