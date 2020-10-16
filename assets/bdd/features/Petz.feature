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
    
  Scenario: Test PUT url/cad/client/1 | Status 200
    Given path 'cad/client/1'
    And request {"name": "ANDRE","address":"Christian TAVARES","district":"RECIFE","uf":"PE","phone":"81998711737","email":"andrechristianl@gmail.com"}
    When method PUT
    Then status 200
    
  Scenario: Test GET url/cad/client/ | Status 200 | Encrypt
    Given path 'cad/client/'
    When method GET
    Then status 200
    
    
  Scenario: Test POST url/cad/pet/ | Status 200
    Given path 'cad/pet/'
    And request {"name": "ANDRE","age":"10 Anos","breed":"PITBULL","note":"CAO COM FEBRE"}
    When method POST
    Then status 200
    
  Scenario: Test GET url/cad/pet/ | Status 200 | Encrypt
    Given path 'cad/pet/'
    When method GET
    Then status 200
    
  Scenario: Test PUT url/cad/pet/1 | Status 200
    Given path 'cad/pet/1'
    And request {"name": "DUD","age":"6 Anos","breed":"GOLDEN","note":"CAO ALEGRE"}
    When method PUT
    Then status 200
    
  Scenario: Test GET url/cad/pet/1 | Status 200 | Encrypt
    Given path 'cad/pet/1'
    When method GET
    Then status 200
   