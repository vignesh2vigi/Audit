app.controller('VmController',function($scope,VmService,$location,$rootScope,$http,$routeParams,FileUploader){
	
	
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
    	 $location.path('/insert')
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

		 var uploader = $scope.uploader = new FileUploader({
	            url: 'http://localhost:8080/audit_vw/servlet/addlead'
	        });

	        // FILTERS

	        uploader.filters.push({
	            name: 'customFilter',
	            fn: function(item /*{File|FileLikeObject}*/, options) {
	                return this.queue.length < 10;
	            }
	        });

	        // CALLBACKS

	        uploader.onWhenAddingFileFailed = function(item /*{File|FileLikeObject}*/, filter, options) {
	            console.info('onWhenAddingFileFailed', item, filter, options);
	        };
	        uploader.onAfterAddingFile = function(fileItem) {
	            console.info('onAfterAddingFile', fileItem);
	        };
	        uploader.onAfterAddingAll = function(addedFileItems) {
	            console.info('onAfterAddingAll', addedFileItems);
	        };
	        uploader.onBeforeUploadItem = function(item) {
	            console.info('onBeforeUploadItem', item);
	        };
	        uploader.onProgressItem = function(fileItem, progress) {
	            console.info('onProgressItem', fileItem, progress);
	        };
	        uploader.onProgressAll = function(progress) {
	            console.info('onProgressAll', progress);
	        };
	        uploader.onSuccessItem = function(fileItem, response, status, headers) {
	            console.info('onSuccessItem', fileItem, response, status, headers);
	        };
	        uploader.onErrorItem = function(fileItem, response, status, headers) {
	            console.info('onErrorItem', fileItem, response, status, headers);
	        };
	        uploader.onCancelItem = function(fileItem, response, status, headers) {
	            console.info('onCancelItem', fileItem, response, status, headers);
	        };
	        uploader.onCompleteItem = function(fileItem, response, status, headers) {
	            console.info('onCompleteItem', fileItem, response, status, headers);
	        };
	        uploader.onCompleteAll = function() {
	            console.info('onCompleteAll');
	           $location.path('/dealerlist')
	        };

	        console.info('uploader', uploader);
	    
		
		
		
	dealerlist()
})