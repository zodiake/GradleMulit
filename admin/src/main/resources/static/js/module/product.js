var productModule = angular.module('Product', ['ProductCategory']);

productModule.service('ProductService', ['$http', function ($http) {

    function transform(obj) {
        var str = [];
        for (var p in obj)
            str.push(encodeURIComponent(p) + "=" + encodeURIComponent(obj[p]));
        return str.join("&");
    }

    var header = {
        'Content-Type': 'application/x-www-form-urlencoded'
    };

    this.findAll = function (opt) {
        return $http.get('/admin/products', {
            params: opt
        });
    };

    this.findOne = function (id) {
        return $http.get('/admin/products/' + id);
    };

    function updateState(id, state) {
        return $http({
            method: 'POST',
            url: '/admin/products/' + id + '/state',
            transformRequest: transform,
            data: {
                state: state
            },
            headers: header
        });
    }

    this.authenticate = function (id) {
        return updateState(id, 'examine');
    };

    this.refuse = function (id) {
        return updateState(id, 'not');
    };
}]);

productModule.controller('ProductController', ['$scope',
    'ProductService',
    'ProductCategoryService',
    '$modal',
    function ($scope, ProductService, ProductCategoryService, $modal) {
        $scope.page = 1;
        $scope.size = 15;
        $scope.opt = {};

        $scope.opt.page = $scope.page;
        $scope.opt.size = $scope.size;

        function init(opt) {
            ProductService
                .findAll(opt)
                .success(function (data) {
                    $scope.items = data.content;
                });
            ProductCategoryService
                .findAll()
                .success(function (data) {
                    $scope.firstCategories = data;
                });
        }

        init($scope.opt);

        $scope.findByParent = function (category) {
            ProductCategoryService
                .findByParentId(category)
                .success(function (data) {
                    $scope.secondCategories = data;
                });
        };

        $scope.search = function () {
            ProductService
                .findAll($scope.opt)
                .success(function (data) {
                    $scope.items = data.content;
                });
        };

        $scope.view = function (product) {
            $modal.open({
                templateUrl: '/admin/templates/products/detail',
                size: 'lg',
                controller: 'ProductDetailController',
                resolve: {
                    productId: function () {
                        return product.id;
                    }
                }
            });
        };
    }
]);

productModule.controller('ProductDetailController', ['$scope',
    'productId',
    '$sce',
    'ProductService',
    function ($scope, productId, $sce, ProductService) {

        function init() {
            ProductService
                .findOne(productId)
                .success(function (data) {
                    $scope.item = data;
                });
        }

        init();

        $scope.authenticate = function () {
            ProductService.authenticate($scope.item.id);
        };

        $scope.refuse = function () {
            ProductService.refuse($scope.item.id);
        };
    }
]);
