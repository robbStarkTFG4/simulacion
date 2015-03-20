(function () {
    'use strict';
    var prueba = angular
            .module('app.prueba');
    prueba.controller('Shell', Shell);
    function Shell($state) {

        this.moveTo = function () {
            $state.go('state1');
            //console.log("hola");
        };

    }

    prueba.controller('nelva', nelva);
    function nelva($scope) {
        $scope.ford = function () {
            console.log("robbStark");
        };
    }
})();
