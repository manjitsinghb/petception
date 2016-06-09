(function(){
var petModule = angular.module('petModule',[]);

petModule.directive('petInfo',function(){
return{
    restrict:'E',templateUrl:'/petAddInfo.html',controller:['$http',function($http){
        this.addPet=function(mypet)
        {
        petInfo={'pet':mypet};
        $http.post('http://localhost:8080/addPet',petInfo,{headers:{'Content-Type':'application/json'}});
        //submit this petInfo to service
        }
    }],controllerAs:'petAddInfo'
}
});
})();