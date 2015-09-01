/**
 * 
 */
var productModule = angular.module('Product', ['ProductCategory']);

productModule.service('ProductService', ['$http', function ($http) {
    this.findAll = function (opt) {
        return $http.get('/admin/products', {
            params: opt
        });
    };
}]);

productModule.controller('ProductController', ['$scope',
    'ProductService',
    'ProductCategoryService',
    function ($scope, ProductService, ProductCategoryService) {
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
    }
]);
