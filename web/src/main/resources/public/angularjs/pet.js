(function(){
var petModule = angular.module('petModule',[]);

petModule.directive('petInfo',function(){
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
        $http.post('http://localhost:8080/addPet',petInfo,{headers:{'Content-Type':'application/json'}})
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
        }
    }],controllerAs:'petAddInfo'
}
});
})();