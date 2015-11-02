var productModule = angular.module('Product', ['ProductCategory', 'Solution']);

productModule.service('ProductService', ['$http', function ($http) {

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
        return $http.get('/admin/products', {
            params: opt
        });
    };

    this.findOne = function (id) {
        return $http.get('/admin/products/' + id);
    };

    function updateState(id, state) {
        return $http({
            method: 'POST',
            url: '/admin/products/' + id + '/state',
            transformRequest: transform,
            data: {
                state: state
            },
            headers: header
        });
    }

    this.authenticate = function (id) {
        return $http({
            method: 'POST',
            url: '/admin/products/' + id + '/state',
            transformRequest: transform,
            data: {
                state: 'up'
            },
            headers: header
        });
    };

    this.refuse = function (id) {
        return updateState(id, 'not');
    };

    this.addSolution = function (id, solutions) {

        var adds = Object.keys(solutions).filter(function (s) {
            return solutions[s];
        });

        return $http({
            method: 'POST',
            url: '/admin/products/' + id + '/solutions',
            transformRequest: transform,
            data: {
                solutions: adds
            },
            headers: header
        });
    };
    this.addDisplay = function(id){
    	return $http({
    		method: 'POST',
    		url: '/admin/productDisplays/productId/'+id,
    		transformRequest: transform,
    		headers: header
    	});
    };
}]);

productModule.controller('ProductController', ['$scope',
    'ProductService',
    'ProductCategoryService',
    '$modal',
    function ($scope, ProductService, ProductCategoryService, $modal) {
        $scope.page = 1;
        $scope.size = 15;
        $scope.opt = {};

        $scope.opt.page = $scope.page;
        $scope.opt.size = $scope.size;

        function init(opt) {
            ProductService
                .findAll(opt)
                .success(function (data) {
                    $scope.items = data.content;
                    $scope.total = data.totalElements;
                });
            ProductCategoryService
                .findAll()
                .success(function (data) {
                    $scope.firstCategories = data;
                });
        }

        init($scope.opt);

        $scope.findByParent = function (category) {
            ProductCategoryService
                .findByParentId(category)
                .success(function (data) {
                    $scope.secondCategories = data;
                });
        };
        
        $scope.findThirdCategory=function(category){
            ProductCategoryService
                .findByParentId(category)
                .success(function (data) {
                    $scope.thirdCategories = data;
                });
        };

        $scope.search = function () {
            ProductService
                .findAll($scope.opt)
                .success(function (data) {
                    $scope.items = data.content;
                });
        };

        $scope.view = function (product) {
            $modal.open({
                templateUrl: '/admin/templates/products/detail',
                size: 'lg',
                controller: 'ProductDetailController',
                resolve: {
                    productId: function () {
                        return product.id;
                    },
                    solutions: function (SolutionService) {
                        return SolutionService.findAll();
                    }
                }
            });
        };
    }
]);

productModule.controller('ProductDetailController', ['$scope',
    'productId',
    '$sce',
    'ProductService',
    'solutions','$modalInstance',
    function ($scope, productId, $sce, ProductService, solutions,$modalInstance) {
        $scope.solutions = solutions.data;
        $scope.alerts = [];

        function init() {
            ProductService
                .findOne(productId)
                .success(function (data) {
                    $scope.item = data;
                    $scope.content = $sce.trustAsHtml($scope.item.content);
                    $scope.item.fake = {};
                    $scope.item.solutions.forEach(function (s) {
                        $scope.item.fake[s.id] = true;
                    });
                });
        }

        init();

        $scope.authenticate = function () {
            ProductService
                .authenticate($scope.item.id)
                .success(function () {
                    $scope.alerts.push({
                        type: 'success',
                        msg: '上架成功',
                    });
                })
                .error(function (err) {
                    $scope.alerts.push({
                        msg: '上架失败',
                    });
                });
        };

        $scope.refuse = function () {
            ProductService
                .refuse($scope.item.id)
                .success(function () {
                    $scope.alerts.push({
                        type: 'success',
                        msg: '已拒绝',
                    });
                })
                .error(function (err) {
                    $scope.alerts.push({
                        msg: '失败',
                    });
                });
        };

        $scope.addSolution = function () {
            ProductService
                .addSolution($scope.item.id, $scope.item.fake)
                .success(function () {
                    $scope.alerts.push({
                        type: 'success',
                        msg: '已关联',
                    });
                })
                .error(function (err) {
                    $scope.alerts.push({
                        msg: '关联失败',
                    });
                });
        };

        $scope.closeAlert = function (index) {
            $scope.alerts.splice(index, 1);
        };
        
        $scope.addDisplay = function(id){
        	ProductService.addDisplay(id).success(function(data){
        		if(data=="limit"){
        			$scope.alerts.push({
                    msg: '商品展示数量达到上限',
                    });
        		}else if(data=="success"){
        			$scope.alerts.push({
                        type: 'success',
                        msg: '操作成功',
                    });
        		}
        	}).error(function(data){
        		
        	})
        };
        $scope.closeModal = function(){
        	$modalInstance.close();
        }
    }
]);
