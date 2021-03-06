var subjectModule = angular.module('Subject', ['Solution']);
var validFailMessage = '校验失败';

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
                    summary: item.summary,
                    category: item.category,
                    createdBy: item.createdBy
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
                    summary: item.summary,
                    category: item.category,
                    createdBy: item.createdBy
                }
            });
        };

        this.saveOrUpdate = function (item) {
            if (item.id)
                return this.update(item);
            else
                return this.save(item);
        };

        this.updateState = function (item) {
            return $http({
                method: 'POST',
                url: '/admin/subject/' + item.id + '/state',
                transformRequest: transform,
                headers: header,
                data: {
                    active: item.active == 'ACTIVATE' ? 'deactivate' : 'activate'
                }
            });
        };
        
        this.showOnIndex = function(item){
        	return $http({
        		method : 'post',
        		url: '/admin/subject/'+item.id+'/showOnIndex',
        		transformRequest: transform,
                headers: header,
                data: {
                	showOnIndex: item.showOnIndex
                }
        	});
        }
    }
]);

subjectModule.controller('SubjectController', ['$scope',
    'SubjectService',
    '$modal',
    'categories',
    function ($scope, SubjectService, $modal, categories) {
        $scope.page = 1;
        $scope.size = 15;
        $scope.opt = {};

        $scope.categories = categories.data;

        function init(opt) {
            SubjectService
                .findAll(opt)
                .success(function (data) {
                    $scope.items = data.content;
                    $scope.total = data.totalElements;
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
                size: $scope.size,
                category: $scope.opt.category,
                activate: $scope.opt.activate
            });
        };

        $scope.updateState = function (item) {
            var modalInstance = $modal.open({
                animation: $scope.animationsEnabled,
                templateUrl: 'subjectModal.html',
                controller: 'SubjectModalCtrl',
                resolve: {
                    item: function () {
                        return item;
                    }
                }
            });
        };
    }
]);

subjectModule.controller('SubjectModalCtrl', [
    '$scope',
    '$modalInstance',
    'item',
    'SubjectService',
    function ($scope, $modalInstance, item, SubjectService) {
        $scope.item = item;
        $scope.ok = function () {
            SubjectService
                .updateState(item)
                .success(function (data) {
                    if (data == 'success') {
                        item.active = item.active == 'ACTIVATE' ? 'DEACTIVATE' : 'ACTIVATE';
                        $modalInstance.dismiss();
                    }
                }).error(function (err) {

                });
        };

        $scope.cancel = function () {
            $modalInstance.dismiss();
        };
    }
]);

subjectModule.controller('SubjectCreateController', ['$scope',
    'SubjectService',
    '$modal',
    '$http',
    'categories',
    function ($scope, SubjectService, $modal, $http, categories) {
        $scope.categories = categories.data;
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
            if ($scope.subjectForm.$valid) {
                SubjectService
                    .saveOrUpdate($scope.item)
                    .success(function (data) {
                        $scope.item.id = data.id;
                        $scope.alerts.push({
                            type: 'success',
                            msg: '保存成功'
                        });
                    })
                    .error(function (err) {
                        $scope.alerts.push({
                            msg: '保存失败'
                        });
                    });
            } else {
                $scope.alerts.push({
                    msg: validFailMessage
                });
            }
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
    'categories',
    function ($scope, SubjectService, $stateParams, $http, categories) {
        $scope.categories = categories.data;
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
            if ($scope.subjectForm.$valid) {
                SubjectService
                    .update($scope.item)
                    .success(function () {
                        $scope.alerts.push({
                            type: 'success',
                            msg: '保存成功'
                        });
                    })
                    .error(function (err) {
                        $scope.alerts.push({
                            msg: '保存失败'
                        });
                    });
            } else {
                $scope.alerts.push({
                    msg: validFailMessage
                });
            }
        };

        $scope.closeAlert = function (index) {
            $scope.alerts.splice(index, 1);
        };
        
        $scope.showOnIndex = function(item){
        	item.showOnIndex = item.showOnIndex == 'ACTIVATE' ? 'DEACTIVATE' : 'ACTIVATE';
        	console.log(item);
        	SubjectService.showOnIndex(item).success(function(data){
        		$scope.alerts.push({
                    type: 'success',
                    msg: '保存成功'
                });
        	}).error(function (err) {
                $scope.alerts.push({
                    msg: '保存失败'
                });
            });
        };
    }
]);