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
		$location.path('/dealerlist')
		
	},function(response){
		console.log(response.data)
		console.log(response.status)
		$scope.error=response.data
	console.log(response.status)
	if(response.status==415){
		$scope.error=response.data
		$location.path('/login')
	}
    	 $location.path('/login')
	})
	}

	
})