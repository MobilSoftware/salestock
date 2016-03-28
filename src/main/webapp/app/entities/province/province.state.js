(function() {
    'use strict';

    angular
        .module('talarionApp')
        .config(stateConfig);

    stateConfig.$inject = ['$stateProvider'];

    function stateConfig($stateProvider) {

        $stateProvider
            .state('province', {
                parent: 'entity',
                url: '/province',
                data: {
                    authorities: ['ROLE_USER']
                },
                views: {
                    'content@': {
                        templateUrl: 'app/entities/province/province-list.view.html',
                        controller: 'ProvinceController'
                    }
                }
            }).state('province-create', {
                parent: 'entity',
                url: '/province/create',
                data: {
                    authorities: ['ROLE_USER']
                },
                views: {
                    'content@': {
                        templateUrl: 'app/entities/province/province-create.view.html',
                        controller: 'ProvinceCreateController'
                    }
                }
            }).state('province-edit', {
                parent: 'entity',
                url: '/province/:id',
                data: {
                    authorities: ['ROLE_USER']
                },
                views: {
                    'content@': {
                        templateUrl: 'app/entities/province/province-edit.view.html',
                        controller: 'ProvinceEditController'
                    }
                }
            });

    }

})();
