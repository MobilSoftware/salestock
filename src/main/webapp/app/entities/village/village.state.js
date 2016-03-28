(function() {
    'use strict';

    angular
        .module('talarionApp')
        .config(stateConfig);

    stateConfig.$inject = ['$stateProvider'];

    function stateConfig($stateProvider) {

        $stateProvider
            .state('village', {
                parent: 'entity',
                url: '/village',
                data: {
                    authorities: ['ROLE_USER']
                },
                views: {
                    'content@': {
                        templateUrl: 'app/entities/village/village-list.view.html',
                        controller: 'VillageController'
                    }
                }
            }).state('village-create', {
                parent: 'entity',
                url: '/village/create',
                data: {
                    authorities: ['ROLE_USER']
                },
                views: {
                    'content@': {
                        templateUrl: 'app/entities/village/village-create.view.html',
                        controller: 'VillageCreateController'
                    }
                }
            }).state('village-edit', {
                parent: 'entity',
                url: '/village/:id',
                data: {
                    authorities: ['ROLE_USER']
                },
                views: {
                    'content@': {
                        templateUrl: 'app/entities/village/village-edit.view.html',
                        controller: 'VillageEditController'
                    }
                }
            });

    }

})();
