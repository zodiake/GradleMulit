var subjectModule = angular.module('Subject', []);

subjectModule.service('SolutionService', ['$http', function ($http) {
    function transform(obj) {
        var str = [];
        for (var p in obj)
            str.push(encodeURIComponent(p) + "=" + encodeURIComponent(obj[p]));
        return str.join("&");
    }

    var header = {
        'Content-Type': 'application/x-www-form-urlencoded'
    };

    this.findAll = function (id) {
        return $http.get('/admin/subjects/' + id + '/solutions');
    };

    this.updateName = function (item) {
        return $http({
            method: 'POST',
            url: '/admin/solutions/' + item.id,
            transformRequest: transform,
            data: {
                name: item.name,
            },
            headers: header
        });
    };

    this.delete = function (id) {
        return $http.delete('/admin/solutions/' + id);
    };
    
    this.save=function(item){
       return $http({
            method: 'POST',
            url: '/admin/subjects/'+item.subject+'/solutions/',
            transformRequest: transform,
            data: {
                name: item.name,
            },
            headers: header
       }); 
    };
}]);

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
                    solution: solutions
                },
                headers: header
            });
        };
    }
]);

subjectModule.controller('SubjectController', ['$scope', 'SubjectService',
    function ($scope, SubjectService, $modal) {
        $scope.page = 1;
        $scope.size = 15;

        function init(opt) {
            SubjectService.findAll(opt).success(function (data) {
                $scope.items = data.content;
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
    }
]);

subjectModule.controller('SubjectCreateController', ['$scope', 'SubjectService', '$modal',
    function ($scope, SubjectService, $modal) {
        $scope.item = {
            solutions: []
        };

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

        $scope.addSolution = function () {
            $modal.open({
                templateUrl: '/admin/solutions?form',
                size: 'sm',
                controller: 'SolutionCreateController',
                scope: $scope
            });
        };

        $scope.deleteSolution = function (index) {
            $scope.item.solutions.splice(index);
        };

        $scope.submit = function () {
            SubjectService.save($scope.item).success(function () {

            });
        };
    }
]);

subjectModule.controller('SubjectEditController', ['$scope', 'SubjectService',
    function ($scope, SubjectService) {
        $scope.editorOptions = {
            uiColor: '#000000',
            filebrowserBrowseUrl: '/upload',
            filebrowserUploadUrl: '/admin/editor/img/upload',
            width: 895,
            height: 300
        };
    }
]);

subjectModule.controller('SolutionCreateController',['$scope',function($scope){
    $scope.solution={};

    $scope.add=function(solution){
        $scope.item.solutions.push($scope.solution);
    };
}]);

subjectModule.controller('SolutionController', ['$scope',
    '$stateParams',
    'SolutionService',
    '$modal',
    function ($scope, $stateParams, SolutionService,$modal) {
        var id = $stateParams.id;
        $scope.items = {};
        $scope.solution={subject:id};


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
                .findAll(id)
                .success(function (data) {
                    $scope.items = data;
                })
                .error(function (err) {

                });
        }

        init(id);
        
        $scope.add=function(s){
            SolutionService
                .save($scope.solution)
                .success(function(){
                    $scope.items.push({name:$scope.solution.name});   
                })
                .error(function(err){
                    
                });
        };
    }
]);
