(function() {
    'use strict';

    angular
        .module('talarionApp')
        .config(alertServiceConfig);

    alertServiceConfig.$inject = ['growlProvider'];

    function alertServiceConfig(growlProvider) {

        growlProvider.globalTimeToLive(3000);

    }
})();
