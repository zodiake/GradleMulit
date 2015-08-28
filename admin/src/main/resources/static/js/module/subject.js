var subjectModule = angular.module('Subject', []);

subjectModule.service('SubjectService', ['$http', function ($http) {

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
        return $http.get('/admin/subjects', {
            params: opt
        });
    };

    this.save = function (item) {
        return $http({
            method: 'POST',
            url: '/admin/subjects',
            transformRequest: transform,
            data: {
                name: item.title,
                content: item.content
            },
            headers: header
        });
    };
}]);

subjectModule.controller('SubjectController', ['$scope',
    'SubjectService',
    function ($scope, SubjectService, $modal) {
        $scope.page = 1;
        $scope.size = 15;

        function init(opt) {
            SubjectService
                .findAll(opt)
                .success(function (data) {
                    $scope.items = data.content;
                })
                .error(function (err) {

                });
        }

        init({
            page: $scope.page,
            size: $scope.size
        });

        $scope.search = function () {
            init({
                page: $scope.page,
                size: $scope.size
            });
        };
    }
]);

subjectModule.controller('SubjectCreateController', ['$scope',
    'SubjectService',
    function ($scope, SubjectService) {

        $scope.editorOptions = {
            uiColor: '#000000',
            filebrowserBrowseUrl: '/upload',
            filebrowserUploadUrl: '/admin/editor/img/upload',
            width: 895,
            height: 300
        };

        $scope.submit = function () {
            SubjectService.save($scope.item);
        };

    }
]);
