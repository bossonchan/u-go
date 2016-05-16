package com.ugo.server.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ugo.server.domain.repository.BuyerRepository;
import com.ugo.server.domain.repository.SellerRepository;

@Service
public class AccountManager {

	final BuyerRepository buyerRepo;
	final SellerRepository sellerRepo;
	
	@Autowired
	public AccountManager(
			BuyerRepository buyerRepo,
			SellerRepository sellerRepo) {
		this.buyerRepo = buyerRepo;
		this.sellerRepo = sellerRepo;
	}
}
