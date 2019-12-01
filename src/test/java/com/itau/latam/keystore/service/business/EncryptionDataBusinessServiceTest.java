package com.itau.latam.keystore.service.business;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.service.spi.ServiceException;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.itau.latam.keystore.model.dto.CompleteDataDTO;
import com.itau.latam.keystore.model.dto.KeyStoreDTO;
import com.itau.latam.keystore.service.business.impl.EncryptionDataBusinessServiceImpl;

@ContextConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class EncryptionDataBusinessServiceTest {

	private EncryptionDataBusinessService businessDataService;

	{
		businessDataService = new EncryptionDataBusinessServiceImpl();
	}

	@Test
	public void T1_encryptDataListSucessfully() {

		List<String> encryptedExpectedResults = new ArrayList<String>();
		encryptedExpectedResults.add("TVE9PS5ZaVJHaWRVbWlyOHU3UWJJMDVuUHF3PT0=");
		encryptedExpectedResults
				.add("TVE9PS5RSDJNc1RQSmdBWTc2aWlLd2Y1cXVUYlZ0akFVRWVFZ1FSbnNTdVp5ZGdsejNEVUQ1VjZIZHJXSTNzVnZ6aE01");

		CompleteDataDTO c1 = new CompleteDataDTO();
		c1.setPlaintext("123456");

		CompleteDataDTO c2 = new CompleteDataDTO();
		c2.setPlaintext("00000000000000000000000000000000000000000000001");

		List<CompleteDataDTO> completeDataDTOParameter = new ArrayList<CompleteDataDTO>();
		completeDataDTOParameter.add(c1);
		completeDataDTOParameter.add(c2);
		KeyStoreDTO keyStore = new KeyStoreDTO();

		keyStore.setId(1);
		keyStore.setSalt("mssql-keystore-container");
		keyStore.setSecretKey("mssql-keystore-container");

		List<CompleteDataDTO> completeDataDTOResult = businessDataService.encryptListData(completeDataDTOParameter,
				keyStore);

		for (int i = 0; i < completeDataDTOResult.size(); i++) {
			CompleteDataDTO selectedData = completeDataDTOResult.get(i);

			assertEquals(selectedData.getEncryptted(), encryptedExpectedResults.get(i));
		}
	}

	@Test
	public void T2_encryptDataListGeneratingAnError() {

		List<String> encryptedExpectedResults = new ArrayList<String>();
		encryptedExpectedResults.add("NOT_VALID_ENCRYPTION_RESULT");

		CompleteDataDTO c1 = new CompleteDataDTO();
		c1.setPlaintext("123456");

		List<CompleteDataDTO> completeDataDTOParameter = new ArrayList<CompleteDataDTO>();
		completeDataDTOParameter.add(c1);
		KeyStoreDTO keyStore = new KeyStoreDTO();

		keyStore.setId(1);
		keyStore.setSalt("mssql-keystore-container");
		keyStore.setSecretKey("mssql-keystore-container");

		List<CompleteDataDTO> completeDataDTOResult = businessDataService.encryptListData(completeDataDTOParameter,
				keyStore);

		for (int i = 0; i < completeDataDTOResult.size(); i++) {
			CompleteDataDTO selectedData = completeDataDTOResult.get(i);

			assertNotEquals(selectedData.getEncryptted(), encryptedExpectedResults.get(i));
		}
	}

	@Test(expected = RuntimeException.class)
	public void T3_decryptDataListWithAnInvalidKeyGeneratingAnError() {

		List<String> decryptedExpectedResults = new ArrayList<String>();
		decryptedExpectedResults.add("123456");

		CompleteDataDTO c1 = new CompleteDataDTO();
		c1.setEncryptted("TVRBdy5ZaVJHaWRVbWlyOHU3UWJJMDVuUHF3PT0=");
		// MTAw.YiRGidUmir8u7QbI05nPqw==

		List<CompleteDataDTO> completeDataDTOParameter = new ArrayList<CompleteDataDTO>();
		completeDataDTOParameter.add(c1);
		KeyStoreDTO keyStore = new KeyStoreDTO();

		keyStore.setId(100);
		keyStore.setSalt("INVALID_SALT");
		keyStore.setSecretKey("mssql-keystore-container");

		Map<Integer, KeyStoreDTO> mapKeys = new HashMap<Integer, KeyStoreDTO>();
		mapKeys.put(keyStore.getId(), keyStore);

		List<CompleteDataDTO> completeDataDTOResult = businessDataService.decryptListData(completeDataDTOParameter,
				mapKeys);

		for (int i = 0; i < completeDataDTOResult.size(); i++) {
			CompleteDataDTO selectedData = completeDataDTOResult.get(i);

			assertEquals(selectedData.getPlaintext(), decryptedExpectedResults.get(i));
		}
	}

	@Test
	public void T4_decryptDataListSucessfully() {

		List<String> decryptedExpectedResults = new ArrayList<String>();
		decryptedExpectedResults.add("123456");

		CompleteDataDTO c1 = new CompleteDataDTO();
		c1.setEncryptted("TVE9PS5ZaVJHaWRVbWlyOHU3UWJJMDVuUHF3PT0=");

		List<CompleteDataDTO> completeDataDTOParameter = new ArrayList<CompleteDataDTO>();
		completeDataDTOParameter.add(c1);
		KeyStoreDTO keyStore = new KeyStoreDTO();

		keyStore.setId(1);
		keyStore.setSalt("mssql-keystore-container");
		keyStore.setSecretKey("mssql-keystore-container");

		Map<Integer, KeyStoreDTO> mapKeys = new HashMap<Integer, KeyStoreDTO>();
		mapKeys.put(keyStore.getId(), keyStore);

		List<CompleteDataDTO> completeDataDTOResult = businessDataService.decryptListData(completeDataDTOParameter,
				mapKeys);

		for (int i = 0; i < completeDataDTOResult.size(); i++) {
			CompleteDataDTO selectedData = completeDataDTOResult.get(i);

			assertEquals(selectedData.getPlaintext(), decryptedExpectedResults.get(i));
		}
	}

	@Test(expected = ServiceException.class)
	public void T5_decryptWithNullTextToDecrypt() {

		CompleteDataDTO c1 = new CompleteDataDTO();
		c1.setEncryptted(null);

		List<CompleteDataDTO> completeDataDTOParameter = new ArrayList<CompleteDataDTO>();
		completeDataDTOParameter.add(c1);
		KeyStoreDTO keyStore = new KeyStoreDTO();

		Map<Integer, KeyStoreDTO> mapKeys = new HashMap<Integer, KeyStoreDTO>();
		mapKeys.put(keyStore.getId(), keyStore);

		businessDataService.decryptListData(completeDataDTOParameter, mapKeys);

	}
	
	@Test(expected = ServiceException.class)
	public void T5_decryptWithBlankTextToDecrypt() {

		CompleteDataDTO c1 = new CompleteDataDTO();
		c1.setEncryptted("");

		List<CompleteDataDTO> completeDataDTOParameter = new ArrayList<CompleteDataDTO>();
		completeDataDTOParameter.add(c1);
		KeyStoreDTO keyStore = new KeyStoreDTO();

		Map<Integer, KeyStoreDTO> mapKeys = new HashMap<Integer, KeyStoreDTO>();
		mapKeys.put(keyStore.getId(), keyStore);

		businessDataService.decryptListData(completeDataDTOParameter, mapKeys);

	}	

}