/**
 * 
 */
var module=angular.module('SubjectCategory',[]);

module.service('SubjectCategoryService',['$http',function($http){
    this.findAll=function(){
        return $http.get('/admin/subject/category');  
    };
}]);
