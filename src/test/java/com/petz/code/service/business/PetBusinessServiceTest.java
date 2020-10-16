package com.petz.code.service.business;

import org.hibernate.service.spi.ServiceException;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.petz.code.model.dto.ClientDTO;
import com.petz.code.validation.ClientValidation;
@ContextConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class PetBusinessServiceTest {

	@Test(expected = ServiceException.class)
	public void createClientTest_emptyCodeObject() {
		ClientValidation.validateRules(null);
	}
	
	@Test(expected = ServiceException.class)
	public void createClientTest_emptyClientValue() {
		ClientDTO client = new ClientDTO();
		client.setName("");
		ClientValidation.validateRules(client);
	}
	
	@Test(expected = ServiceException.class)
	public void createClientTest_nullClientValue() {
		ClientDTO client = new ClientDTO();
		client.setName(null);
		ClientValidation.validateRules(client);
	}
}