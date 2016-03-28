'use strict';

angular.module('talarionApp')
    .factory('SubDistrict', function($resource) {
        return $resource('api/subDistrict/:id', {}, {
            'update': {
                method: 'PUT'
            }
        });
    });
