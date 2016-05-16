package com.ugo.server.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.HttpStatus;

import com.ugo.server.domain.entity.Buyer;
import com.ugo.server.domain.entity.Seller;
import com.ugo.server.service.AccountManager;

@RestController
public class AccountController {
	
	private static final Logger log = LoggerFactory.getLogger(AccountController.class);
	private static final String SESS_ATTR_USER = "SESS_ATTR_USER";
	private static final String SESS_ATTR_ROLE = "SESS_ATTR_ROLE";
	
	final AccountManager accountManager;
	
	@Autowired
	public AccountController(AccountManager accountManager) {
		this.accountManager = accountManager;
	}
	
	// ----------- Buyer APIs ----------
	
	@RequestMapping(method = RequestMethod.POST, value = "/buyer/session")
	public Buyer loginAsBuyer(@RequestBody Buyer buyer,  HttpServletRequest request) {
		HttpSession session = request.getSession();
		if (session.getAttribute(SESS_ATTR_USER) != null) {
			throw new ConflictException("You have been logged in.");
		}
		
		buyer =  this.accountManager.loginAsBuyer(buyer);
		if (buyer == null) {
			throw new BadRequestException("Login failed, username or password is wrong");
		}
		
		session.setAttribute(SESS_ATTR_USER, buyer.getId());
		session.setAttribute(SESS_ATTR_ROLE, "buyer");
		return buyer;
	}
	
	@RequestMapping(method = RequestMethod.DELETE, value = "/buyer/session")
	public Buyer logoutAsBuyer(HttpServletRequest request){
		HttpSession session = request.getSession();
		if (session.getAttribute(SESS_ATTR_USER) == null) {
			throw new ConflictException("You have been logged out.");
		}
		session.setAttribute(SESS_ATTR_USER, null);
		session.setAttribute(SESS_ATTR_ROLE, null);
		return null;
	}
	
	@RequestMapping(method = RequestMethod.POST, value = "/buyer")
	public Buyer registerAsBuyer(@RequestBody Buyer buyer) {
		if (buyer.getPassword() == null) {
			throw new BadRequestException("password required");
		}
		if (buyer.getUsername() == null) {
			throw new BadRequestException("username required");
		}
		if (buyer.getPhoneNumber() == null) {
			throw new BadRequestException("phoneNumber required");
		}
		buyer = this.accountManager.registerAsBuyer(buyer);
		if (buyer == null) {
			throw new BadRequestException("create failed, username taken");
		}
		return buyer;
	}
	
	// --------- Seller APIs ----------
	
	@RequestMapping(method = RequestMethod.POST, value = "/seller/session")
	public Seller loginAsSeller(@RequestBody Seller seller,  HttpServletRequest request) {
		HttpSession session = request.getSession();
		if (session.getAttribute(SESS_ATTR_USER) != null) {
			throw new ConflictException("You have been logged in.");
		}
		
		seller =  this.accountManager.loginAsSeller(seller);
		if (seller == null) {
			throw new BadRequestException("Login failed, username or password is wrong");
		}
		
		session.setAttribute(SESS_ATTR_USER, seller.getId());
		session.setAttribute(SESS_ATTR_ROLE, "seller");
		return seller;	
	}
	
	@RequestMapping(method = RequestMethod.DELETE, value = "/seller/session")
	public Seller logoutAsSeller(HttpServletRequest request){
		HttpSession session = request.getSession();
		if (session.getAttribute(SESS_ATTR_USER) == null) {
			throw new ConflictException("You have been logged out.");
		}
		session.setAttribute(SESS_ATTR_USER, null);
		session.setAttribute(SESS_ATTR_ROLE, null);
		return null;
	}
	
	@RequestMapping(method = RequestMethod.POST, value = "/seller")
	public Seller registerAsSeller(@RequestBody Seller seller) {
		if (seller.getPassword() == null) {
			throw new BadRequestException("password required");
		}
		if (seller.getUsername() == null) {
			throw new BadRequestException("username required");
		}
		if (seller.getIdentity() == null) {
			throw new BadRequestException("identity required");
		}
		if (seller.getPhoneNumber() == null) {
			throw new BadRequestException("phoneNumber required");
		}
		seller = this.accountManager.registerAsSeller(seller);
		if (seller == null) {
			throw new BadRequestException("create failed, username or identity taken");
		}
		return seller;
	}
}

@ResponseStatus(HttpStatus.BAD_REQUEST)
class BadRequestException extends RuntimeException {
	public BadRequestException(String message) {
		super(message);
	}
}

@ResponseStatus(HttpStatus.UNAUTHORIZED)
class UnauthorizedException extends RuntimeException {
	public UnauthorizedException(String message) {
		super(message);
	}
}

@ResponseStatus(HttpStatus.FORBIDDEN)
class ForbiddenException extends RuntimeException {
	public ForbiddenException(String message) {
		super(message);
	}
}

@ResponseStatus(HttpStatus.NOT_FOUND)
class NotFoundException extends RuntimeException {
	public NotFoundException(String message) {
		super(message);
	}
}

@ResponseStatus(HttpStatus.CONFLICT)
class ConflictException extends RuntimeException {
	public ConflictException(String message) {
		super(message);
	}
}