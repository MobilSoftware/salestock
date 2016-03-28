(function() {
    'use strict';

    angular
        .module('talarionApp')
        .config(stateConfig);

    stateConfig.$inject = ['$stateProvider'];

    function stateConfig($stateProvider) {

        $stateProvider
            .state('document', {
                parent: 'entity',
                url: '/document',
                data: {
                    authorities: ['ROLE_USER']
                },
                views: {
                    'content@': {
                        templateUrl: 'app/entities/document/document-list.view.html',
                        controller: 'DocumentListController'
                    }
                }
            }).state('document-create', {
                parent: 'entity',
                url: '/document/create',
                data: {
                    authorities: ['ROLE_USER']
                },
                views: {
                    'content@': {
                        templateUrl: 'app/entities/document/document-create.view.html',
                        controller: 'DocumentCreateController'
                    }
                }
            });

    }

})();
