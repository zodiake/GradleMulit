/**
 * 
 */
var app = angular.module('app', [
    'ui.router',
    'ui.bootstrap',
    'ui.router.tabs',
    'filterModel',
    'upload',
    'ngCkeditor',
    'User',
    'Brand',
    'Advertise',
    'Info',
    'Subject',
    'Product',
    'Category',
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
            .state('advertise', {
                url: '/advertisement',
                templateUrl: '/admin/advertisement',
                controller: 'AdvertiseController'
            })
            .state('info', {
                url: '/info',
                templateUrl: '/admin/info',
                controller: 'InfoController'
            })
            .state('subject', {
                url: '/subject',
                templateUrl: '/admin/subject',
                controller: 'SubjectController'
            })
            .state('subjectCreate', {
                url: '/subjectCreate',
                templateUrl: '/admin/subject/create',
                controller: 'SubjectCreateController'
            })
            .state('subjectDetail', {
                url: '/subjectDetail/:id',
                templateUrl: function (stateParams) {
                    return '/admin/subjects/' + stateParams.id;
                },
                controller: 'SubjectEditController'
            })
            .state('solutions', {
                url: '/subject/:id/solutions',
                templateUrl: '/admin/templates/solutions',
                controller: 'SolutionController'
            })
            .state('products', {
                url: '/consumable',
                templateUrl: '/admin/templates/products',
                controller: 'ProductController'
            })
            .state('category', {
                url: '/category',
                templateUrl: '/admin/category',
                controller: 'CategoryController'
            })
            .state('childCategory', {
                url: '/:id/categories',
                templateUrl: '/admin/childCategory',
                controller: 'ChildCategoryController'
            });
    }
]);
