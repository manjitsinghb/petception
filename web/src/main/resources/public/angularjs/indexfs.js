(function(){
var indexFs = angular.module('index',['navModule','petModule']);

indexFs.controller('PetInfoController',function($scope,$http){
$http.get('http://localhost:8080/getAllPets').
        then(function(response) {
        $scope.petInfo = [];
        $scope.imgSource=[];
        chunk=3;
        for(i=0;i<response.data.pet.length;i++)
        {
            $http.post('http://localhost:8080/getPetPhoto',
            response.data.pet[i].url,{headers:{'Content-Type':'application/text'}}).
            then(function(imgres)
            {
             $scope.imgSource.push("abc"+imgres.data);
            });
        }
        for(i=0;i<response.data.pet.length;i++)
        {
            response.data.pet[i]["urlsrc"]=$scope.imgSource[i];
        }


        for(i=0;i<response.data.pet.length;i+=chunk)
        {
            tempArray=response.data.pet.slice(i,i+chunk);
            $scope.petInfo.push(tempArray);
        }
        });
});
})();
