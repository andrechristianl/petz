function fn() {   
  var env = karate.env; // get system property 'karate.env'
  if (!env) {
    env = 'dev';
  }
  var config = {
    env: env,    
    
    	
  }
  if (env == 'dev') {
    // customize
	config.urlBase = 'http://another-host'
		
  } else if (env == 'e2e') {
    // customize
	config.urlBase = 'http://another-host'
  }
  
  var port = karate.properties['karate.server.port'];
  port = port || '8080';
  config.urlBase = 'http://localhost:' + port;
  return config;
}