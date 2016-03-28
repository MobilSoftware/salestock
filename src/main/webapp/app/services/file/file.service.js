'use strict';

angular.module('talarionApp')
    .factory('File', function($resource) {
        return $resource('api/file/:id', {}, {
            'update': {
                method: 'PUT'
            }
        });
    });
