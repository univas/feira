(function() {
'use strict';

angular.module('FairApp', [])

.controller('FairController', function($scope, $http) {

    $scope.email = null;
    $scope.age = null;
    $scope.autorizacao = true;

    $scope.courses = [
        {"label": 'Administração', "value": '0'},
        {"label": 'Bioética', "value": '20'},
        {"label": 'Ciências Biológicas', "value": '1'},
        {"label": 'Ciências Contábeis', "value": '2'},
        {"label": 'Ciências da Linguagem - Pós', "value": '19'},
        {"label": 'Educação Física', "value": '3'},
        {"label": 'Enfermagem', "value": '4'},
        {"label": 'Engenharia de Produção', "value": '5'},
        {"label": 'Farmácia', "value": '7'},
        {"label": 'Fisioterapia', "value": '8'},
        {"label": 'Gastronomia', "value": '9'},
        {"label": 'Gestão de Produção Industrial', "value": '10'},
        {"label": 'Gestão de Recursos Humanos', "value": '11'},
        {"label": 'História', "value": '12'},
        {"label": 'Letras', "value": '13'},
        {"label": 'Medicina', "value": '14'},
        {"label": 'Mestrado - Saúde', "value": '21'},
        {"label": 'Nutrição', "value": '6'},
        {"label": 'Pedagogia', "value": '15'},
        {"label": 'Pibid', "value": '22'},
        {"label": 'Psicologia', "value": '16'},
        {"label": 'Publicidade e Propaganda', "value": '17'},
        {"label": 'Sistemas de Informação', "value": '18'}
    ];

    $scope.sendData = function() {
        
        if (!$scope.email || $scope.email == '') {
            Materialize.toast('Por favor, preencha o campo e-mail', 6000);

        } else if (!$scope.age || $scope.age == '') {
            Materialize.toast('Por favor, preencha o campo idade', 6000);

        } else {
            var data = getSelectedCheckbox();
            if (data == '') {
                Materialize.toast('Por favor, selecione ao menos 1 curso', 6000);
            } else {

                data = 'email=' + $scope.email +
                       '&age=' + $scope.age +
                       '&permission=' + $scope.autorizacao +
                       data;
                
                sendDataAjax(data);
            }
        } 
    };

    function getSelectedCheckbox() {
        var str = '';
        var listOfCheckbox = document.getElementById('courses').getElementsByTagName('input');

        for (var i = 0; i < listOfCheckbox.length; i++) {
            var checkbox = listOfCheckbox[i];
            if (checkbox.checked) {
                str = str + '&list=' + checkbox.value;
            }
        }

        return str;
    };

    function sendDataAjax(data) {
        $('mydiv').show();
        var now = new Date();
        $http({
            method: 'GET',
            url: '/Voting/rest/vote/saveVotes?' + data + '&date=' + now.getTime()
        }).then(function successCallback(response) {
            $('mydiv').hide();
            Materialize.toast(response.data.message, 6000);
            cleanFields();

        }, function errorCallback(response) {
            $('mydiv').hide();
            Materialize.toast(response.data.message, 6000);
        });
    };

    function cleanFields() {
    	$scope.email = '';
    	$scope.age = '';
    	$scope.autorizacao = true;

    	var listOfCheckbox = document.getElementById('courses').getElementsByTagName('input');

        for (var i = 0; i < listOfCheckbox.length; i++) {
            var checkbox = listOfCheckbox[i];
            if (checkbox.checked) {
                checkbox.checked = false;
            }
        }
    };

});

})();