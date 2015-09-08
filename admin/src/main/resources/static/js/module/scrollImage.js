var module = angular.module('scroll', []);

module.service('ScrollService', ['$http', function ($http) {
    function transform(obj) {
        var str = [];
        for (var p in obj)
            str.push(encodeURIComponent(p) + "=" + encodeURIComponent(obj[p]));
        return str.join("&");
    }

    var header = {
        'Content-Type': 'application/x-www-form-urlencoded'
    };

    this.findAll = function () {
        return $http.get('/admin/scrollImages');
    };

    this.update = function (item) {
        return $http({
            method: 'POST',
            url: '/admin/scrollImages/' + item.id,
            transformRequest: transform,
            data: {
                href: item.href,
                imageUrl: item.imageUrl
            },
            headers: header
        });
    };
}]);

module.controller('ScrollImgController', ['$scope',
    'ScrollService',
    '$modal',
    function ($scope, ScrollService, $modal) {
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

        $scope.view = function (item) {
            $modal.open({
                templateUrl: '/admin/templates/scrollImg/detail',
                size: 'sm',
                controller: 'ScrollImgDetailController',
                resolve: {
                    item: function () {
                        return item;
                    }
                }
            });
        };
    }
]);

module.controller('ScrollImgDetailController', ['$scope',
    'item',
    '$http',
    'ScrollService',
    function ($scope, item, $http, ScrollService) {
        $scope.item = item;

        $scope.upload = function (event) {
            var file = event.target.files[0];
            var fd = new FormData();
            var reader = new FileReader();

            fd.append('file', file);

            $http.post('/admin/img/upload', fd, {
                withCredentials: true,
                headers: {
                    'Content-Type': undefined
                },
                transformRequest: angular.identity
            }).success(function (data) {
                $scope.item.imageUrl = data[0];
            });
        };

        $scope.submit = function () {
            ScrollService
                .update($scope.item)
                .success(function () {

                })
                .error(function (err) {

                });
        };
    }
]);
