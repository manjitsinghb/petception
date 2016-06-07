(function(){
var indexFs = angular.module('index',[]);

indexFs.controller('PetInfoController',function($scope,$http){
$http.get('http://localhost:8080/getAllPets').
        then(function(response) {
            $scope.petInfo = response.data;
        });
});
})();
