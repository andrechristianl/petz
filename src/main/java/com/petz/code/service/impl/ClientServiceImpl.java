package com.petz.code.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.petz.code.core.utils.ObjectDataConverterUtil;
import com.petz.code.model.dto.ClientDTO;
import com.petz.code.repository.ClientRepository;
import com.petz.code.repository.entity.Client;
import com.petz.code.service.ClientService;
import com.petz.code.validation.ClientValidation;

@Service
public class ClientServiceImpl implements ClientService {
	@Autowired
	private ClientRepository clientRepository;

	@Override
	public void clientAddListData(ClientDTO clientDTO) {
		ClientValidation.validateRules(clientDTO);
		Client client = ObjectDataConverterUtil.copyObject(clientDTO, Client.class);
		this.clientRepository.save(client);
	}

	@Override
	public void updateClient(int entryId, ClientDTO clientDTO) {
		ClientValidation.validateRules(clientDTO);
		Client client = this.clientRepository.findById(entryId);
		this.clientRepository.save(client);
	}

	@Override
	public ClientDTO findClient(int entryId) {
		Client client = this.clientRepository.findById(entryId);
		ClientDTO clientDTO = ObjectDataConverterUtil.copyObject(client, ClientDTO.class);
		return clientDTO;
	}

	@Override
	public List<ClientDTO> findAllClient() {
		List<Client> client = this.clientRepository.findAll();
		List<ClientDTO> clientDTO = ObjectDataConverterUtil.copyList(client, ClientDTO.class);
		return clientDTO;
	}
}
