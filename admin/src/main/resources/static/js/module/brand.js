var brandModule = angular.module('Brand', []);

brandModule.service('BrandService', ['$http',
function($http) {
    this.findAll = function(opt) {
        return $http.get('/admin/brands', {
            params : opt
        });
    };
    this.delete = function(item) {
        var state = item.state == 'ACTIVATE' ? 'DEACTIVATE' : 'ACTIVATE';
        return $http({
            method : 'POST',
            url : '/admin/brands/' + item.id + '/activate',
            transformRequest : function(obj) {
                var str = [];
                for (var p in obj)
                str.push(encodeURIComponent(p) + "=" + encodeURIComponent(obj[p]));
                return str.join("&");
            },
            data : {
                state : state
            },
            headers : {
                'Content-Type' : 'application/x-www-form-urlencoded'
            }
        });
    };
}]);

brandModule.controller('BrandController', ['$scope', '$modal', 'BrandService',
function($scope, $modal, BrandService) {
    $scope.page = 1;
    $scope.size = 15;

    $scope.create = function() {
        $modal.open({
            templateUrl : '/admin/brandDetail',
            size : 'sm',
            controller : 'BrandDetailController'
        });
    };

    function init(opt) {
        BrandService.findAll(opt).success(function(data) {
            $scope.items = data.content;
        }).error(function(err) {

        });
    }

    init({
        page : $scope.page,
        size : $scope.size
    });

    $scope.delete = function(item) {
        BrandService.delete(item).success(function(data) {
            if (data.data == 'success') {
                item.state = item.state == 'ACTIVATE' ? 'DEACTIVATE' : 'ACTIVATE';
            }
        }).error(function(err) {

        });
    };
}]);

brandModule.controller('BrandDetailController', ['$scope',
function($scope) {
    $scope.upload = function() {
        console.log(11);
    };
}]);
