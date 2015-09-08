var module = angular.module('scroll', []);

module.service('ScrollService', ['$http', function ($http) {
    this.findAll = function () {
        return $http.get('/admin/scrollImages');
    };
}]);

module.controller('ScrollImgController', ['$scope',
    'ScrollService',
    function ($scope, ScrollService) {
        function init() {
            ScrollService
                .findAll()
                .success(function (data) {
                    $scope.items = data;
                })
                .error(function (err) {

                });
        }

        init();
    }
]);
