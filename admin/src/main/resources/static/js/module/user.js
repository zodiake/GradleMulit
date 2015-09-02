var userModule = angular.module('User', []);

userModule.service('CommonUserService', ['$http',
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
            return $http.get('/admin/CommonUsers', {
                params: opt
            });
        };

        this.findOne = function (item) {
            return $http.get('/admin/CommonUsers/' + item.id);
        };

        this.updateScore = function (item) {
            return $http({
                method: 'POST',
                url: '/admin/CommonUsers/' + item.id + '/score',
                transformRequest: transform,
                data: {
                    score: item.score,
                },
                headers: header
            });
        };
    }
]);

userModule.service('ProviderService', ['$http',
    function ($http) {
        this.findAll = function (opt) {
            return $http.get('/admin/providers', {
                params: opt
            });
        };
        this.findOne = function (id) {
            return $http.get('/admin/providers/' + id);
        };
        this.authenticate = function (item) {
            return $http.put('/admin/providers/' + item.id + '/authority');
        };
    }
]);

userModule.controller('CommonUserController', ['$scope',
    'CommonUserService',
    '$modal',
    function ($scope, CommonUserService, $modal) {
        $scope.page = 1;
        $scope.size = 15;

        function init(opt) {
            CommonUserService.findAll(opt).success(function (data) {
                $scope.items = data.content;
                $scope.total = data.totalElements;
            }).error(function (err) {

            });
        }

        init({
            page: $scope.page,
            size: $scope.size
        });

        $scope.seach = function () {
            init({
                page: $scope.page,
                size: $scope.size
            });
        };

        $scope.showDetail = function (item) {
            CommonUserService
                .findOne(item)
                .success(function (data) {
                    $modal.open({
                        templateUrl: '/admin/commonUserDetail',
                        size: 'lg',
                        controller: 'CommonUserDetailController',
                        resolve: {
                            item: function () {
                                return data;
                            }
                        }
                    });
                }).error(function (err) {

                });
        };
    }
]);

userModule.controller('ProviderController', ['$scope',
    'ProviderService',
    '$modal',
    function ($scope, ProviderService, $modal) {
        $scope.page = 1;
        $scope.size = 15;

        function init(opt) {
            ProviderService.findAll(opt).success(function (data) {
                $scope.items = data.content;
                $scope.total = data.totalElements;
            }).error(function (err) {

            });
        }

        init({
            page: $scope.page,
            size: $scope.size,
            auth: $scope.auth
        });

        $scope.search = function () {
            init({
                page: $scope.page,
                size: $scope.size,
                auth: $scope.auth
            });
        };

        $scope.showDetail = function (item) {
            ProviderService
                .findOne(item.id)
                .success(function (data) {
                    $modal.open({
                        templateUrl: '/admin/providerDetail',
                        controller: 'ProviderDetailController',
                        size: 'lg',
                        resolve: {
                            item: function () {
                                return data;
                            }
                        }
                    });
                })
                .error(function (err) {

                });
        };
    }
]);

userModule.controller('CommonUserDetailController', ['$scope',
    'item',
    'CommonUserService',
    function ($scope, item, CommonUserService) {
        $scope.item = item;

        $scope.updateScore = function (item) {
            CommonUserService
                .updateScore(item)
                .success(function () {

                })
                .error(function () {

                });
        };
    }
]);

userModule.controller('ProviderDetailController', ['$scope',
    'item',
    'ProviderService',
    function ($scope, item, ProviderService) {
        $scope.item = item;
        $scope.authenticate = function (item) {
            ProviderService
                .authenticate(item)
                .success(function (data) {})
                .error(function (err) {

                });
        };
    }
]);
