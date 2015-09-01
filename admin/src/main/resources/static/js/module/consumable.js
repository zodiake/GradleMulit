var module = angular.module('Product', []);

module.service('ProductService', ['$http', function ($http) {
    this.findAll = function (opt) {
        return $http.get('/admin/products', {
            params: opt
        });
    };
}]);

module.controller('ProductController', ['$scope',
    'ProductService',
    function ($scope, ProductService) {
        $scope.page = 1;
        $scope.size = 15;

        function init(opt) {
            ProductService
                .findAll(opt)
                .success(function (data) {
                    $scope.items = data;
                })
                .error(function (err) {

                });
        }

        init(opt);
    }
]);
