var menuModule = angular.module('Menu', []);

menuModule.service('MenuService', ['$http', function ($http) {
    this.findAll = function () {
        return $http.get('/admin/menus');
    };
}]);
