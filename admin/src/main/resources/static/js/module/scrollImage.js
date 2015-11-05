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
    this.save = function(item){
    	return $http({
    		method: 'POST',
            url: '/admin/scrollImages/',
            transformRequest: transform,
            data: {
                href: item.href,
                imageUrl: item.imageUrl
            },
            headers: header
    	});
    };
    this.updateState = function(item){
    	return $http({
    		method : 'POST',
    		url : '/admin/scrollImages/'+item.id+'/state',
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
        $scope.create = function(){
        	$modal.open({
                templateUrl: '/admin/templates/scrollImg/detail',
                size: 'sm',
                controller: 'ScrollImgAddController'
            });
        };
        $scope.updateState = function(item){
        	ScrollService.updateState(item).success(function(data){
        		if(item.state=="DEACTIVATE")
   				 	item.state = "ACTIVATE";
        		else
   				 	item.state="DEACTIVATE";
        	}).error(function(data){
        		
        	});
        }
    }
]);

module.controller('ScrollImgDetailController', ['$scope',
    'item',
    '$http',
    'ScrollService','$modalInstance',
    function ($scope, item, $http, ScrollService,$modalInstance) {
        $scope.item = item;
        $scope.alerts = [];

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
        
        $scope.closeModal = function(){
        	$modalInstance.dismiss();
        };

        $scope.submit = function () {
            ScrollService
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

module.controller('ScrollImgAddController', ['$scope','ScrollService','$http','$modalInstance',
    function ($scope, ScrollService , $http , $modalInstance) {
	$scope.item = {};
	$scope.alerts = [];
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
    $scope.submit = function(){
    	ScrollService.save($scope.item).success(function(data){
    		$scope.alerts.push({
                type: 'success',
                msg: '保存成功',
            });
    	}).error(function(data){
    	});
    }
}
]);
