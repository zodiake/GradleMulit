/**
 * 
 */
var app = angular.module('app', [
    'ui.router',
    'ui.bootstrap',
    'ui.router.tabs',
    'User',
    'Brand',
    'Advertise'
]);

app.config([
    '$stateProvider',
    '$urlRouterProvider',
    '$httpProvider',
    function ($stateProvider, $urlRouterProvider, $httpProvider) {
        $urlRouterProvider.otherwise("/commonUser");

        $stateProvider
            .state('commonUser', {
                url: '/commonUser',
                templateUrl: '/admin/commonUser',
                controller: 'CommonUserController'
            })
            .state('provider', {
                url: '/provider',
                templateUrl: '/admin/provider',
                controller: 'ProviderController'
            })
            .state('brand', {
                url: '/brands',
                templateUrl: '/admin/brand',
                controller: 'BrandController'
            })
            .state('brandDetail', {
                url: '/brandDetail',
                templateUrl: '/admin/brandDetail',
                controller: 'BrandDetailController'
            })
            .state('advertise', {
                url: '/advertisement',
                templateUrl: '/admin/advertisement',
                controller: 'AdvertiseController'
            })
            .state('advertiseDetail', {
                url: '/advertisement/:id',
                templateUrl: '/admin/createAdvertise',
                controller: 'AdvertiseDetailController',
                resolve: {
                    category: function ($http) {
                        return $http.get('/admin/advertise/category');
                    }
                }
            })
            .state('createAdvertise', {
                url: '/createAdvertise',
                templateUrl: '/admin/createAdvertise',
                controller: 'CreateAdvertiseController',
                resolve: {
                    category: function ($http) {
                        return $http.get('/admin/advertise/category');
                    }
                }
            })
            .state('products', {
                url: '/products',
                templateUrl: '/admin/products',
                controller: 'ProductsController'
            });
    }
]);
