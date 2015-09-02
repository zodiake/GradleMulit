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
            CategoryService.findAll(option).success(function(data) {
                $scope.yq = data.content;
                $scope.sj = data.content;
            }).error(function(err) {

            });
        }
    }
]);
