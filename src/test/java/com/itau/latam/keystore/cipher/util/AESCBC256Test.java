package com.itau.latam.keystore.cipher.util;

import static org.junit.Assert.assertNotNull;

import java.util.HashMap;
import java.util.Map;

import javax.crypto.spec.SecretKeySpec;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@ContextConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
public class AESCBC256Test {
    
    @Test
    public void validateCipherSuiteInitializedWithFallbackStrategy() {
        String secretKey = "foo";
        String salt = "bar";
        String id = "1";
        Map<String, CipherSuite> ciphers = AESCBC256.validateCipherSuite(secretKey, salt, id);
        assertNotNull(ciphers.get(id));
    }
    
    @Test(expected = RuntimeException.class)
    public void validadeExceptionInTheCipherSuiteCreationWhenSaltIsNull() {
        String secretKey = "foo";
        String salt = null;
        String id = "1";
        AESCBC256.validateCipherSuite(secretKey, salt, id);
    }
    
    @Test(expected = RuntimeException.class)
    public void validadeExceptionInTheCipherSuiteCreationWhenKeyIsNull() {
        String secretKey = null;
        String salt = "bar";
        String id = "1";
        AESCBC256.validateCipherSuite(secretKey, salt, id);
    }
    
    
    
    
    
    
    @Test(expected = RuntimeException.class)
    public void expetingExceptionWhileTryingToDecryptButTheCipherWasntProperlyInitialized() {
        String encryptedData = "TWc9PS5PQ3hHQS9semQ2VFBsZDljUFI4cWdDNGR5UTRPTk5xNzRpOGYzTlpUK0s5NXc1bTBKazRaRkhudm5mK0RuSUI3Z3BXaHYzcnZMRXVJSzViMVd3MUNCUT09";
        AESCBC256.generateFinalDecryptedData(encryptedData);
    }
    
    // TODO: Mock the static cipher somehow
//    @Test
//    public void someTest() {
//        String encryptedData = "TWc9PS5PQ3hHQS9semQ2VFBsZDljUFI4cWdDNGR5UTRPTk5xNzRpOGYzTlpUK0s5NXc1bTBKazRaRkhudm5mK0RuSUI3Z3BXaHYzcnZMRXVJSzViMVd3MUNCUT09";
//        AESCBC256.generateFinalDecryptedData(encryptedData);
//    }
}
