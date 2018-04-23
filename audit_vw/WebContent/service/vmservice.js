app.factory('VmService',function($http){
	var vmService={}
	var BASE_URL="http://localhost:8080/audit_vw"
	
	
		vmService.dealerinfo=function(user)
	{
	return $http.post(BASE_URL+"/servlet/insertdealerinfo",user)
	}
	
	return vmService;
	
})