package com.itau.latam.keystore.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


import com.itau.latam.keystore.repository.entity.KeyStore;

@Repository
public interface KeyStoreRepository extends JpaRepository<KeyStore, Integer>{
	
	
	List<KeyStore> findById(int id);

	@Query(value ="SELECT TOP 1 * FROM  Stores.dbo.KeyStore  WITH (NOLOCK) ORDER BY created_date DESC",
			nativeQuery = true)
	List<KeyStore> findAllCreateDate();
	
	
	
}
