(function() {
    'use strict';

    angular
        .module('talarionApp')
        .controller('SidebarLeftController', SidebarLeftController);

    SidebarLeftController.$inject = ['$location', '$state', 'Auth', 'Principal', 'LoginService','$rootScope'];

    function SidebarLeftController($location, $state, Auth, Principal, LoginService, $rootScope) {
        var vm = this;

        vm.navCollapsed = true;
        vm.isAuthenticated = Principal.isAuthenticated;
        vm.login = login;
        vm.logout = logout;
        vm.$state = $state;

        // Define $rootScope.account if html is refreshed
        Principal.identity().then(function(account) {
            $rootScope.account = account;
            console.log(JSON.stringify(account))
        });

        function login() {
            LoginService.open();
        }

        function logout() {
            Auth.logout();
            $state.go('home');
        }
    }
})();
