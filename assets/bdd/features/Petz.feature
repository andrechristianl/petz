Feature: Codes Resources  
  Background:    
     * def configBase = call read('file:./assets/bdd/karate-config.js')
     * print configBase
     * url configBase['urlBase']
	 * header Accept = 'application/json'    
    
  Scenario: Test POST url/cad/client/ | Status 200
    Given path 'cad/client/'
    And request {"name": "ANDRE","address":"Christian","district":"RECIFE","uf":"PE","phone":"81998711737","email":"andre@ioweb.com.br"}
    When method POST
    Then status 200
    
  Scenario: Test GET url/cad/client/ | Status 200 | Encrypt
    Given path 'cad/client/'
    When method GET
    Then status 200
   