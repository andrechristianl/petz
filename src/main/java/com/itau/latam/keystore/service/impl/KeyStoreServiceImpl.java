package com.itau.latam.keystore.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.itau.latam.core.service.impl.FormatterServiceImpl;
import com.itau.latam.keystore.model.dto.KeyStoreDTO;
import com.itau.latam.keystore.repository.KeyStoreRepository;
import com.itau.latam.keystore.repository.entity.KeyStore;
import com.itau.latam.keystore.service.KeyStoreService;

@Service
public class KeyStoreServiceImpl implements KeyStoreService{
    @Autowired
    private KeyStoreRepository keyTableRepository;
    
    @Override
    public KeyStoreDTO findByDate() {
        KeyStore mainTable = this.keyTableRepository.findLatestDate();
        KeyStoreDTO mainTableDTO = FormatterServiceImpl.copyObject( mainTable, KeyStoreDTO.class);
        return mainTableDTO;
    }

    @Override
    public KeyStoreDTO findById(int id) {
        KeyStore mainTable = this.keyTableRepository.findById(id);
        KeyStoreDTO mainTableDTO = FormatterServiceImpl.copyObject(mainTable, KeyStoreDTO.class);
        return mainTableDTO;
    }
}
