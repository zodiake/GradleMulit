var category = angular.module('Category', []);

category.service('CategoryService', ['$http',
    function($http) {
		this.findAll = function(id) {
	        return $http.get('/admin/product/categories');
	    };
	    this.save = function(item) {
	        return $http.post('/admin/product/categories', item);
	    };

    }
]);

category.controller('CategoryController', [
      '$scope',
      'CategoryService',
      function($scope, CategoryService) {
    	  CategoryService
              .findAll()
              .then(function(data) {
            	  console.log(data);
                  $scope.yq = data.data['0'].categories;
                  $scope.sj = data.data['1'].categories;
                  $scope.hc = data.data['2'].categories;
                  $scope.fw = data.data['3'].categories;
              });
      	}
 ]);

category.controller('CategoryAddController', [
      '$scope',
      'CategoryService',
      function($scope, CategoryService) {
            
    	  $scope.item = {};
          $scope.alerts = [];

          $scope.submit = function() {
        	  CategoryService
                  .save($scope.item)
                  .success(function(data) {
                	  console.log(data);
                      $scope.alerts.push({
                          type: 'success',
                          msg: '添加成功'
                      });
                  })
                  .error(function(err) {
                      $scope.alerts.push({
                          type: 'fail',
                          msg: '添加失败'
                      });
                  });
          };
    	  
      }
  ]);