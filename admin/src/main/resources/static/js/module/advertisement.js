var advertiseModule = angular.module('Advertise', []);

advertiseModule.service('AdvertiseService', ['$http',
    function ($http) {

        function transform(obj) {
            var str = [];
            for (var p in obj)
                str.push(encodeURIComponent(p) + "=" + encodeURIComponent(obj[p]));
            return str.join("&");
        }

        var header = {
            'Content-Type': 'application/x-www-form-urlencoded'
        };

        this.findAll = function (opt) {
            return $http.get('/admin/advertisements', {
                params: opt
            });
        };

        this.save = function (item) {
            return $http({
                method: 'POST',
                url: '/admin/advertisements',
                transformRequest: transform,
                data: {
                    category: item.category,
                    url: item.url,
                    coverImg: item.coverImg
                },
                headers: header
            });
        };

        this.update = function (item) {
            return $http({
                method: 'POST',
                url: '/admin/advertisements/' + item.id,
                transformRequest: transform,
                data: {
                    category: item.category,
                    url: item.url,
                    coverImg: item.coverImg
                },
                headers: header
            });
        };
    }
]);

advertiseModule.service('AdvertiseCategoryService', ['$http',
    function ($http) {
        this.findAll = function () {
            return $http.get('/admin/advertise/category');
        };
    }
]);

advertiseModule.controller('AdvertiseController', ['$scope', 'AdvertiseService', '$modal',
    function ($scope, AdvertiseService, $modal) {
        $scope.page = 1;
        $scope.size = 15;
        $scope.state

        function init(opt) {
            AdvertiseService.findAll(opt).success(function (data) {
                $scope.items = data.content;
            }).error(function () {

            });
        }


        $scope.search = function () {
            init({
                page: $scope.page,
                size: $scope.size,
                state: $scope.state
            });
        };

        init({
            page: $scope.page,
            size: $scope.size,
            state: $scope.state
        });

        $scope.create = function () {
            $modal.open({
                templateUrl: '/admin/createAdvertise',
                size: 'sm',
                controller: 'CreateAdvertiseController',
                resolve: {
                    category: function (AdvertiseCategoryService) {
                        return AdvertiseCategoryService.findAll();
                    }
                }
            });
        };

        $scope.view = function (item) {
            $modal.open({
                templateUrl: '/admin/createAdvertise',
                size: 'sm',
                controller: 'AdvertiseDetailController',
                resolve: {
                    category: function (AdvertiseCategoryService) {
                        return AdvertiseCategoryService.findAll();
                    },
                    item: function () {
                        return item;
                    }
                }
            });
        };
    }
]);

advertiseModule.controller('CreateAdvertiseController', ['$scope', 'category', 'AdvertiseService', '$http',
    function ($scope, category, AdvertiseService, $http) {
        $scope.categories = category.data;
        $scope.item = {};

        $scope.submit = function () {
            AdvertiseService.save($scope.item).success(function () {

            }).error(function () {

            });
        };

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
                $scope.item.coverImg = data[0];
            });
        };
    }
]);

advertiseModule.controller('AdvertiseDetailController', ['$scope',
    'category',
    'item',
    'AdvertiseService',
    '$http',
    function ($scope, category, item, AdvertiseService, $http) {
        $scope.categories = category.data;
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
                $scope.item.coverImg = data[0];
            });
        };

        $scope.submit = function () {
            AdvertiseService
                .update($scope.item)
                .success(function () {

                }).error(function (err) {

                });
        };
    }
]);
