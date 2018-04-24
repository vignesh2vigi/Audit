/**
 * 
 */
app.controller('UserController',function($scope,UserService,$location,$rootScope,$cookieStore){
	
	$scope.login=function(){
		console.log($scope.log)
		UserService.login($scope.log).then(function(response){
		console.log(response.data)
		console.log(response.status)
		$rootScope.currentUser=response.data //username
		$cookieStore.put('currentUser',response.data)
		$location.path('/insert')
		
	},function(response){
		console.log(response.data)
		console.log(response.status)
		$scope.error=response.data
	console.log(response.status)
	if(response.status==415){
    	
		$location.path('/insert')
	}
    	 $location.path('/login')
	})
	}
})