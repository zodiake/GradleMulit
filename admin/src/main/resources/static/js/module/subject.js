var subjectModule = angular.module('Subject', ['Solution']);

subjectModule.service('SubjectService', ['$http',
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
            return $http.get('/admin/subjects', {
                params: opt
            });
        };

        this.findOne = function (id) {
            return $http.get('/admin/subjects/' + id);
        };

        this.save = function (item) {
            var solutions = item.solutions.map(function (d) {
                return d.name;
            });
            return $http({
                method: 'POST',
                url: '/admin/subjects',
                transformRequest: transform,
                data: {
                    name: item.title,
                    content: item.content,
                    solution: solutions,
                    image: item.cover,
                    summary: item.summary
                },
                headers: header
            });
        };

        this.update = function (item) {
            return $http({
                method: 'POST',
                url: '/admin/subjects/' + item.id,
                transformRequest: transform,
                headers: header,
                data: {
                    name: item.name,
                    content: item.content,
                    image: item.cover,
                    summary: item.summary
                }
            });
        };

        this.saveOrUpdate = function (item) {
            if (item.id)
                return this.update(item);
            else
                return this.save(item);
        };
    }
]);

subjectModule.controller('SubjectController', ['$scope',
    'SubjectService',
    '$modal',
    function ($scope, SubjectService, $modal) {
        $scope.page = 1;
        $scope.size = 15;

        function init(opt) {
            SubjectService
                .findAll(opt)
                .success(function (data) {
                    $scope.items = data.content;
                    $scope.total = data.totalElements;
                }).error(function (err) {

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

        $scope.view = function (item) {};
    }
]);

subjectModule.controller('SubjectCreateController', ['$scope', 'SubjectService', '$modal', '$http',
    function ($scope, SubjectService, $modal, $http) {
        $scope.item = {
            solutions: []
        };
        $scope.alerts = [];

        $scope.editorOptions = {
            uiColor: '#000000',
            filebrowserBrowseUrl: '/upload',
            filebrowserUploadUrl: '/admin/editor/img/upload',
            width: 795,
            height: 300
        };

        $scope.submit = function () {
            SubjectService
                .saveOrUpdate($scope.item)
                .success(function (data) {
                    $scope.item.id = data.id;
                    $scope.alerts.push({
                        type: 'success',
                        msg: '保存成功',
                    });
                })
                .error(function (err) {
                    $scope.alerts.push({
                        msg: '保存失败',
                    });
                });
        };

        $scope.addSolution = function () {
            $modal.open({
                templateUrl: '/admin/solutions?form',
                size: 'sm',
                controller: 'SolutionCreateController',
                scope: $scope
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
                $scope.item.cover = data[0];
            });
        };

        $scope.deleteSolution = function (index) {
            $scope.item.solutions.splice(index, 1);
        };

        $scope.closeAlert = function (index) {
            $scope.alerts.splice(index, 1);
        };
    }
]);

subjectModule.controller('SubjectEditController', ['$scope',
    'SubjectService',
    '$stateParams',
    '$http',
    function ($scope, SubjectService, $stateParams,$http) {
        $scope.alerts = [];

        function init() {
            SubjectService
                .findOne($stateParams.id)
                .success(function (item) {
                    $scope.item = item;
                });
        }

        init();

        $scope.editorOptions = {
            uiColor: '#000000',
            filebrowserBrowseUrl: '/upload',
            filebrowserUploadUrl: '/admin/editor/img/upload',
            width: 700,
            height: 300
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
                $scope.item.cover = data[0];
            });
        };

        $scope.submit = function () {
            SubjectService
                .update($scope.item)
                .success(function () {
                    $scope.alerts.push({
                        type: 'success',
                        msg: '保存成功',
                    });
                })
                .error(function (err) {
                    $scope.alerts.push({
                        msg: '保存失败',
                    });
                });
        };

        $scope.closeAlert = function (index) {
            $scope.alerts.splice(index, 1);
        };
    }
]);

subjectModule.controller('SolutionCreateController', ['$scope', function ($scope) {
    $scope.solution = {};

    $scope.add = function (solution) {
        $scope.item.solutions.push($scope.solution);
    };
}]);

subjectModule.controller('SolutionController', ['$scope',
    '$stateParams',
    'SolutionService',
    '$modal',
    function ($scope, $stateParams, SolutionService, $modal) {
        var id = $stateParams.id;
        $scope.items = {};
        $scope.solution = {
            subject: id
        };

        $scope.update = function (item) {
            SolutionService
                .updateName(item)
                .success(function (data) {

                })
                .error(function (err) {

                });
        };

        $scope.delete = function (id) {

        };

        function init() {
            SolutionService
                .findBySubject(id)
                .success(function (data) {
                    $scope.items = data;
                })
                .error(function (err) {

                });
        }

        init(id);

        $scope.add = function (s) {
            SolutionService
                .save($scope.solution)
                .success(function (data) {
                    $scope.items.push({
                        name: $scope.solution.name,
                        id: data.id
                    });
                })
                .error(function (err) {

                });
        };
    }
]);
