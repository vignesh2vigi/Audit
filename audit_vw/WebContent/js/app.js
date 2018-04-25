/**
 * 
 */
var app = angular.module("app", [ 'ngRoute','ngCookies','angularFileUpload'])
app.config(function($routeProvider) {

	$routeProvider
	.when("/login", {
        templateUrl : 'view/login.html',
        controller:'UserController'
        })
        .when("/insert", {
        templateUrl : 'view/insert.html',
        controller:'VmController'
        })
       /*  .when("/stock", {
        templateUrl : 'view/stock.html',
        controller:'VmController'
        })*/
        .when("/stock", {
        templateUrl : 'view/excel.html',
        controller:'VmController'
        })
        .when("/stocklist/:sno", {
        templateUrl : 'view/stocklist.html',
        controller:'VmController'
        })
        .when("/dealerlist", {
        templateUrl : 'view/dealerlist.html',
        controller:'VmController'
        })
    .otherwise("/login",{templateurl:"view/login.html",controller:'UserController'})
   	
})

app.run(function($rootScope,$cookieStore,$location,UserService){
	
		if($rootScope.currentUser==undefined){
			$rootScope.currentUser=$cookieStore.get('currentUser')
		}
		$rootScope.logout=function(){
			
			UserService.logout().then(function(response){
			delete $rootScope.currentUser;
			console.log(response.status)
			console.log(response.data)
			$cookieStore.remove('currentUser')
			$location.path('/login')
			
		},function(response){
			if(response.status==401){
				console.log(response.status)
				console.log(response.data)
				delete $rootScope.currentUser;
				$cookieStore.remove('currentUser')
				$location.path('/login')

			}
				
		})
	}

})
