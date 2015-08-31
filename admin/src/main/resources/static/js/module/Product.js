/**
 * 
 */
var productModule=angular.module('Product',[]);

productModule.service('ProductService',['$http',function($http){
    this.findAll=function(opt){
        $http.get('/admin/products')  
    };    
}]);

productModule.controller('ProductController',['$scope',function($scope){
    
}]);

