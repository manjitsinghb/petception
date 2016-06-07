(function(){
var indexFs = angular.module('index',[]);

indexFs.controller('PetInfoController',function(){
this.pet = dbPet;
});
var dbPet={"petId":"petId","color":"brown","name":"barry","weight":"20","breed":"golden retriver","url":"http://google.com"};
})();