package com.itau.latam.keystore.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.itau.latam.keystore.model.dto.CompleteDataDTO;
import com.itau.latam.keystore.repository.CompleteDataRepository;
import com.itau.latam.keystore.service.CompleteDataService;

@Service
public class CompleteDataServiceImpl implements CompleteDataService{
	
	@Autowired
	private CompleteDataRepository completeDataRepository;

	@Override
	public List<CompleteDataDTO> encrypt(CompleteDataDTO completeData) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<CompleteDataDTO> decrypt(CompleteDataDTO completeData) {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	
	
	
	
	

}
