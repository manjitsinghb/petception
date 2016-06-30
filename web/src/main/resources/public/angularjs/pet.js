(function(){
var petModule = angular.module('petModule',[]);

petModule.directive('petInfo',function(){
return{
    restrict:'E',templateUrl:'/petInfo.html',controller:['$http',function($http){
    this.petInfo={};
    this.petId='';
    this.getPetDetails=function(petId)
    {
    petRequest={'petId':petId};
    $http.post('http://localhost:8080/getPet',petRequest,{headers:{'Content-Type':'application/json'}}).
    then(function(response)
    {
    this.petInfo=response.data.pet[0];
    }.bind(this),function(response){}.bind(this));
    }}],controllerAs:'getPetInfo'
}
});
})();


//upload file using ajax
