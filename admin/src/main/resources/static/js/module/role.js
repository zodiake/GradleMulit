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

    this.save = function (item) {
        var menus = Object.keys(item.fake).filter(function (s) {
            return item.fake[s];
        });
        return $http({
            method: 'POST',
            url: '/admin/authorities/',
            transformRequest: transform,
            data: {
                name: item.name,
                menus: menus
            },
            headers: header
        });
    };

    this.update = function (item) {
        var menus = Object.keys(item.fake).filter(function (s) {
            return item.fake[s];
        });
        return $http({
            method: 'POST',
            url: '/admin/authorities/' + item.id,
            transformRequest: transform,
            data: {
                name: item.name,
                menus: menus
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

    this.delete = function (item) {
        var state = item.state == 'ACTIVATE' ? 'deactivate' : 'activate';
        return $http({
            method: 'POST',
            url: '/admin/authorities/' + item.id + '/state',
            transformRequest: transform,
            data: {
                active: state
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

        $scope.delete = function (item) {
            var modalInstance = $modal.open({
                animation: true,
                templateUrl: 'roleModal.html',
                controller: 'RoleModuleController',
                resolve: {
                    item: function () {
                        return item;
                    }
                }
            });
        };

        $scope.create = function () {
            $modal.open({
                templateUrl: '/admin/templates/authorityDetail',
                size: 'sm',
                controller: 'RoleCreateController',
                scope: $scope,
                resolve: {
                    menus: function (MenuService) {
                        return MenuService.findAll();
                    }
                }
            });
        };
    }
]);

roleModule.controller('RoleModuleController', ['$scope',
    '$modalInstance',
    'item',
    'RoleService',
    function ($scope, $modalInstance, item, RoleService) {
        $scope.item = item;

        $scope.ok = function () {
            RoleService
                .delete(item)
                .success(function (data) {
                    if (data.status == 'success') {
                        item.state = item.state == 'ACTIVATE' ? 'DEACTIVATE' : 'ACTIVATE';
                        $modalInstance.dismiss();
                    }
                })
                .error(function (err) {

                });
        };

        $scope.cancel = function () {
            $modalInstance.dismiss();
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
        $scope.alerts = [];

        $scope.item.menus.forEach(function (s) {
            $scope.item.fake[s.id] = true;
        });

        $scope.submit = function () {
            RoleService
                .update($scope.item)
                .success(function (data) {
                    if (data.id) {
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

roleModule.controller('RoleCreateController', ['$scope',
    'menus',
    'RoleService',
    function ($scope, menus, RoleService) {
        $scope.menus = menus.data;
        $scope.alerts = [];

        $scope.submit = function () {
            RoleService
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
