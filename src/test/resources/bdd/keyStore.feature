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
    And match response contains [{"encryptted":"TVE9PS55NDdzcGxrdDFQVnBBU3daemZMYTdnPT0="}] 
   
  Scenario: Test POST /ppid/decrypt | Status 200 | Decrypt
    Given path 'ppid/decrypt'
    And request [{"encryptted": "TVE9PS5tSzRuQXJoOXdvZG92NkVjS3ZYUjVRPT0="},{"encryptted": "TVE9PS5peklNWkh2R1JOMjZMRGZUUmJjRmpnPT0="},{"encryptted": "TVE9PS40cmorZEVsUkVOb2ZGSGhtbVNKV2Z3PT0="},{"encryptted": "TVE9PS5rUGZIdTVhYWxNMVAvSXJLWU9meEtnPT0="}]
    When method POST
    Then status 200
       
  Scenario: Test POST /ppid/decrypt | Status 200 | Decrypt Branco
    Given path 'ppid/decrypt'
    And request [{ encryptted: '' }]
    When method POST
    Then status 500
    
  Scenario: Test POST /ppid/decrypt | Status 200 | Decrypt Null
    Given path 'ppid/decrypt'
    And request [{ encryptted:  null }]
    When method POST
    Then status 500