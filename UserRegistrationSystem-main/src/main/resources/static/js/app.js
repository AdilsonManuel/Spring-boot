/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

var app = angular.module("userregistrationsystem", ["ngRoute", "ngResource"]);

app.config(function ($routeProvider)
{
    $routeProvider
            .when("/list-all-users", {
                templateUrl: "/template/listuser.html",
                controller: "listUserController",
            })
            .when("/register-new-user", {
                templateUrl: "/template/userregistration.html",
                controller: "registerUserController", })
            .when("/update-user/:id", {
                templateUrl: "/template/userupdate.html",
                controller: "userDetailsController", })
            .otherwise({
                redirectTo: '/home',
                templateUrl: '/template/home.html',
            });

});

app.config(['$httpProvider',function ($httpProvider) {
        $httpProvider.interceptors.push('AuthInterceptor')
    }]);