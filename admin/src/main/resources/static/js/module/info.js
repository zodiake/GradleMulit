var infoModule = angular.module('Info', []);

infoModule.service('InfoService', ['$http', function ($http) {
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
        return $http.get('/admin/information', {
            params: opt
        });
    };

    this.findOne = function (item) {
        return $http.get('/admin/informations/' + item.id);
    };

    this.save = function (item) {
        return $http({
            method: 'POST',
            url: '/admin/informations?form',
            transformRequest: transform,
            data: {
                title: item.title,
                category: item.category,
                content: item.content
            },
            headers: header
        });
    };

    this.update = function (item) {
        return $http({
            method: 'POST',
            url: '/admin/informations/' + item.id,
            transformRequest: transform,
            data: {
                title: item.title,
                category: item.category,
                content: item.content
            },
            headers: header
        });
    };
    
    this.updateState=function(item){
        var state=item.state=='activate'?'deactivate':'activate';
        return $http({
            method: 'POST',
            url: '/admin/informations/' + item.id+'/state',
            transformRequest: transform,
            data: {
                state:state
            },
            headers: header
        }); 
    };
}]);

infoModule.controller('InfoController', ['$scope',
    'InfoService',
    '$modal',
    function ($scope, InfoService, $modal) {
        $scope.page = 1;
        $scope.size = 15;
        $scope.state = "";

        function init(opt) {
            InfoService
                .findAll(opt)
                .success(function (data) {
                    $scope.items = data.content;
                })
                .error(function (err) {

                });
        }

        init({
            page: $scope.page,
            size: $scope.size,
            state: $scope.state
        });

        $scope.search = function () {
            init({
                page: $scope.page,
                size: $scope.size,
                state: $scope.state
            });
        };

        $scope.view = function (item) {
            InfoService
                .findOne(item)
                .success(function () {
                    $modal.open({
                        templateUrl: '/admin/templates/info/detail',
                        size: 'lg',
                        controller: 'InfoDetailController',
                        resolve: {
                            item: function () {
                                return item;
                            },
                            categories: function ($http) {
                                return $http.get('/admin/info/category');
                            }
                        }
                    });
                })
                .error(function (err) {

                });
        };
        
        $scope.updateState=function(item){
            InfoService
                .  
        };
    }
]);

infoModule.controller('InfoCreateController', ['$scope',
    'categories',
    'InfoService',
    function ($scope, categories, InfoService) {
        $scope.categories = categories.data.content;
        $scope.item = {};

        $scope.editorOptions = {
            uiColor: '#000000',
            filebrowserBrowseUrl: '/upload',
            filebrowserUploadUrl: '/admin/editor/img/upload',
            width: 895,
            height: 300
        };

        $scope.submit = function () {
            InfoService
                .save($scope.item)
                .success(function () {

                })
                .error(function (err) {

                });
        };

    }
]);

infoModule.controller('InfoDetailController', ['$scope',
    'item',
    'categories',
    'InfoService',
    function ($scope, item, categories, InfoService) {
        $scope.editorOptions = {
            uiColor: '#000000',
            filebrowserBrowseUrl: '/upload',
            filebrowserUploadUrl: '/admin/editor/img/upload',
            width: 795,
            height: 300
        };
        $scope.item = item;
        $scope.categories = categories.data.content;

        $scope.submit = function () {
            InfoService
                .update($scope.item)
                .success(function () {

                })
                .error(function (err) {

                });
        }
    }
]);
