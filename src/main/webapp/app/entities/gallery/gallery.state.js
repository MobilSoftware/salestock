(function() {
    'use strict';

    angular
        .module('talarionApp')
        .config(stateConfig);

    stateConfig.$inject = ['$stateProvider'];

    function stateConfig($stateProvider) {

        $stateProvider
            .state('gallery', {
                parent: 'entity',
                url: '/gallery',
                data: {
                    authorities: ['ROLE_USER']
                },
                views: {
                    'content@': {
                        templateUrl: 'app/entities/gallery/gallery-list.view.html',
                        controller: 'GalleryListController'
                    }
                }
            }).state('gallery-create', {
                parent: 'entity',
                url: '/gallery/create',
                data: {
                    authorities: ['ROLE_USER']
                },
                views: {
                    'content@': {
                        templateUrl: 'app/entities/gallery/gallery-create.view.html',
                        controller: 'GalleryCreateController'
                    }
                }
            });

    }

})();
