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

        var adds = Object.keys(item.fake).filter(function (s) {
            return item.fake[s];
        });

        return $http({
            method: 'POST',
            url: '/admin/users',
            transformRequest: transform,
            data: {
                name: item.name,
                password: item.password,
                roles: adds
            },
            headers: header
        });
    };

    this.update = function (item) {
        var adds = Object.keys(item.fake).filter(function (s) {
            return item.fake[s];
        });

        return $http({
            method: 'POST',
            url: '/admin/users/' + item.id,
            transformRequest: transform,
            data: {
                name: item.name,
                password: item.password,
                roles: adds
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

    this.findOne = function (id) {
        return $http.get('/admin/users/' + id);
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
                    $scope.alerts.push({
                        msg: '保存失败',
                    });
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
                        return RoleService.findAllActive();
                    }
                }
            });
        };

        $scope.view = function (item) {
            $modal.open({
                templateUrl: '/admin/templates/adminUserAdd',
                size: 'sm',
                controller: 'AdminUserUpdateController',
                scope: $scope,
                resolve: {
                    roles: function (RoleService) {
                        return RoleService.findAllActive();
                    },
                    item: function (AdminService) {
                        return AdminService.findOne(item.id);
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
        $scope.roles = roles.data;
        $scope.alerts = [];

        $scope.submit = function () {
            AdminService
                .saveOrUpdate($scope.item)
                .success(function (data) {
                    if (data.id) {
                        $scope.item.id = data.id;
                        $scope.alerts.push({
                            type: 'success',
                            msg: '保存成功',
                        });
                    }
                })
                .error(function (err) {

                });
        };

        $scope.closeAlert = function (index) {
            $scope.alerts.splice(index, 1);
        };
    }
]);

adminUserModule.controller('AdminUserUpdateController', ['$scope',
    'AdminService',
    '$modal',
    'roles',
    'item',
    function ($scope, AdminService, $modal, roles, item) {
        $scope.item = item.data;
        $scope.item.fake = [];
        $scope.roles = roles.data;
        $scope.alerts = [];

        $scope.item.roles.forEach(function (s) {
            $scope.item.fake[s.id] = true;
        });

        $scope.submit = function () {
            AdminService
                .saveOrUpdate($scope.item)
                .success(function (data) {
                    if (data.id) {
                        $scope.item.id = data.id;
                        $scope.alerts.push({
                            type: 'success',
                            msg: '保存成功',
                        });
                    }
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
