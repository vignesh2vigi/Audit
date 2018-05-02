app.controller('VmController',function($http,$scope,VmService,$location,$rootScope,$http,$routeParams,FileUploader){
	
	
	var sno=$routeParams.sno
	var audit_id=$routeParams.audit_id
	
	
	  $scope.getFileDetails = function (e) {

        $scope.files = [];
        console.log( $scope.files)
        $scope.$apply(function () {

            // STORE THE FILE OBJECT IN AN ARRAY.
            for (var i = 0; i < e.files.length; i++) {
            	console.log(e.files[i])
                $scope.files.push(e.files[i])
                
            }
   		 var data = new FormData();

         for (var i in $scope.files) {
             data.append("uploadedFile", $scope.files[i]);
         }
     	console.log("check"+data)
         var request = {
        	
                 method: 'POST',
                 url: 'servlet/uploadStatement',
                 data: data,
                 headers: {
                     'Content-Type': undefined
                 }
             };
     	  $http(request)
     	  .success(function (data, status) {
        console.log(data);
        $scope.user.auth_letter = data.d;
    })
              .error(function () {
              	console.log("upload Statement error")
              });
    /* 	VmService.image(data).then(function(response){
    		console.log(response.data)
    		console.log(response.status)
    		$scope.user=response.data
    		$location.path('/insert')
    		                               
    	},function(response){
    		console.log(response.data)
    		console.log(response.status)
    		$scope.error=response.data
    	console.log(response.status)
        	 $location.path('/insert')
    	})*/
    	

           // $scope.review size = e.files.length;
        });
    };
	
	$scope.dealerinfo=function(user){

	
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
	
	VmService.assignid(audit_id).then(function(response) {
		console.log(response.data)
		console.log(response.status)
		 
		$scope.loan = response.data
		
		
	}, function(response) {
		console.log(response.status)
		if(response.status==401){
    	
			$location.path('/login')
		}
	})
	
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
	 	function assignerlist() {
	    		VmService.assignerlist().then(function(response) {
	    			console.log(response.data)
	    			console.log(response.status)
	    			 
	    			$scope.an = response.data
	    			
	    		}, function(response) {
	    			console.log(response.status)
	    			if(response.status==401){
	    	    	
	    				$location.path('/login')
	    			}
	    		})
	    	}
	function dealerlist() {
		VmService.dealerlist().then(function(response) {
			console.log(response.data)
			console.log(response.status)
			 
			$scope.deal = response.data
			$rootScope.pend=$scope.deal.length
			/*$scope.items = 3;
*/		}, function(response) {
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
	    
	        $scope.as=function(loan){
	    		console.log(loan.remark)
	    		console.log(loan.sno)
	    		console.log(loan.audit_id)
	    		var assign={
	    			"remarks":loan.remark,"assign_to":loan.sno,"audit_id":loan.audit_id
	    		}
	    		VmService.assign(assign).then(function(response){
	    			console.log(response.data)
	    			console.log(response.status)
	    		
	    			$location.path('/dealerlist')
	    			
	    		},function(response){
	    			console.log(response.data)
	    			console.log(response.status)
	    			$scope.error=response.data
	    		console.log(response.status)
	    	    	 $location.path('/dealerlist')
	    		})
	    	}
	   
	        function assigndeal() {
	    		VmService.assigndeal().then(function(response) {
	    			console.log(response.data)
	    			console.log(response.status)
	    			 
	    			$scope.assign = response.data
	    			$rootScope.asn=$scope.assign.length
	    		}, function(response) {
	    			console.log(response.status)
	    			if(response.status==401){
	    	    	
	    				$location.path('/login')
	    			}
	    		})
	    	}
	        function comdeal() {
	    		VmService.comdeal().then(function(response) {
	    			console.log(response.data)
	    			console.log(response.status)
	    			 
	    			$scope.com = response.data
	    			$rootScope.cpt=$scope.com.length
	    		}, function(response) {
	    			console.log(response.status)
	    			if(response.status==401){
	    	    	
	    				$location.path('/login')
	    			}
	    		})
	    	}
		
	    	
	dealerlist()
	assignerlist()
	assigndeal()
	comdeal()
})