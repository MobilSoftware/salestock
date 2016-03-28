'use strict';

angular.module('talarionApp')
    .factory('Village', function($resource) {
        return $resource('api/village/:id', {}, {
            'update': {
                method: 'PUT'
            }
        });
    });
