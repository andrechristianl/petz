function fn() {   
  var env = karate.env; // get system property 'karate.env'
  var port = karate.properties['karate.server.port'];
  port = port || '8080';
  
  if (!env) {
    env = 'dev';
  }
  var config = {
    env: env,    
    
    	
  }
  if (env == 'dev') {
      
	config.urlBase = 'http://172.10.0.12:8080';
		
		
  } else {
   
	config.urlBase = 'http://localhost:' + port;
	
  }
  
  //config.urlBase = 'http://localhost:' + port;
 
  
  return config;
}