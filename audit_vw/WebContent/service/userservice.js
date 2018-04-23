app.factory('UserService',function($http){
	var userService={}
	var BASE_URL="http://localhost:8080/audit_vw"
	
	
	userService.login=function(log)
	{
	return $http.post(BASE_URL+"/servlet/login",log)
	}
	userService.logout=function()
	{
	return $http.get(BASE_URL+"/servlet/logout")
	}
	return userService;
	
})