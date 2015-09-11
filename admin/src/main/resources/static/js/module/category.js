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
                url: '/admin/product/category/' + item.category + '/categories',
                transformRequest: transform,
                data: {
                    name: item.name
                },
                headers: header
            });
        };

        this.update = function (item) {
            return $http({
                method: 'POST',
                url: '/admin/product/category/' + item.category,
                transformRequest: transform,
                data: {
                    name: item.name
                },
                headers: header
            });
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
                $scope.sj = data.data['1'].categories;
                $scope.hc = data.data['2'].categories;
                $scope.fw = data.data['3'].categories;
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

    }
]);

category.controller('ChildCategoryController', [
    '$scope',
    'CategoryService',
    '$modal',
    '$stateParams',
    function ($scope, CategoryService, $modal, $stateParams) {

        function init() {
            CategoryService
                .findByParent($stateParams.id)
                .then(function (data) {
                    $scope.items=data.data;
                });
        }

        init();

        $scope.create = function () {
            $modal.open({
                templateUrl: '/admin/categoryAdd',
                size: 'sm',
                controller: 'CategoryAddController'
            });
        };

    }
]);

category.controller('CategoryAddController', [
    '$scope',
    'CategoryService',
    function ($scope, CategoryService) {
        $scope.submit = function () {
            CategoryService
                .save($scope.item)
                .success(function () {

                })
                .error(function (err) {

                });
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
        console.log(item.parent);
        $scope.categories = categories;
        $scope.submit = function () {
            Category
                .save($scope.item)
                .success(function () {

                })
                .error(function () {

                });
        };

        $scope.closeAlert = function (index) {
            $scope.alerts.splice(index, 1);
        };
    }
]);
