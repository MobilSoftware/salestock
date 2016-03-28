'use strict';

angular.module('talarionApp')
    .factory('Province', function($resource) {
        return $resource('api/province/:id', {}, {
            'update': {
                method: 'PUT'
            }
        });
    });
