/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

app.controller('registerUserController', function ($scope, $http, $location, $route)
{
    $scope.submitUserForm = function () {
        $http({
            method: 'POST',
            url: 'http://localhost:8080/api/user/',
            data: $scope.user,
        }).then(function (response) {
            $location.path("/list-all-users");
            $route.reload();
        }, function (errResponse)
        {
            $scope.errorMessage = errResponse.data.errorMessage;
        });
    }

    $scope.resetForm = function () {
        $scope.user = null;
    }
});

app.controller('listUserController', function ($scope, $http, $location, $route) {
    $http({
        method: 'GET',
        url: 'http://localhost:8080/api/user/'
    }).then(function (response) {
        $scope.users = response.data;
    });

    $scope.editUser = function (userID) {
        $location.path("/update-user/" + userID);
    }

    $scope.deleteUser = function (userID) {
        $http({
            method: 'DELETE',
            url: 'http://localhost:8080/api/user/' + userID
        }).then(function (response) {
            $location.path("/list-all-users");
            $route.reload();
        });
    }
});

app.controller('userDetailsController', function ($scope, $http, $location, $routeParams, $route) {
    $scope.userID = $routeParams.id;

    $http({
        method: 'GET',
        url: 'http://localhost:8080/api/user/' + $scope.userID
    }).then(function (response) {
        $scope.user = response.data;
    });

    $scope.submitUserForm = function () {
        $http({
            method: 'POST',
            url: 'http://localhost:8080/api/user/',
            data: $scope.user,
        }).then(
                function (response) {
                    $location.path("/list-all-users");
                    $route.reload();
                },
                function (errResponse) {
                    $scope.errorMessage = "Error while updating User - Error Message: " + errResponse.data.errorMessage;
                }
        )
    }

})

