'use strict';

angular.module('talarionApp')
    .factory('District', function($resource) {
        return $resource('api/district/:id', {}, {
            'update': {
                method: 'PUT'
            }
        });
    });
