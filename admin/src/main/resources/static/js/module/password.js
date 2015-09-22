var passwordModule = angular.module('Password', []);

passwordModule.service('PasswordService', ['$http', function ($http) {
    function transform(obj) {
        var str = [];
        for (var p in obj)
            str.push(encodeURIComponent(p) + "=" + encodeURIComponent(obj[p]));
        return str.join("&");
    }

    var header = {
        'Content-Type': 'application/x-www-form-urlencoded'
    };

    this.update = function (password) {
        return $http({
            method: 'POST',
            url: '/admin/users/password',
            transformRequest: transform,
            data: {
                password: password
            },
            headers: header
        });
    };
}]);

passwordModule.controller('PasswordController', ['$scope',
    'PasswordService',
    function ($scope, PasswordService) {
        $scope.item = {};

        $scope.submit = function () {
            PasswordService
                .update($scope.item.password)
                .success(function(){
                    
                })
                .error(function(err){
                    
                });
        };
    }
]);
