'use strict';

angular.module('talarionApp')
    .controller('ProvinceController', function($scope, $state, Province, ParseLinks, growl) {
        $scope.provinceList = [];
        $scope.page = 1;
        $scope.loadAll = function() {
            Province.query({
                page: $scope.page,
                per_page: 20
            }, function(result, headers) {
                $scope.links = ParseLinks.parse(headers('link'));
                $scope.provinceList = result;
            });
        };
        $scope.loadPage = function(page) {
            $scope.page = page;
            $scope.loadAll();
        };
        // $scope.loadAll();

        $scope.showUpdate = function(id) {
            Province.get({
                id: id
            }, function(result) {
                $scope.province = result;
                $('#saveProvinceModal').modal('show');
            });
        };

        $scope.save = function() {
            if ($scope.author.id != null) {
                Province.update($scope.province,
                    function() {
                        $scope.refresh();
                    });
            } else {
                Province.save($scope.province,
                    function() {
                        $scope.refresh();
                    });
            }
        };

        $scope.delete = function(id) {
            Province.get({
                id: id
            }, function(result) {
                $scope.province = result;
                $('#deleteProvinceConfirmation').modal('show');
            });
        };

        $scope.confirmDelete = function(id) {
            Province.delete({
                    id: id
                },
                function() {
                    $scope.loadAll();
                    $('#deleteProvinceConfirmation').modal('hide');
                    $scope.clear();
                    growl.info("Province successfully deleted ", {});
                });
        };

        $scope.refresh = function() {
            $scope.loadAll();
            $('#saveProvinceModal').modal('hide');
            $scope.clear();
        };

        $scope.clear = function() {

        };

        $scope.callServer = function callServer(tableState) {

            $scope.isLoading = true;

            var pagination = tableState.pagination;

            var start = pagination.start || 0; // This is NOT the page number, but the index of item in the list that you want to use to display the table.
            var number = pagination.number || 10; // Number of entries showed per page.
            var numberOfPages = pagination.numberOfPages || 1;
            var page = start / number + 1;

            var nameSearchCrit = '';

            if(tableState.search.predicateObject != undefined){
              nameSearchCrit = tableState.search.predicateObject.name || '';
            }

            Province.query({
                page: page,
                per_page: 10,
                name: nameSearchCrit
            }, function(result, headers) {
                $scope.links = ParseLinks.parse(headers('link'));
                $scope.provinceList = result;
                tableState.pagination.numberOfPages = 1 * headers('X-Total-Pages');
                tableState.pagination.number = 1 * headers('X-Size');
                tableState.pagination.start = tableState.pagination.number * (page - 1);
                $scope.isLoading = false;

                console.log('tableState.pagination.start: ' + tableState.pagination.start);
                console.log('tableState.pagination.numberOfPages: ' + tableState.pagination.numberOfPages);
                console.log('tableState.pagination.number: ' + tableState.pagination.number);

                $state.go('province');
            });

        };
    });
