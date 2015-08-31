/**
 * 
 */
var productModule = angular.module('Product', ['ProductCategory']);

productModule.service('ConsumableService', ['$http', function ($http) {
    this.findAll = function (opt) {
        return $http.get('/admin/consumable', {
            params: opt
        });
    };
}]);

productModule.controller('ConsumableController', ['$scope',
    'ConsumableService',
    'ProductCategoryService',
    function ($scope, ConsumableService, ProductCategoryService) {
        $scope.page = 1;
        $scope.size = 15;
        $scope.opt = {};

        $scope.opt.page = $scope.page;
        $scope.opt.size = $scope.size;

        function init(opt) {
            ConsumableService
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
            console.log(category);
            ProductCategoryService
                .findByParentId(category)
                .success(function (data) {
                    $scope.secondCategories = data;
                });
        };

        $scope.search = function () {
            ConsumableService
                .findAll($scope.opt)
                .success(function (data) {
                    $scope.items = data.content;
                });
        };
    }
]);
