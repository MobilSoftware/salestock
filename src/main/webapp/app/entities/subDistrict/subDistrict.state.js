(function() {
    'use strict';

    angular
        .module('talarionApp')
        .config(stateConfig);

    stateConfig.$inject = ['$stateProvider'];

    function stateConfig($stateProvider) {

        $stateProvider
            .state('subDistrict', {
                parent: 'entity',
                url: '/subDistrict',
                data: {
                    authorities: ['ROLE_USER']
                },
                views: {
                    'content@': {
                        templateUrl: 'app/entities/subDistrict/subDistrict-list.view.html',
                        controller: 'SubDistrictController'
                    }
                }
            }).state('subDistrict-create', {
                parent: 'entity',
                url: '/subDistrict/create',
                data: {
                    authorities: ['ROLE_USER']
                },
                views: {
                    'content@': {
                        templateUrl: 'app/entities/subDistrict/subDistrict-create.view.html',
                        controller: 'SubDistrictCreateController'
                    }
                }
            }).state('subDistrict-edit', {
                parent: 'entity',
                url: '/subDistrict/:id',
                data: {
                    authorities: ['ROLE_USER']
                },
                views: {
                    'content@': {
                        templateUrl: 'app/entities/subDistrict/subDistrict-edit.view.html',
                        controller: 'SubDistrictEditController'
                    }
                }
            });

    }

})();
