var brandModule = angular.module('Brand', []);

brandModule.service('BrandService', ['$http', function ($http) {
    this.findAll = function () {
        return $http.get('/admin/brands');
    };
    this.delete = function (item) {
        var state = item.state == 'ACTIVATE' ? 'DEACTIVATE' : 'ACTIVATE';
        return $http({
            method: 'POST',
            url: '/admin/brands/' + item.id + '/activate',
            data: $.param({
                state: state
            }),
            headers: {
                'Content-Type': 'application/x-www-form-urlencoded'
            }
        });
    };
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
                .success(function (data) {
                    $scope.items = data.content;
                })
                .error(function (err) {

                });
        }

        init();

        $scope.delete = function (item) {
            BrandService
                .delete(item)
                .success(function (data) {
                    if(data.data=='success'){
                        item.state=item.state == 'ACTIVATE' ? 'DEACTIVATE' : 'ACTIVATE';
                    }
                })
                .error(function (err) {

                });
        };
    }
]);

brandModule.controller('BrandDetailController', ['$scope', function ($scope) {
    $scope.upload = function () {
        console.log(11);
    };
}]);
