var advertiseModule = angular.module('Advertise', []);

advertiseModule.service('AdvertiseService', ['$http',
function($http) {
    this.findAll = function(opt) {
        return $http.get('/admin/advertisements', {
            params : opt
        });
    };
    this.save = function(item) {
        return $http({
            method : 'POST',
            url : '/admin/advertisements',
            transformRequest : function(obj) {
                var str = [];
                for (var p in obj)
                str.push(encodeURIComponent(p) + "=" + encodeURIComponent(obj[p]));
                return str.join("&");
            },
            data : {
                category : item.category,
                url:item.href
            },
            headers : {
                'Content-Type' : 'application/x-www-form-urlencoded'
            }
        });
    };
}]);

advertiseModule.service('AdvertiseCategoryService', ['$http',
function($http) {
    this.findAll = function() {
        return $http.get('/admin')
    };
}]);

advertiseModule.controller('AdvertiseController', ['$scope', 'AdvertiseService',
function($scope, AdvertiseService) {
    $scope.page = 1;
    $scope.size = 15;
    $scope.state

    function init(opt) {
        AdvertiseService.findAll(opt).success(function(data) {
            $scope.items = data.content;
        }).error(function() {

        });
    }


    $scope.search = function() {
        init({
            page : $scope.page,
            size : $scope.size,
            state : $scope.state
        });
    };

    init({
        page : $scope.page,
        size : $scope.size,
        state : $scope.state
    });
}]);

advertiseModule.controller('CreateAdvertiseController', ['$scope', 'category', 'AdvertiseService',
function($scope, category, AdvertiseService) {
    $scope.categories = category.data;

    $scope.submit = function() {
        AdvertiseService.save($scope.item).success(function() {

        }).error(function() {

        });
    };
}]);

advertiseModule.controller('AdvertiseDetailController', ['$scope', 'category',
function($scope, category) {
    $scope.category = category;
}]);
