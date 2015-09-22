var module = angular.module('Solution', []);

module.service('SolutionService', ['$http', function ($http) {
    this.findAll = function () {
        return $http.get('/admin/solutions');
    };

    function transform(obj) {
        var str = [];
        for (var p in obj)
            str.push(encodeURIComponent(p) + "=" + encodeURIComponent(obj[p]));
        return str.join("&");
    }

    var header = {
        'Content-Type': 'application/x-www-form-urlencoded'
    };

    this.updateName = function (item) {
        return $http({
            method: 'POST',
            url: '/admin/solutions/' + item.id,
            transformRequest: transform,
            data: {
                name: item.name,
            },
            headers: header
        });
    };

    this.delete = function (item) {
        return $http({
            method: 'POST',
            url: '/admin/solutions/' + item.id + '/state',
            transformRequest: transform,
            data: {
                activate: item.activate == 'ACTIVATE' ? 'DEACTIVATE' : 'ACTIVATE'
            },
            headers: header
        });
    };

    this.save = function (item) {
        return $http({
            method: 'POST',
            url: '/admin/subjects/' + item.subject + '/solutions/',
            transformRequest: transform,
            data: {
                name: item.name,
            },
            headers: header
        });
    };

    this.findBySubject = function (id) {
        return $http.get('/admin/subjects/' + id + '/solutions');
    };
}]);

module.controller('SolutionCreateController', ['$scope', function ($scope) {
    $scope.solution = {};

    $scope.add = function (solution) {
        $scope.item.solutions.push($scope.solution);
    };
}]);

module.controller('SolutionController', ['$scope',
    '$stateParams',
    'SolutionService',
    '$modal',
    function ($scope, $stateParams, SolutionService, $modal) {
        var id = $stateParams.id;
        $scope.items = {};
        $scope.alerts=[];
        $scope.solution = {
            subject: id
        };

        $scope.update = function (item) {
            SolutionService
                .updateName(item)
                .success(function (data) {
                    $scope.alerts.push({
                        type: 'success',
                        msg: '保存成功',
                    });
                })
                .error(function (err) {
                    $scope.alerts.push({
                        msg: '保存失败',
                    });
                });
        };

        function init() {
            SolutionService
                .findBySubject(id)
                .success(function (data) {
                    $scope.items = data;
                })
                .error(function (err) {

                });
        }

        init(id);

        $scope.add = function (s) {
            SolutionService
                .save($scope.solution)
                .success(function (data) {
                    $scope.items.push({
                        name: $scope.solution.name,
                        id: data.id
                    });
                })
                .error(function (err) {

                });
        };

        $scope.delete = function (item) {
            var modalInstance = $modal.open({
                animation: true,
                templateUrl: 'solutionModal.html',
                controller: 'SolutionModuleController',
                resolve: {
                    item: function () {
                        return item;
                    }
                }
            });
        };

        $scope.closeAlert = function (index) {
            $scope.alerts.splice(index, 1);
        };
    }
]);

module.controller('SolutionModuleController', [
    '$scope',
    '$modalInstance',
    'item',
    'SolutionService',
    function ($scope, $modalInstance, item, SolutionService) {
        $scope.item = item;

        $scope.ok = function () {
            SolutionService
                .delete(item)
                .success(function (data) {
                    if (data.status == 'success') {
                        item.active = item.active == 'ACTIVATE' ? 'DEACTIVATE' : 'ACTIVATE';
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
