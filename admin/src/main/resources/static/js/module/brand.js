var brandModule = angular.module('Brand', []);

brandModule.service('BrandService', ['$http', function ($http) {
    this.findAll = function () {
        return $http.get('/admin/brands');
    }
}]);

brandModule.controller('BrandController', ['$scope',
    '$modal',
    'BrandService',
    function ($scope, $modal, BrandService) {
        $scope.create = function () {
            $modal.open({
                templateUrl: '/admin/brandDetail',
                size: 'sm',
                controller: 'BrandDetailController'
            });
        };

        function init() {
            BrandService
                .findAll()
                .success(function () {

                })
                .error(function (err) {

                })
        }

        init();
    }
]);

brandModule.controller('BrandDetailController', ['$scope', function ($scope) {
    $scope.upload = function () {
        console.log(11);
    }
}]);
