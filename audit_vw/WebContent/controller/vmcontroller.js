app.controller('VmController',function($scope,VmService,$location,$rootScope,$cookieStore){
	
	$scope.dealerinfo=function(){
		console.log($scope.user)
		VmService.dealerinfo($scope.user).then(function(response){
		console.log(response.data)
		console.log(response.status)
		$rootScope.currentUser=response.data //username
		$cookieStore.put('currentUser',response.data)
		$location.path('/stock')
		
	},function(response){
		console.log(response.data)
		console.log(response.status)
		$scope.error=response.data
	console.log(response.status)
    	 $location.path('/login')
	})
	}
})