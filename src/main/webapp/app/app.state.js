(function() {
    'use strict';

    angular
        .module('talarionApp')
        .config(stateConfig);

    stateConfig.$inject = ['$stateProvider'];

    function stateConfig($stateProvider) {

        // $stateProvider.state('app', {
        //     abstract: true,
        //     views: {
        //         'navbar@': {
        //             templateUrl: 'app/layouts/navbar/navbar.html',
        //             controller: 'NavbarController',
        //             controllerAs: 'vm'
        //         }
        //     },
        //     resolve: {
        //         authorize: ['Auth',
        //             function(Auth) {
        //                 return Auth.authorize();
        //             }
        //         ],
        //         translatePartialLoader: ['$translate', '$translatePartialLoader', function($translate, $translatePartialLoader) {
        //             $translatePartialLoader.addPart('global');
        //         }]
        //     }
        // });

        $stateProvider.state('app', {
            abstract: true,
            views: {
                'sidebar-left@': {
                    templateUrl: 'app/layouts/sidebar-left/sidebar-left.html',
                    controller: 'SidebarLeftController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                authorize: ['Auth',
                    function(Auth) {
                        return Auth.authorize();
                    }
                ],
                translatePartialLoader: ['$translate', '$translatePartialLoader', function($translate, $translatePartialLoader) {
                    $translatePartialLoader.addPart('global');
                }]
            }
        });
    }
})();
