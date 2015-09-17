var adminUserModule = angular.module('adminUser', []);

adminUserModule.service('AdminService', ['$http', function ($http) {
    function transform(obj) {
        var str = [];
        for (var p in obj)
            str.push(encodeURIComponent(p) + "=" + encodeURIComponent(obj[p]));
        return str.join("&");
    }

    var header = {
        'Content-Type': 'application/x-www-form-urlencoded'
    };

    this.findAll = function (page) {
        return $http.get('/admin/users', {
            size: page.size,
            page: page.page
        });
    };

    this.save = function (item) {
        return $http({
            method: 'POST',
            url: '/admin/users',
            transformRequest: transform,
            data: {
                name: item.name,
                password: item.password
            },
            headers: header
        });
    };

    this.update = function (item) {
        return $http({
            method: 'POST',
            url: '/admin/users/' + item.id,
            transformRequest: transform,
            data: {
                name: item.name,
                password: item.password
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
}]);

adminUserModule.controller('AdminUserController', ['$scope',
    'AdminService',
    '$modal',
    function ($scope, AdminService, $modal) {
        $scope.page = 1;
        $scope.size = 15;

        function init(option) {
            AdminService
                .findAll(option)
                .success(function (data) {
                    $scope.items = data.content;
                })
                .error(function (err) {

                });
        }

        init({
            page: $scope.page,
            size: $scope.size
        });

        $scope.create = function () {
            $modal.open({
                templateUrl: '/admin/templates/adminUserAdd',
                size: 'sm',
                controller: 'AdminUserAddController',
                scope: $scope,
                resolve: {
                    roles: function (RoleService) {
                        return RoleService.findAll();
                    }
                }
            });
        };
    }
]);

adminUserModule.controller('AdminUserAddController', ['$scope',
    'AdminService',
    '$modal',
    'roles',
    function ($scope, AdminService, $modal, roles) {
        $scope.item = {};
        $scope.roles = roles;

        $scope.submit = function () {
            AdminService
                .saveOrUpdate($scope.item)
                .success(function () {

                })
                .error(function (err) {

                });
        };
    }
]);
