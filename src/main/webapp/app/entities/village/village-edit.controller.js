'use strict';

/**
 * @ngdoc function
 * @name yoAngularApp.controller:AboutCtrl
 * @description # AboutCtrl Controller of the yoAngularApp
 */
angular.module('talarionApp').controller('VillageEditController',
    function($scope, $state, $stateParams, Village, growl, SubDistrict, ParseLinks) {

        $scope.submitted = false;

        $scope.village = {};
        $scope.village.subDistrict = {};

        $scope.load = function(id) {
            Village.get({
                id: id
            }, function(result) {
                $scope.village = result;
            });
        };
        $scope.load($stateParams.id);

        $scope.save = function() {

            $scope.submitted = true;

            if ($scope.editForm.$valid) {

                Village.update($scope.village, function() {

                    growl.info("Village successfully edited ", {});

                    $state.go('village')
                });

            }

        };

        $scope.showSubDistrictLookup = function() {

            $('#subDistrictLookupDialog').modal('show');
        };

        $scope.selectSubDistrictLookup = function(subDistrict) {

            $scope.village.subDistrict = subDistrict;

            $('#subDistrictLookupDialog').modal('hide');
        };

        $scope.callServerSubDistrict = function callServerSubDistrict(tableState) {

            $scope.isLoading = true;

            var pagination = tableState.pagination;

            var start = pagination.start || 0; // This is NOT the page number, but the index of item in the list that you want to use to display the table.
            var number = pagination.number || 10; // Number of entries showed per page.
            var numberOfPages = pagination.numberOfPages || 1;
            var page = start / number + 1;

            var nameSearchCrit = '';

            if (tableState.search.predicateObject != undefined) {
                nameSearchCrit = tableState.search.predicateObject.name || '';
            }

            SubDistrict.query({
                page: page,
                per_page: 10,
                name: nameSearchCrit
            }, function(result, headers) {
                $scope.links = ParseLinks.parse(headers('link'));
                $scope.subDistrictList = result;
                tableState.pagination.numberOfPages = 1 * headers('X-Total-Pages');
                tableState.pagination.number = 1 * headers('X-Size');
                tableState.pagination.start = tableState.pagination.number * (page - 1);
                $scope.isLoading = false;

                // console.log('tableState.pagination.start: ' + tableState.pagination.start);
                // console.log('tableState.pagination.numberOfPages: ' + tableState.pagination.numberOfPages);
                // console.log('tableState.pagination.number: ' + tableState.pagination.number);

                // $state.go('setting-produk');
            });

        };

    });
