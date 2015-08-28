var infoModule = angular.module('Info', []);

infoModule.service('InfoService', ['$http', function ($http) {
    this.findAll = function (opt) {
        return $http.get('/admin/information', {
            params: opt
        });
    };
}]);

infoModule.controller('InfoController', ['$scope',
    'InfoService',
    function ($scope, InfoService) {
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
        }
    }
]);

infoModule.controller('InfoCreateController', ['$scope', function ($scope) {
    $scope.editorOptions = {
        uiColor: '#000000',
        filebrowserBrowseUrl: '/upload',
        filebrowserUploadUrl: '/img/upload?',
        width: 895,
        height: 300
    };
}]);
