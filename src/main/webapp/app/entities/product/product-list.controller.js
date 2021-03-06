'use strict';

angular.module('talarionApp')
    .controller('ProductController', function($scope, $state, Product, ParseLinks) {
        $scope.productList = [];
        $scope.page = 1;
        $scope.loadAll = function() {
            Product.query({
                page: $scope.page,
                per_page: 20
            }, function(result, headers) {
                $scope.links = ParseLinks.parse(headers('link'));
                $scope.productList = result;
            });
        };
        $scope.loadPage = function(page) {
            $scope.page = page;
            $scope.loadAll();
        };
        // $scope.loadAll();

        $scope.showUpdate = function(id) {
            Product.get({
                id: id
            }, function(result) {
                $scope.product = result;
                $('#saveProductModal').modal('show');
            });
        };

        $scope.save = function() {
            if ($scope.author.id != null) {
                Product.update($scope.product,
                    function() {
                        $scope.refresh();
                    });
            } else {
                Product.save($scope.product,
                    function() {
                        $scope.refresh();
                    });
            }
        };

        $scope.delete = function(id) {
            Product.get({
                id: id
            }, function(result) {
                $scope.product = result;
                $('#deleteProductConfirmation').modal('show');
            });
        };

        $scope.confirmDelete = function(id) {
            Product.delete({
                    id: id
                },
                function() {
                    $scope.loadAll();
                    $('#deleteProductConfirmation').modal('hide');
                    $scope.clear();
                });
        };

        $scope.refresh = function() {
            $scope.loadAll();
            $('#saveProductModal').modal('hide');
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
            var categoryNameSearchCrit = '';
            var productSizeNameSearchCrit = '';
            var productColorNameSearchCrit = '';

            if (tableState.search.predicateObject != undefined) {
                nameSearchCrit = tableState.search.predicateObject.name || '';
                categoryNameSearchCrit = tableState.search.predicateObject.categoryName || '';
                productSizeNameSearchCrit = tableState.search.predicateObject.productSizeName || '';
                productColorNameSearchCrit = tableState.search.predicateObject.productColorName || '';
            }

            Product.query({
                page: page,
                size: 10,
                name: nameSearchCrit,
                categoryName: categoryNameSearchCrit,
                productSizeName: productSizeNameSearchCrit,
                productColorName: productColorNameSearchCrit
            }, function(result, headers) {
                $scope.links = ParseLinks.parse(headers('link'));
                $scope.productList = result;
                tableState.pagination.numberOfPages = 1 * headers('X-Total-Pages');
                tableState.pagination.number = 1 * headers('X-Size');
                tableState.pagination.start = tableState.pagination.number * (page - 1);
                $scope.isLoading = false;

                console.log('tableState.pagination.start: ' + tableState.pagination.start);
                console.log('tableState.pagination.numberOfPages: ' + tableState.pagination.numberOfPages);
                console.log('tableState.pagination.number: ' + tableState.pagination.number);

                $state.go('product');
            });

        };
    });
