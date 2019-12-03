Feature: Codes Resources  
  Background:    
     * def configBase = call read('file:./assets/bdd/karate-config.js')
     * print configBase
     * url configBase['urlBase']
	 * header Accept = 'application/json'    
    
  Scenario: Test POST url/ppid/encrypt | Status 200 | Encrypt
    Given path 'ppid/encrypt'
    And request [{  plaintext: 'André' },	{ plaintext: 'Igor' }, { plaintext: 'Paulo'  }, { plaintext: 'Jarvis' }]
    When method POST
    Then status 200
    And match response contains [{"plaintext":"André","encryptted":"TVE9PS5tSzRuQXJoOXdvZG92NkVjS3ZYUjVRPT0="},{"plaintext":"Igor","encryptted":"TVE9PS5peklNWkh2R1JOMjZMRGZUUmJjRmpnPT0="},{"plaintext":"Paulo","encryptted":"TVE9PS40cmorZEVsUkVOb2ZGSGhtbVNKV2Z3PT0="},{"plaintext":"Jarvis","encryptted":"TVE9PS5rUGZIdTVhYWxNMVAvSXJLWU9meEtnPT0="}]
    
  Scenario: Test POST url/ppid/encrypt | Status 400 | Encrypt branco
    Given path 'ppid/encrypt'
    And request [{  plaintext: '' }]
    When method POST    
    Then status 400
    And match response contains {"code": 400,"message": "An issue has been raised because one or more of the provided encrypted fields were either null or empty"} 

  Scenario: Test POST url/ppid/encrypt | Status 400 | Encrypt null
    Given path 'ppid/encrypt'
    And request [{  plaintext: null }]
    When method POST    
    Then status 400
    And match response contains {"code": 400,"message": "An issue has been raised because one or more of the provided encrypted fields were either null or empty"} 
   
  Scenario: Test POST url/ppid/decrypt | Status 200 | Decrypt
    Given path 'ppid/decrypt'
    And request [{"encryptted": "TVE9PS5tSzRuQXJoOXdvZG92NkVjS3ZYUjVRPT0="},{"encryptted": "TVE9PS5peklNWkh2R1JOMjZMRGZUUmJjRmpnPT0="},{"encryptted": "TVE9PS40cmorZEVsUkVOb2ZGSGhtbVNKV2Z3PT0="},{"encryptted": "TVE9PS5rUGZIdTVhYWxNMVAvSXJLWU9meEtnPT0="}]
    When method POST
    Then status 200
       
  Scenario: Test POST url/ppid/decrypt | Status 400 | Decrypt Branco
    Given path 'ppid/decrypt'
    And request [{ encryptted: '' }]
    When method POST
    Then status 400
    And match response contains {"code": 400,"message": "An issue has been raised because one or more of the provided encrypted fields were either null or empty"}
    
  Scenario: Test POST url/ppid/decrypt | Status 400 | Decrypt Null
    Given path 'ppid/decrypt'
    And request [{ encryptted:  null }]
    When method POST
    Then status 400
    And match response contains {"code": 400,"message": "An issue has been raised because one or more of the provided encrypted fields were either null or empty"}    