var roleModule = angular.module('role', []);

roleModule.service('RoleService', ['$http', function ($http) {
    this.findAll = function () {
        return $http.get('/admin/authorities');
    };
    this.findAllActive = function () {
        return $http.get('/admin/authorities?active=activate');
    };
}]);

roleModule.controller('RoleController', ['$scope',
    'RoleService',
    function ($scope, RoleService) {
        function init() {
            RoleService
                .findAll()
                .success(function (data) {
                    console.log(data);
                })
                .error(function (err) {

                });
        }

        init();
    }
]);
