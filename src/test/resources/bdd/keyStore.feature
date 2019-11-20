Feature: Codes Resources  
  Background:    
     * url urlBase
	 * header Accept = 'application/json'    
    
  Scenario: Test POST /ppid/encrypt | Status 200 | Encrypt
    Given path 'ppid/encrypt'
    And request [{  plaintext: 'André' },	{ plaintext: 'Igor' }, { plaintext: 'Paulo'  }, { plaintext: 'Jarvis' }]
    When method POST
    Then status 200
    
  Scenario: Test POST /ppid/encrypt | Status 200 | Encrypt branco
    Given path 'ppid/encrypt'
    And request [{  plaintext: '' }]
    When method POST    
    Then status 200
  
  Scenario: Test POST /ppid/encrypt | Status 200 | Encrypt null
    Given path 'ppid/encrypt'
    And request [{  plaintext: null }]
    When method POST    
    Then status 200
    And match response contains [{}] 
   
  Scenario: Test POST /ppid/decrypt | Status 200 | Decrypt
    Given path 'ppid/decrypt'
    And request [{ encryptted: 'QW5kcsOp' },	{ encryptted: 'SWdvcg==' }, { encryptted: 'UGF1bG8=' }, { encryptted: 'SmFydmlz' },  { encryptted: 'U3VlbGk='}]
    When method POST
    Then status 200
       
  Scenario: Test POST /ppid/decrypt | Status 200 | Decrypt Branco
    Given path 'ppid/decrypt'
    And request [{ encryptted: '' }]
    When method POST
    Then status 200
    
  Scenario: Test POST /ppid/decrypt | Status 200 | Decrypt Null
    Given path 'ppid/decrypt'
    And request [{ encryptted:  null }]
    When method POST
    Then status 200
    And match response contains [{}] 