package com.itau.latam.keystore.cipher.util;

import static org.junit.Assert.assertEquals;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

import javax.crypto.NoSuchPaddingException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@ContextConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
public class AESCBC156NegativeTest {
    
    
    private void setupTestKeys() {
        String secretKey = "foo";
        String salt = "bar";
        String id = "1";
        AESCBC256.validateCipherSuite(secretKey, salt, id);
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
        setupTestKeys();
        
        String result = AESCBC256.generateDecryptedData(encryptedData);
        assertEquals(result, "hello");
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
        setupTestKeys();
        String result = AESCBC256.generateEncryptedData(clearText);
        
        assertEquals(result, "TVE9PS4zaFBCcEhvNEFIREQzMzBGMkM3YTZ2VkFmd1lNUU1zZkVlZ2Via2loVVZ4YWlacVkzcTlEL1BmZmRGQkpTcDVtZlR3Z1pEeXFjZXVSR2xiSHI1b3FJZz09");
    }
}
