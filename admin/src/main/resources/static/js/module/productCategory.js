var module = angular.module('ProductCategory', []);

module.service('ProductCategoryService', ['$http', function ($http) {
    this.findAll = function () {
        return $http.get('/admin/product/category');
    };

    this.findByParentId = function (id) {
        return $http.get('/admin/product/category/' + id + '/categories');
    }
}]);
