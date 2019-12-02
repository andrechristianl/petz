function () {   
  var env = karate.env; // get system property 'karate.env'
  var ip_server = karate.ip
  
  var ip_server = java.lang.System.getenv('IP_HOSTNAME') == null ? 'localhost' : java.lang.System.getenv('IP_HOSTNAME');
  
  var port = karate.properties['karate.server.port'];
  port = port || '8080';
  
  if (!env) {
    env = 'dev';
  }
  var config = {
    env: env,    
    
    	
  }
  if (env == 'dev') {
	config.urlBase = 'http://' + ip_server +':' + port;
		
		
  } else {
   
	config.urlBase = 'http://' + ip_server +':' + port;
	
  }
  return config;
}