package com.ugo.server.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ugo.server.domain.entity.Buyer;
import com.ugo.server.domain.entity.Seller;
import com.ugo.server.service.AccountManager;

@RestController
public class AccountController {
	
	final AccountManager accountManager;
	
	@Autowired
	public AccountController(AccountManager accountManager) {
		this.accountManager = accountManager;
	}
	
	// ----------- Buyer APIs ----------
	
	@RequestMapping(method = RequestMethod.POST, value = "/buyer/session")
	public Buyer loginAsBuyer() {
		return null;
	}
	
	@RequestMapping(method = RequestMethod.DELETE, value = "/buyer/session")
	public Buyer logoutAsBuyer(){
		return null;
	}
	
	@RequestMapping(method = RequestMethod.POST, value = "/buyer")
	public Buyer registerAsBuyer() {
		return null;
	}
	
	// --------- Seller APIs ----------
	
	@RequestMapping(method = RequestMethod.POST, value = "/seller/session")
	public Seller loginAsSeller() {
		return null;	
	}
	
	@RequestMapping(method = RequestMethod.DELETE, value = "/seller/session")
	public Seller logoutAsSeller(){
		return null;
	}
	
	@RequestMapping(method = RequestMethod.POST, value = "/seller")
	public Seller registerAsSeller() {
		return null;
	}
}
