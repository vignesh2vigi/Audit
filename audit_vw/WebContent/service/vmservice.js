app.factory('VmService',function($http){
	var vmService={}
	var BASE_URL="http://localhost:8080/audit_vw"
	
	
		vmService.dealerinfo=function(user)
	{
	return $http.post(BASE_URL+"/servlet/insertdealerinfo",user)
	}
	vmService.stockup=function(vehicle)
	{
	return $http.post(BASE_URL+"/servlet/stockup",vehicle)
	}
	vmService.getdetails=function()
	{
	return $http.get(BASE_URL+"/servlet/getdetails")
	}
	
	vmService.stocklist=function()
	{
	return $http.get(BASE_URL+"/servlet/stocklist")
	}
	vmService.dealerlist=function()
	{
	return $http.get(BASE_URL+"/servlet/dealerlist")
	}
	vmService.stocklist=function(sno)
	{
	return $http.get(BASE_URL+"/servlet/stocklist/"+sno)
	}
	
	return vmService;
	
})