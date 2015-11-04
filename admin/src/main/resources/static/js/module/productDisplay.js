var displayModule = angular.module('Display', ['Product','Solution']);

displayModule.service('DisplayService', [ '$http', function($http) {
	
	function transform(obj) {
        var str = [];
        for (var p in obj)
            str.push(encodeURIComponent(p) + "=" + encodeURIComponent(obj[p]));
        return str.join("&");
    }

    var header = {
        'Content-Type': 'application/x-www-form-urlencoded'
    };
	
	this.findAll = function(opt){
			return $http.get('/admin/productDisplays', {
                params: opt
            });
	};
	this.update = function(id,state){
		 return $http({
	            method: 'POST',
	            url: '/admin/productDisplays/' + id + '/state',
	            transformRequest: transform,
	            data: {
	                state: state
	            },
	            headers: header
	        });
	}
} ]);

displayModule.controller('DisplayController', [ '$scope', 'DisplayService',
		'$modal','ProductService', function($scope, DisplayService, $modal,ProductService) {
	 $scope.page = 1;
     $scope.size = 15;
     
     $scope.items = [];
     $scope.alerts = [];
     
     function init(opt){
    	 DisplayService.findAll(opt).success(function(data){
    		 $scope.items = data.content;
    		 $scope.total = data.totalElements;
    	 }).error(function(data){
    		 
    	 });
     }
     init({
    	 page: $scope.page,
         size: $scope.size
     });
     
     $scope.productDetail = function(id){
    	 var productDetailModal = $modal.open({
             templateUrl: '/admin/templates/productDisplay/detail',
             size: 'lg',
             controller: 'ProductDetailController',
             resolve: {
                 productId: function () {
                     return id;
                 },
                 solutions: function (SolutionService) {
                     return SolutionService.findAll();
                 }
             }
         });
     };
     
     $scope.search = function(current){
    	 DisplayService.findAll({
        	 page: current,
             size: $scope.size
         }).success(function(data){
    		 $scope.items = data.content;
    		 $scope.total = data.totalElements;
    	 }).error(function(data){
    		 
    	 });
     };
     $scope.update=function(item){
    	 DisplayService.update(item.id,item.state).success(function(data){
    		 if(data=="success"){
    			 if(item.state=="DEACTIVATE")
    				 item.state = "ACTIVATE";
    			 else
    				 item.state="DEACTIVATE";
    			 $scope.alerts.push({
    				 type: 'success',
    				 msg: '操作成功',
    			 });
    		 }else if(data=="limit"){
    			 $scope.alerts.push({
    				 msg: '商品展示数量达到上限',
    			 });
    		 }
    	 }).error(function(data){
    		 
    	 });
     };
     $scope.closeAlert = function (index) {
         $scope.alerts.splice(index, 1);
     };
} ]);