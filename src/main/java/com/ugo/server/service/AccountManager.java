package com.ugo.server.service;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ugo.server.domain.entity.Buyer;
import com.ugo.server.domain.entity.Seller;
import com.ugo.server.domain.repository.BuyerRepository;
import com.ugo.server.domain.repository.SellerRepository;
import com.ugo.server.web.AccountController;

@Service
public class AccountManager {
	
	private static final Logger log = LoggerFactory.getLogger(AccountManager.class);

	final BuyerRepository buyerRepo;
	final SellerRepository sellerRepo;
	
	@Autowired
	public AccountManager(
			BuyerRepository buyerRepo,
			SellerRepository sellerRepo) {
		this.buyerRepo = buyerRepo;
		this.sellerRepo = sellerRepo;
	}

	public Buyer registerAsBuyer(Buyer buyer) {
		if (this.buyerRepo.findByUsername(buyer.getUsername()) != null) {
			return null;
		} else {
			return this.buyerRepo.save(buyer);
		}
	}

	public Buyer loginAsBuyer(Buyer buyer) {
		Buyer found = this.buyerRepo.findByUsername(buyer.getUsername());
		if (found != null && found.getPassword().equals(buyer.getPassword())) {
			return found;
		}
		return null;
	}

	public Seller loginAsSeller(Seller seller) {
		Seller found = this.sellerRepo.findByUsername(seller.getUsername());
		if (found != null && found.getPassword().equals(seller.getPassword())) {
			return found;
		}
		return null;
	}

	public Seller registerAsSeller(Seller seller) {
		if (this.sellerRepo.findByUsername(seller.getUsername()) != null) {
			if (this.sellerRepo.findByIdentity(seller.getIdentity()) != null) {
				return null;
			}
			return null;
		} else {
			return this.sellerRepo.save(seller);
		}
	}

	public Seller getSellerFromSession(HttpSession session) {
		if (!("seller".equals(session.getAttribute("SESS_ATTR_ROLE")))){
			return null;
		}
		if (session.getAttribute("SESS_ATTR_USER") == null) {
			return null;
		}
		return this.sellerRepo.findOne((Long)session.getAttribute("SESS_ATTR_USER"));
	}
	
	public Buyer getBuyerFromSession(HttpSession session) {
		
		if (!("buyer".equals(session.getAttribute("SESS_ATTR_ROLE")))){
			return null;
		}
		if (session.getAttribute("SESS_ATTR_USER") == null) {
			return null;
		}
		return this.buyerRepo.findOne((Long)session.getAttribute("SESS_ATTR_USER"));
	}
}
