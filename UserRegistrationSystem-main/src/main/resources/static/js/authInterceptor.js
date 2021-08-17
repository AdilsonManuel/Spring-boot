/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
app.factory('AuthInterceptor', [function () {
        return {
            'request': function (config) {
                config.headers = config.headers || {};
                var encodedString = btoa("admin:password");
                config.headers.Authorization = 'Basic ' + encodedString;
                return config;
            }
        };
    }]);