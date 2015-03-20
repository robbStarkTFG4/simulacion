(function () {
    'use strict';

    angular.module('app', [
        /*
         * Order is not important. Angular makes a
         * pass to register all of the modules listed
         * and then when app.dashboard tries to use app.data,
         * it's components are available.
         */

        /*
         * Everybody has access to these.
         * We could place these under every feature area,
         * but this is easier to maintain.
         */
        'app.core',
        'app.prueba'

    ]).config(rutas);
    function rutas($stateProvider, $urlRouterProvider) {
        //
        // For any unmatched url, redirect to /state1
      //  $urlRouterProvider.otherwise('/view2');
        //
        // Now set up the states
        $stateProvider
                .state('state1', {
                    url: '/view1',
                    templateUrl: 'app/prueba/prueba.html',
                    controller:'nelva'
                }).state('state2', {
            url: '/view2',
            templateUrl: 'index.html'
        });
    }
    ;
})();