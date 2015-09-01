/**
 * 
 */
var app = angular.module('app', [
    'ui.router',
    'ui.bootstrap',
    'ui.router.tabs',
    'upload',
    'ngCkeditor',
    'User',
    'Brand',
    'Advertise',
    'Info',
    'Subject',
    'Product'
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
            .state('infoCreate', {
                url: '/newInfo',
                templateUrl: '/admin/info?form',
                controller: 'InfoCreateController',
                resolve: {
                    categories: ['$http', function ($http) {
                        return $http.get('/admin/info/category');
                    }]
                }
            })
            .state('infoDetail', {
                url: '/info/:id',
                templateUrl: function (stateParams) {
                    return '/admin/informations/' + stateParams.id;
                },
                controller: 'InfoDetailController'
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
                templateUrl: '/admin/solutions',
                controller: 'SolutionController'
            })
            .state('products', {
                url: '/consumable',
                templateUrl: '/admin/templates/products',
                controller: 'ProductController'
            });
    }
]);
