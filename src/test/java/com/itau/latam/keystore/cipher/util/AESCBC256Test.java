package com.itau.latam.keystore.cipher.util;

import static org.junit.Assert.assertNotNull;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.Map;

import javax.crypto.NoSuchPaddingException;

import org.hibernate.service.spi.ServiceException;
import org.junit.Before;
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
        String encryptedData = "TVE9PS5TcVdpUXYwcVVueFdacHh0SHNGM3B3PT0=";
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
    @Test(expected = RuntimeException.class)
    public void expectingExceptionWhenIdToSearchDIffersFromIdInsideEncryptedData() throws NoSuchAlgorithmException, InvalidKeySpecException, NoSuchPaddingException {
        String encryptedData = "TVE9PS5TcVdpUXYwcVVueFdacHh0SHNGM3B3PT0=";
        AESCBC256.validateCipherSuite("foo", "bar", "2");
        
        AESCBC256.generateFinalDecryptedData(encryptedData);
    }
    
    @Test(expected = RuntimeException.class)
    public void expectingExceptionWhenSaltIsIncorret() throws NoSuchAlgorithmException, InvalidKeySpecException, NoSuchPaddingException {
        String encryptedData = "TVE9PS5TcVdpUXYwcVVueFdacHh0SHNGM3B3PT0=";
        AESCBC256.validateCipherSuite("jarvis", "bar", "1");
        
        AESCBC256.generateFinalDecryptedData(encryptedData);
    }
    
    @Test(expected = RuntimeException.class)
    public void expectingExceptionWhenKeyIsIncorret() throws NoSuchAlgorithmException, InvalidKeySpecException, NoSuchPaddingException {
        String encryptedData = "TVE9PS5TcVdpUXYwcVVueFdacHh0SHNGM3B3PT0=";
        AESCBC256.validateCipherSuite("foo", "jarvis", "1");
        
        AESCBC256.generateFinalDecryptedData(encryptedData);
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
        AESCBC256.validateCipherSuite("foo", "bar", "1");
        
        AESCBC256.generateFinalEncryptedData(clearText);
    }
}
