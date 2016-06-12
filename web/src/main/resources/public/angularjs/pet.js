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
    $http.post('http://97.99.64.156:8080/getPet',petRequest,{headers:{'Content-Type':'application/json'}}).
    then(function(response)
    {
    this.petInfo=response.data.pet[0];
    }.bind(this),function(response){}.bind(this));
    }}],controllerAs:'getPetInfo'
}
});


petModule.directive('petAdd',function(){
return{
    restrict:'E',templateUrl:'/petAddInfo.html',controller:['$http',function($http){
        this.success=0;
        this.error=0;
        this.errorMessage={};
        this.addPet=function(mypet)
        {
        this.success=0;
        this.error=0;
        this.errorMessage={};
        petInfo={'pet':mypet};
        $http.post('http://97.99.64.156:8080/addPet',petInfo,{headers:{'Content-Type':'application/json'}})
        .then(function(response){
            result= response.data;
            if(result.status=='SUCCESS')
            {
            this.success=1;
            }
            else
            {
            this.errorMessage=result.errorMessage.split(",");
            this.error=1;
            }
        }.bind(this));
        //submit this petInfo to service
        }}],controllerAs:'petAddInfo'
}
});
})();