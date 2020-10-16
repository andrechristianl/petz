package com.petz.code.service;


import java.util.List;

import com.petz.code.model.dto.ClientDTO;

public interface ClientService {
	void clientAddListData(ClientDTO clientDTO);

	void updateClient(int entryId, ClientDTO clientDTO);

	ClientDTO findClient(int entryId);

	List<ClientDTO> findAllClient();
}
