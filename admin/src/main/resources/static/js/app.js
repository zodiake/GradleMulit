/**
 * 
 */
var app = angular.module('app', [
    'ui.router',
    'ui.bootstrap',
    'ui.router.tabs',
    'User',
    'Brand'
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
            .state('advertise',{
                url:'/advertisement',
                templateUrl:'/admin/advertisement',
                controller:'AdvertiseController'
            })
            .state('products', {
                url: '/products',
                templateUrl: '/admin/products',
                controller: 'ProductsController'
            });
    }
]);
