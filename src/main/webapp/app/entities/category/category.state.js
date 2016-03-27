(function() {
    'use strict';

    angular
        .module('talarionApp')
        .config(stateConfig);

    stateConfig.$inject = ['$stateProvider'];

    function stateConfig($stateProvider) {

        $stateProvider
            .state('category', {
                parent: 'entity',
                url: '/category',
                data: {
                    authorities: ['ROLE_USER']
                },
                views: {
                    'content@': {
                        templateUrl: 'app/entities/category/category-list.view.html',
                        controller: 'CategoryController'
                    }
                }
            }).state('category-create', {
                parent: 'entity',
                url: '/category/create',
                data: {
                    authorities: ['ROLE_USER']
                },
                views: {
                    'content@': {
                        templateUrl: 'app/entities/category/category-create.view.html',
                        controller: 'CategoryCreateController'
                    }
                }
            }).state('category-edit', {
                parent: 'entity',
                url: '/category/:id',
                data: {
                    authorities: ['ROLE_USER']
                },
                views: {
                    'content@': {
                        templateUrl: 'app/entities/category/category-edit.view.html',
                        controller: 'CategoryEditController'
                    }
                }
            });

    }

})();
