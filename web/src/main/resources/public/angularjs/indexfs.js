(function(){
var indexFs = angular.module('index',['navModule','petModule']);

indexFs.controller('PetInfoController',function($scope,$http){
$http.get('http://localhost:8080/getAllPets').
        then(function(response) {
        $scope.petInfo = [];
        chunk=3;
        for(i=0;i<response.data.pet.length;i+=chunk)
        {
            tempArray=response.data.pet.slice(i,i+chunk);
            $scope.petInfo.push(tempArray);
        }
        });
});
})();
