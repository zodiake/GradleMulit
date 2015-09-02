var productModule = angular.module('Product', ['ProductCategory', 'Solution']);

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
        return $http({
            method: 'POST',
            url: '/admin/products/' + id + '/state',
            transformRequest: transform,
            data: {
                state: state
            },
            headers: header
        });
    };

    this.refuse = function (id) {
        return updateState(id, 'not');
    };

    this.addSolution = function (id, solutions) {

        var adds = Object.keys(solutions).filter(function (s) {
            return solutions[s];
        });

        return $http({
            method: 'POST',
            url: '/admin/products/' + id + '/solutions',
            transformRequest: transform,
            data: {
                solutions: adds
            },
            headers: header
        });
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
                    },
                    solutions: function (SolutionService) {
                        return SolutionService.findAll();
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
    'solutions',
    function ($scope, productId, $sce, ProductService, solutions) {
        $scope.solutions = solutions.data;

        function init() {
            ProductService
                .findOne(productId)
                .success(function (data) {
                    $scope.item = data;
                    $scope.item.fake = {};
                    $scope.item.solutions.forEach(function (s) {
                        $scope.item.fake[s.id] = true;
                    });
                });
        }

        init();

        $scope.authenticate = function () {
            ProductService.authenticate($scope.item.id);
        };

        $scope.refuse = function () {
            ProductService.refuse($scope.item.id);
        };

        $scope.addSolution = function () {
            ProductService.addSolution($scope.item.id, $scope.item.fake);
        };
    }
]);
