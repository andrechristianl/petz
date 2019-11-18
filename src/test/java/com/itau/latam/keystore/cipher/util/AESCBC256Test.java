package com.itau.latam.keystore.cipher.util;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.HashMap;
import java.util.Map;

import javax.crypto.NoSuchPaddingException;

import org.hibernate.service.spi.ServiceException;
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
    public void expectingExceptionWhileTryingToDecryptButTheCipherWasntProperlyInitialized() {
        String encryptedData = "TVE9PS4zaFBCcEhvNEFIREQzMzBGMkM3YTZ2VkFmd1lNUU1zZkVlZ2Via2loVVZ4YWlacVkzcTlEL1BmZmRGQkpTcDVtWWFBaS9ZQ3ZrQkpsVHF2anhOdm53QT09";
        AESCBC256.generateFinalDecryptedData(encryptedData);
    }
    
    
    /////////////////////////////////////////////////////////////////////////////////////////
    //  encrypted-text -> TVE9PS5TcVdpUXYwcVVueFdacHh0SHNGM3B3PT0=
    //
    //  Solves to/for: 
    //      key -> foo , 
    //      salt" -> bar, 
    //      clear-text -> hello
    /////////////////////////////////////////////////////////////////////////////////////////
    @Test
    public void validateCorrectDecryption() throws NoSuchAlgorithmException, InvalidKeySpecException, NoSuchPaddingException {
        String encryptedData = "TVE9PS5TcVdpUXYwcVVueFdacHh0SHNGM3B3PT0=";

        HashMap<String, CipherSuite> suite = new HashMap<String, CipherSuite>();
        suite.put("1", new CipherSuite("foo", "bar"));
        AESCBC256.setCiphers(suite);
        
        String result = AESCBC256.generateFinalDecryptedData(encryptedData);
        assertEquals(result, "hello");
    }
    
    @Test(expected = RuntimeException.class)
    public void expectingExceptionWhenIdToSearchDIffersFromIdInsideEncryptedData() throws NoSuchAlgorithmException, InvalidKeySpecException, NoSuchPaddingException {
        String encryptedData = "TVE9PS5TcVdpUXYwcVVueFdacHh0SHNGM3B3PT0=";

        HashMap<String, CipherSuite> suite = new HashMap<String, CipherSuite>();
        suite.put("2", new CipherSuite("foo", "bar"));
        AESCBC256.setCiphers(suite);
        
        AESCBC256.generateFinalDecryptedData(encryptedData);
    }
    
    @Test(expected = RuntimeException.class)
    public void expectingExceptionWhenSaltIsIncorret() throws NoSuchAlgorithmException, InvalidKeySpecException, NoSuchPaddingException {
        String encryptedData = "TVE9PS5TcVdpUXYwcVVueFdacHh0SHNGM3B3PT0=";

        HashMap<String, CipherSuite> suite = new HashMap<String, CipherSuite>();
        suite.put("1", new CipherSuite("jarvis", "bar"));
        AESCBC256.setCiphers(suite);
        
        AESCBC256.generateFinalDecryptedData(encryptedData);
    }
    
    @Test(expected = RuntimeException.class)
    public void expectingExceptionWhenKeyIsIncorret() throws NoSuchAlgorithmException, InvalidKeySpecException, NoSuchPaddingException {
        String encryptedData = "TVE9PS5TcVdpUXYwcVVueFdacHh0SHNGM3B3PT0=";

        HashMap<String, CipherSuite> suite = new HashMap<String, CipherSuite>();
        suite.put("1", new CipherSuite("foo", "jarvis"));
        AESCBC256.setCiphers(suite);
        
        AESCBC256.generateFinalDecryptedData(encryptedData);
    }
    
    
    /////////////////////////////////////////////////////////////////////////////////////////
    //  encrypted-text -> TVE9PS4zaFBCcEhvNEFIREQzMzBGMkM3YTZ2VkFmd1lNUU1zZkVlZ2Via2loVVZ4YWlacVkzcTlEL1BmZmRGQkpTcDVtZlR3Z1pEeXFjZXVSR2xiSHI1b3FJZz09
    //
    //  Solves to/for: 
    //      key -> foo , 
    //      salt" -> bar, 
    //      clear-text -> 1234567890123456789012345678901234567890123456789012345
    /////////////////////////////////////////////////////////////////////////////////////////    
    @Test()
    public void validateCorrectEncryption() throws NoSuchAlgorithmException, InvalidKeySpecException, NoSuchPaddingException {
        String clearText = "1234567890123456789012345678901234567890123456789012345";

        HashMap<String, CipherSuite> suite = new HashMap<String, CipherSuite>();
        suite.put("1", new CipherSuite("foo", "bar"));
        AESCBC256.setCiphers(suite);
        AESCBC256.setLastId("1");
        
        String result = AESCBC256.generateFinalEncryptedData(clearText);
        
        assertEquals(result, "TVE9PS4zaFBCcEhvNEFIREQzMzBGMkM3YTZ2VkFmd1lNUU1zZkVlZ2Via2loVVZ4YWlacVkzcTlEL1BmZmRGQkpTcDVtZlR3Z1pEeXFjZXVSR2xiSHI1b3FJZz09");
    }
    
    
    /////////////////////////////////////////////////////////////////////////////////////////
    //  encrypted-text -> TVE9PS4zaFBCcEhvNEFIREQzMzBGMkM3YTZ2VkFmd1lNUU1zZkVlZ2Via2loVVZ4YWlacVkzcTlEL1BmZmRGQkpTcDVtUFRMQ0lDSHhVMmZERHp0cjVaeU9MQT09
    //
    //  Solves to/for: 
    //      key -> foo , 
    //      salt" -> bar, 
    //      clear-text -> 12345678901234567890123456789012345678901234567890123456
    /////////////////////////////////////////////////////////////////////////////////////////
    @Test(expected = ServiceException.class)
    public void expectingExceptionWhenClearTextHas56OrMoreCharacters() throws NoSuchAlgorithmException, InvalidKeySpecException, NoSuchPaddingException {
        String clearText = "12345678901234567890123456789012345678901234567890123456";

        HashMap<String, CipherSuite> suite = new HashMap<String, CipherSuite>();
        suite.put("1", new CipherSuite("foo", "bar"));
        AESCBC256.setCiphers(suite);
        
        AESCBC256.generateFinalEncryptedData(clearText);
    }
}
