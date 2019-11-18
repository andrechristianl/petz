package com.itau.latam.keystore.cipher.util;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

import javax.crypto.NoSuchPaddingException;

import org.hibernate.service.spi.ServiceException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@ContextConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
public class AESCBC256Test {
    
    
    private void setupTestKeys() {
        String secretKey = "foo";
        String salt = "bar";
        String id = "1";
        AESCBC256.validateCipherSuite(secretKey, salt, id);
    }
    
    
    @Test(expected = RuntimeException.class)
    public void expectingExceptionWhileTryingToDecryptButTheCipherWasntProperlyInitialized() {
    	setupTestKeys();
        String encryptedData = "TVE9PS5TcVdpUXYwcVVueFdacHh0SHNGM3B3PT0=";
        AESCBC256.generateDecryptedData(encryptedData);
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

        String secretKey = "foo";
        String salt = "bar";
        String id = "2";
        AESCBC256.validateCipherSuite(secretKey, salt, id);
    	
        String encryptedData = "TVE9PS5TcVdpUXYwcVVueFdacHh0SHNGM3B3PT0=";
        AESCBC256.generateDecryptedData(encryptedData);
    }
    
    @Test(expected = RuntimeException.class)
    public void expectingExceptionWhenSaltIsIncorrect() throws NoSuchAlgorithmException, InvalidKeySpecException, NoSuchPaddingException {
        String encryptedData = "TVE9PS5TcVdpUXYwcVVueFdacHh0SHNGM3B3PT0=";
        AESCBC256.validateCipherSuite("jarvis", "bar", "1");
        
        AESCBC256.generateDecryptedData(encryptedData);
    }
    
    @Test(expected = RuntimeException.class)
    public void expectingExceptionWhenKeyIsIncorrect() throws NoSuchAlgorithmException, InvalidKeySpecException, NoSuchPaddingException {
        String encryptedData = "TVE9PS5TcVdpUXYwcVVueFdacHh0SHNGM3B3PT0=";
        AESCBC256.validateCipherSuite("foo", "jarvis", "1");
        
        AESCBC256.generateDecryptedData(encryptedData);
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
        
        AESCBC256.generateEncryptedData(clearText);
    }
}
