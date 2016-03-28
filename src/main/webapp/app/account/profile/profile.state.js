(function() {
    'use strict';

    angular
        .module('talarionApp')
        .config(stateConfig);

    stateConfig.$inject = ['$stateProvider'];

    function stateConfig($stateProvider) {
        $stateProvider
            .state('profile', {
                parent: 'account',
                url: '/profile',
                data: {
                    authorities: ['ROLE_USER']
                },
                views: {
                    'content@': {
                        templateUrl: 'app/account/profile/profile.view.html',
                        controller: 'ProfileController'
                    }
                }
            });
    }
})();
