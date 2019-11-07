package com.itau.latam.keystore.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.itau.latam.keystore.repository.entity.KeyTable;

public interface KeyTableRepository extends JpaRepository<KeyTable, Integer>{
	List<KeyTable> findBydateCreated(String date);
	
	List<KeyTable> findById(int id);
}
