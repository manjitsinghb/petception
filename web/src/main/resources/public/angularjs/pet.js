(function(){
var petModule = angular.module('petModule',[]);

petModule.directive('petInfo',function(){
return{
    restrict:'E',templateUrl:'/petAddInfo.html',controller:['$http',function($http){
        this.success=0;
        this.error=0;
        this.addPet=function(mypet)
        {
        this.success=0;
        this.error=0;
        petInfo={'pet':mypet};
        $http.post('http://localhost:8080/addPet',petInfo,{headers:{'Content-Type':'application/json'}})
        .then(function(data){
        console.log("success");
            this.success=-1;
        },function(data){
        console.log("error");
            this.error=-1;
        })
        ;
        //submit this petInfo to service
        }
    }],controllerAs:'petAddInfo'
}
});
})();