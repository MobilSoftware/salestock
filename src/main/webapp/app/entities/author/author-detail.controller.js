(function() {
    'use strict';

    angular
        .module('talarionApp')
        .controller('AuthorDetailController', AuthorDetailController);

    AuthorDetailController.$inject = ['$scope', '$rootScope', '$stateParams', 'entity', 'Author', 'Book'];

    function AuthorDetailController($scope, $rootScope, $stateParams, entity, Author, Book) {
        var vm = this;
        vm.author = entity;

        var unsubscribe = $rootScope.$on('talarionApp:authorUpdate', function(event, result) {
            vm.author = result;
        });
        $scope.$on('$destroy', unsubscribe);

    }
})();
