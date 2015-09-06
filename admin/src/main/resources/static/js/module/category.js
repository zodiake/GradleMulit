var category = angular.module('Category', []);

category.service('CategoryService', ['$http',
    function($http) {
        this.findAll = function(option) {
            return $http.get('/admin/product/categories', {
                params: option
            });
        };
    }
]);

category.controller('CategoryController', ['$scope', 'CategoryService',
    function($scope, CategoryService) {
        function init(option) {
            CategoryService
                .findAll(option)
                .success(function(data) {
                	console.log(data);
                    $scope.yq = data;
                    $scope.sj = data;
                })
                .error(function(err) {

                });
        }

        init();
    }
]);
