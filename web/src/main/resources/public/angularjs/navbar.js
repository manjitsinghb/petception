(function(){var navApp = angular.module('navModule',[]);

navApp.directive('navBar',function(){
return{
    restrict:'E',templateUrl:'/navbar.html'
}
});
})();