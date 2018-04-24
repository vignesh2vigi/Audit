app.controller('VmController',function($scope,VmService,$location,$rootScope,$http,$routeParams){
	
	
	var sno=$routeParams.sno
		
	
	$scope.dealerinfo=function(){
		console.log($scope.user)
		VmService.dealerinfo($scope.user).then(function(response){
		console.log(response.data)
		console.log(response.status)
	
		$location.path('/stock')
		
	},function(response){
		console.log(response.data)
		console.log(response.status)
		$scope.error=response.data
	console.log(response.status)
    	 $location.path('/stock')
	})
	}
	

	
	VmService.getdetails().then(function(response) {
		console.log(response.data)
		console.log(response.status)
		 
		$scope.vehicle = response.data
		
		$rootScope.ray = new Array($scope.vehicle.no_stock);
		console.log("count"+$rootScope.ray)
	}, function(response) {
		console.log(response.status)
		if(response.status==401){
    	
			$location.path('/login')
		}
	})
	
  /*  $scope.uploadFile=function(){
	       var fd=new FormData();
	        console.log($scope.files);
	        angular.forEach($scope.files,function(file){
	        fd.append('file',file);
	        });
	       $http.post('http://localhost:8080/audit_vw/servlet/addlead',fd,
	           {
	               transformRequest: angular.identity,
	               headers: {'file': undefined}                     
	            }).success(function(d)
	                {
	                    console.log(d);
	                })         
	       }*/
	
	
	$scope.stock=function(){
		console.log(""+$scope.vehicle.sno)
		console.log(""+$rootScope.vm.vin_no)
		VmService.stockup($scope.vehicle).then(function(response){
			console.log(response.data)
			console.log(response.status)
		
			$location.path('/insert')
			
		},function(response){
			console.log(response.data)
			console.log(response.status)
			$scope.error=response.data
		console.log(response.status)
	    	 $location.path('/stock')
		})
	}
	
	
		VmService.stocklist(sno).then(function(response) {
			console.log(response.data)
			console.log(response.status)
			 
			$scope.stock = response.data
			$scope.items = 3;
		}, function(response) {
			console.log(response.status)
			if(response.status==401){
	    	
				$location.path('/login')
			}
		})
	
	function dealerlist() {
		VmService.dealerlist().then(function(response) {
			console.log(response.data)
			console.log(response.status)
			 
			$scope.deal = response.data
			$scope.items = 3;
		}, function(response) {
			console.log(response.status)
			if(response.status==401){
	    	
				$location.path('/login')
			}
		})
	}

	dealerlist()
})