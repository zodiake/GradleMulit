var module = angular.module('Solution', []);

module.service('SolutionService', ['$http', function ($http) {
    this.findAll = function () {
        return $http.get('/admin/solutions')
    };
}]);
