var advertiseModule = angular.module('Advertise', []);

advertiseModule.service('AdvertiseService', ['$http', function ($http) {
    this.findAll = function (opt) {
        return $http.get('/admin/advertisements')
    }
}]);

advertiseModule.controller('AdvertiseController', ['$scope', function ($scope) {

}]);

advertiseModule.controller('CreateAdvertiseController', ['$scope', function ($scope) {

}]);
