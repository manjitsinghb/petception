(function(){
var indexFs = angular.module('index',[]);

indexFs.controller('PetInfoController',function($scope,$http){
$http.get('http://localhost:8080/getAllPets').
        success(function(data) {
            $scope.petInfo = data;
        });
});
})();
