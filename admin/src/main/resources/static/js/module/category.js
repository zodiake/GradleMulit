var category = angular.module('Category', []);

category.service('CategoryService', ['$http',
    function ($http) {
        function transform(obj) {
            var str = [];
            for (var p in obj)
                str.push(encodeURIComponent(p) + "=" + encodeURIComponent(obj[p]));
            return str.join("&");
        }
        var header = {
            'Content-Type': 'application/x-www-form-urlencoded'
        };

        this.findByParent = function (id) {
            if (id == null)
                return $http.get('/admin/product/categories');
            else
                return $http.get('/admin/product/category/' + id + '/categories');
        };

        this.save = function (item) {
            return $http({
                method: 'POST',
                url: '/admin/product/category/' + item.parent + '/categories',
                transformRequest: transform,
                data: {
                    name: item.name,
                    parent: item.parent
                },
                headers: header
            });
        };

        this.update = function (item) {
            return $http({
                method: 'POST',
                url: '/admin/product/category/' + item.id,
                transformRequest: transform,
                data: {
                    name: item.name,
                    parent: item.parent
                },
                headers: header
            });
        };

        this.delete = function (item) {
            return $http({
                method: 'POST',
                url: '/admin/product/categories/' + item.id + '/state',
                transformRequest: transform,
                data: {
                    activate: item.activate == 'ACTIVATE' ? 'DEACTIVE' : 'ACTIVATE'
                },
                headers: header
            });
        };

        this.saveOrUpdate = function (item) {
            if (!item.id)
                return this.save(item);
            else
                return this.update(item);
        };
    }
]);

category.controller('CategoryController', [
    '$scope',
    'CategoryService',
    '$modal',
    function ($scope, CategoryService, $modal) {
        categories = [{
            id: 1,
            name: '仪器'
        }, {
            id: 2,
            name: '试剂'
        }, {
            id: 3,
            name: '耗材'
        }, {
            id: 4,
            name: '服务'
        }];
        CategoryService
            .findByParent()
            .then(function (data) {
                $scope.yq = data.data['0'].categories;
                $scope.sj = data.data['3'].categories;
                $scope.hc = data.data['2'].categories;
                $scope.fw = data.data['1'].categories;
            });

        $scope.create = function () {
            $modal.open({
                templateUrl: '/admin/categoryAdd',
                size: 'sm',
                controller: 'CategoryAddController'
            });
        };

        $scope.edit = function (item) {
            $modal.open({
                templateUrl: '/admin/categoryEdit',
                size: 'sm',
                controller: 'CategoryEditController',
                resolve: {
                    item: function () {
                        return item;
                    },
                    categories: function () {
                        return categories;
                    }
                }
            });
        };

        $scope.delete = function (item) {
            var modalInstance = $modal.open({
                animation: $scope.animationsEnabled,
                templateUrl: 'productCategoryModal.html',
                controller: 'ProductModalCtrl',
                resolve: {
                    item: function () {
                        return item;
                    }
                }
            });
        };
    }
]);

 category.controller('ProductModalCtrl', [
    '$scope',
    '$modalInstance',
    'item',
    'CategoryService',
    function ($scope, $modalInstance, item, CategoryService) {
        $scope.item = item;
        $scope.ok = function () {
            CategoryService
                .delete(item)
                .success(function (data) {
                    if (data == 'success') {
                        item.activate = item.activate == 'ACTIVATE' ? 'DEACTIVATE' : 'ACTIVATE';
                        $modalInstance.dismiss();
                    }
                })
                .error(function () {

                });
        };

        $scope.cancel = function () {
            $modalInstance.dismiss();
        };
    }
]);

category.controller('ChildCategoryController', [
    '$scope',
    'CategoryService',
    '$modal',
    '$stateParams',
    function ($scope, CategoryService, $modal, $stateParams) {
        $scope.edit = function (item) {
            CategoryService
                .update(item)
                .success(function () {

                })
                .error(function () {

                });
        };

        function init() {
            CategoryService
                .findByParent($stateParams.id)
                .then(function (data) {
                    $scope.items = data.data;
                });
        }

        init();

        $scope.create = function () {
            $modal.open({
                templateUrl: '/admin/childCategoryAdd',
                size: 'sm',
                controller: 'CategoryChildAddController',
                scope: $scope
            });
        };

    }
]);

category.controller('CategoryChildAddController', ['$scope',
    '$stateParams',
    'CategoryService',
    function ($scope, $stateParams, CategoryService) {
        $scope.item = {};
        $scope.alerts=[];

        $scope.submit = function () {
            $scope.item.parent = $stateParams.id;
            CategoryService
                .saveOrUpdate($scope.item)
                .success(function (data) {
                    $scope.item.id = data.id;
                    var flag=false;
                    for(var i=0;i<$scope.items.length;i++){
                        if($scope.items[i].id==data.id){
                            flag=true;
                            break;
                        }
                    }
                    if(!flag){
                        $scope.items.push($scope.item);
                    }
                    $scope.alerts.push({
                        type: 'success',
                        msg: '保存成功',
                    });
                })
                .error(function () {
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

category.controller('CategoryAddController', [
    '$scope',
    'CategoryService',
    function ($scope, CategoryService) {
        $scope.item = {};
        $scope.alerts=[];

        $scope.submit = function () {
            CategoryService
                .saveOrUpdate($scope.item)
                .success(function (data) {
                    $scope.item.id=data.id;
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

        $scope.closeAlert = function (index) {
            $scope.alerts.splice(index, 1);
        };
    }
]);

category.controller('CategoryEditController', [
    '$scope',
    'CategoryService',
    'item',
    'categories',
    function ($scope, CategoryService, item, categories) {
        $scope.item = item;
        $scope.alerts = [];
        $scope.categories = categories;

        $scope.submit = function () {
            CategoryService
                .update($scope.item)
                .success(function () {
                    $scope.alerts.push({
                        type: 'success',
                        msg: '保存成功',
                    });
                })
                .error(function () {
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
