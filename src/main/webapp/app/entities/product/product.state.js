(function() {
    'use strict';

    angular
        .module('talarionApp')
        .config(stateConfig);

    stateConfig.$inject = ['$stateProvider'];

    function stateConfig($stateProvider) {

        $stateProvider
            .state('product', {
                parent: 'entity',
                url: '/product',
                data: {
                    authorities: ['ROLE_USER']
                },
                views: {
                    'content@': {
                        templateUrl: 'app/entities/product/product-list.view.html',
                        controller: 'ProductController'
                    }
                }
            }).state('product-create', {
                parent: 'entity',
                url: '/product/create',
                data: {
                    authorities: ['ROLE_USER']
                },
                views: {
                    'content@': {
                        templateUrl: 'app/entities/product/product-create.view.html',
                        controller: 'ProductCreateController'
                    }
                }
            }).state('product-edit', {
                parent: 'entity',
                url: '/product/:id',
                data: {
                    authorities: ['ROLE_USER']
                },
                views: {
                    'content@': {
                        templateUrl: 'app/entities/product/product-edit.view.html',
                        controller: 'ProductEditController'
                    }
                }
            });

    }

})();
