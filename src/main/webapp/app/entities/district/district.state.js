(function() {
    'use strict';

    angular
        .module('talarionApp')
        .config(stateConfig);

    stateConfig.$inject = ['$stateProvider'];

    function stateConfig($stateProvider) {

        $stateProvider
            .state('district', {
                parent: 'entity',
                url: '/district',
                data: {
                    authorities: ['ROLE_USER']
                },
                views: {
                    'content@': {
                        templateUrl: 'app/entities/district/district-list.view.html',
                        controller: 'DistrictController'
                    }
                }
            }).state('district-create', {
                parent: 'entity',
                url: '/district/create',
                data: {
                    authorities: ['ROLE_USER']
                },
                views: {
                    'content@': {
                        templateUrl: 'app/entities/district/district-create.view.html',
                        controller: 'DistrictCreateController'
                    }
                }
            }).state('district-edit', {
                parent: 'entity',
                url: '/district/:id',
                data: {
                    authorities: ['ROLE_USER']
                },
                views: {
                    'content@': {
                        templateUrl: 'app/entities/district/district-edit.view.html',
                        controller: 'DistrictEditController'
                    }
                }
            });

    }

})();
