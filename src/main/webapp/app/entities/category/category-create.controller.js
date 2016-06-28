'use strict';

/**
 * @ngdoc function
 * @name yoAngularApp.controller:AboutCtrl
 * @description # AboutCtrl Controller of the yoAngularApp
 */
angular.module('talarionApp').controller('CategoryCreateController',
    function($scope, $state, $stateParams, Category, growl) {

      $scope.submitted = false;

        $scope.category = {};
        $scope.category.parent = {};

        $scope.create = function() {

            $scope.submitted = true;

            if ($scope.createForm.$valid) {

                Category.save($scope.category, function() {

                    growl.info("Category successfully added ", {});

                    $state.go('category')
                });

            }

        };

        $scope.selectCategoryAc = function(selected) {

            // console.log(JSON.stringify(selected));

            $scope.category.parent = selected.originalObject;

        }

    });
