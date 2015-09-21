var roleModule = angular.module('role', ['Menu']);

roleModule.service('RoleService', ['$http', function ($http) {

    function transform(obj) {
        var str = [];
        for (var p in obj)
            str.push(encodeURIComponent(p) + "=" + encodeURIComponent(obj[p]));
        return str.join("&");
    }

    var header = {
        'Content-Type': 'application/x-www-form-urlencoded'
    };

    this.findAll = function () {
        return $http.get('/admin/authorities');
    };

    this.findAllActive = function () {
        return $http.get('/admin/authorities?active=activate');
    };

    this.findOne = function (id) {
        return $http.get('/admin/authorities/' + id);
    };

    this.update = function (item) {
        var roles = Object.keys(item.fake).filter(function (s) {
            return item.fake[s];
        });
        return $http({
            method: 'POST',
            url: '/admin/authorities/' + item.id,
            transformRequest: transform,
            data: {
                name: item.name,
                roles: roles
            },
            headers: header
        });
    };
}]);

roleModule.controller('RoleController', ['$scope',
    'RoleService',
    '$modal',
    function ($scope, RoleService, $modal) {
        function init() {
            RoleService
                .findAll()
                .success(function (data) {
                    $scope.items = data;
                })
                .error(function (err) {

                });
        }

        init();

        $scope.edit = function (item) {
            $modal.open({
                templateUrl: '/admin/templates/authorityDetail',
                size: 'sm',
                controller: 'roleDetailController',
                scope: $scope,
                resolve: {
                    item: function (RoleService) {
                        return RoleService.findOne(item.id);
                    },
                    menus: function (MenuService) {
                        return MenuService.findAll();
                    }
                }
            });
        };
    }
]);

roleModule.controller('roleDetailController', ['$scope',
    'item',
    'menus',
    'RoleService',
    function ($scope, item, menus, RoleService) {
        $scope.item = item.data;
        $scope.menus = menus.data;
        $scope.item.fake = {};

        $scope.item.menus.forEach(function (s) {
            $scope.item.fake[s.id] = true;
        });

        $scope.submit = function () {
            RoleService
                .update($scope.item)
                .success(function (data) {

                })
                .error(function (err) {

                });
        }
    }
]);
