package com.ugo.server.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ugo.server.domain.entity.Seller;

public interface SellerRepository extends JpaRepository<Seller, Long>{
	Seller findByUsername(String username);
	Seller findByIdentity(String identity);
}
