package com.ugo.server.web;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.ugo.server.Application;
import com.ugo.server.domain.entity.Buyer;
import com.ugo.server.domain.entity.Item;
import com.ugo.server.domain.entity.Reservation;
import com.ugo.server.domain.entity.Seller;
import com.ugo.server.domain.entity.ShoppingCartItem;
import com.ugo.server.domain.entity.Status;
import com.ugo.server.service.AccountManager;
import com.ugo.server.service.ItemManager;

@RestController
public class ItemController {

	private static final Logger log = LoggerFactory.getLogger(ItemController.class);

	final ItemManager itemManager;
	final AccountManager accountManager;
	
	@Autowired
	public ItemController(ItemManager itemManager, AccountManager accountManager) {
		this.itemManager = itemManager;
		this.accountManager = accountManager;
	}
	
	// ----------- Buyer APIs ----------
	@RequestMapping(method = RequestMethod.GET, value = "/items/{itemId}")
	public Item getItemById(@PathVariable Long itemId) {
		Item item = this.itemManager.findItemById(itemId);
		if (item == null) {
			throw new NotFoundException("Could not find the item");
		} else {
			return item;
		}
	}

	@RequestMapping(method = RequestMethod.POST, value = "/items/{itemId}/reservation")
	public Reservation reserveItem(@PathVariable Long itemId, HttpServletRequest request) {
		
		HttpSession session = request.getSession();
		
		Buyer buyer = this.accountManager.getBuyerFromSession(session);
		
		if (!("buyer".equals(session.getAttribute("SESS_ATTR_ROLE")))){
			throw new ForbiddenException("Only buyer can reserve items.");
		}
		
		if (buyer == null) {
			throw new UnauthorizedException("You should logged in as buyer.");
		}
		
		Item item = this.itemManager.findItemById(itemId);
		if (item == null) {
			throw new NotFoundException("Could not find the item");
		}
		if (item.getStatus() == Status.INACTIVE || item.getStatus() == Status.REMOVED) {
			throw new ForbiddenException("Could not reserve a removed item.");
		}
		return this.itemManager.reserveItem(buyer, item);
	}
	
	@RequestMapping(method = RequestMethod.POST, value = "/items/{itemId}/shoppingcart")
	public ShoppingCartItem addItemToShoppiingCart(@PathVariable Long itemId, HttpServletRequest request) {
		HttpSession session = request.getSession();
		
		Buyer buyer = this.accountManager.getBuyerFromSession(session);
		
		if (!("buyer".equals(session.getAttribute("SESS_ATTR_ROLE")))){
			throw new ForbiddenException("Only buyer can add shopping-cart items.");
		}
		
		if (buyer == null) {
			throw new UnauthorizedException("You should logged in as buyer.");
		}
		
		Item item = this.itemManager.findItemById(itemId);
		if (item == null) {
			throw new NotFoundException("Could not find the item");
		}
		
		if (item.getStatus() == Status.INACTIVE || item.getStatus() == Status.REMOVED) {
			throw new ForbiddenException("Could not collect a removed item.");
		}
		
		return this.itemManager.addToShoppingCart(buyer, item);
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/shoppingcart")
	public List<ShoppingCartItem> getShopingCartItems(HttpServletRequest request) {
		HttpSession session = request.getSession();
		
		Buyer buyer = this.accountManager.getBuyerFromSession(session);
		
		if (!("buyer".equals(session.getAttribute("SESS_ATTR_ROLE")))){
			throw new ForbiddenException("Only buyer can get shopping-cart items.");
		}
		
		if (buyer == null) {
			throw new UnauthorizedException("You should logged in as buyer.");
		}
		return this.itemManager.getShoppingCartItems(buyer);
	}
	
	@RequestMapping(method = RequestMethod.DELETE, value = "/shoppingcart/{shoppingCartItemId}")
	public ShoppingCartItem deleteItemFromShoppingCart(@PathVariable Long shoppingCartItemId, HttpServletRequest request) {
		HttpSession session = request.getSession();
		
		Buyer buyer = this.accountManager.getBuyerFromSession(session);
		
		if (!("buyer".equals(session.getAttribute("SESS_ATTR_ROLE")))){
			throw new ForbiddenException("Only buyer can remove shopping-cart items.");
		}
		
		if (buyer == null) {
			throw new UnauthorizedException("You should logged in as buyer.");
		}
		
		ShoppingCartItem shoppingCartItem = this.itemManager.findShoppingCartItemById(shoppingCartItemId);
		if (shoppingCartItem == null) {
			throw new NotFoundException("Could not find the item");
		}
		
		if (shoppingCartItem.getBuyer().getId() != buyer.getId()) {
			throw new ForbiddenException("Only owner can handle the item");
		}
		
		return this.itemManager.removeShoppingCartItem(shoppingCartItem);
	}
	
	// --------- Seller APIs ----------
	
	@RequestMapping(method = RequestMethod.POST, value = "/photos")
	public Map<String, Object> uploadPhoto(@RequestParam("file") MultipartFile file) {
		if (file.isEmpty()) {
			throw new BadRequestException("file is empty.");
		}
		
		Map<String, Object> result = new HashMap<String, Object>();
		
		try {
			byte[] bytes = file.getBytes();
			String rootPath = "static";
			String uploadDir = "uploads";
			File dir = new File(rootPath + File.separator + uploadDir);
			if (!dir.exists()) {
				dir.mkdirs();
			}
		
			String name = (new Date().getTime())  + ".jpg";
			File serverFile = new File(dir.getAbsolutePath() + File.separator + name);
			BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(serverFile));
			
			stream.write(bytes);
			stream.close();
			result.put("url", File.separator + uploadDir + File.separator + name);
			
			return result;
			
		} catch (Exception e) {
			
			result.put("url", "");
			return result;
		}
	}
	
	@RequestMapping(method = RequestMethod.POST, value = "/items")
	public Item postItem(@RequestBody Item item, HttpServletRequest request) {
		HttpSession session = request.getSession();
		Seller seller = this.accountManager.getSellerFromSession(session);

		if (!("seller".equals(session.getAttribute("SESS_ATTR_ROLE")))) {
			throw new ForbiddenException("Only seller can create item");
		}
		
		if (seller == null) {
			throw new UnauthorizedException("You should logged in as seller");
		}
		
		if (item.getName() == null || "".equals(item.getName())) {
			throw new BadRequestException("name required");
		}
		if (item.getDescription() == null || "".equals(item.getDescription())) {
			throw new BadRequestException("description required");
		}
		if (item.getCategory() == null) {
			throw new BadRequestException("category required");
		}
		return this.itemManager.createItem(seller, item);
	}
	
	@RequestMapping(method = RequestMethod.DELETE, value = "/items/{itemId}")
	public Item deleteItem(@PathVariable Long itemId, HttpServletRequest request) {
		HttpSession session = request.getSession();
		Seller seller = this.accountManager.getSellerFromSession(session);

		if (!("seller".equals(session.getAttribute("SESS_ATTR_ROLE")))) {
			throw new ForbiddenException("Only seller can create item");
		}
		
		if (seller == null) {
			throw new UnauthorizedException("You should logged in as seller");
		}
		
		Item item = this.itemManager.findItemById(itemId);
		if (item == null) {
			throw new NotFoundException("Could not find the item");
		}
		return this.itemManager.removeItem(item);
	}
	
	// ----- same API entry, different behavior according to role ----
	
	@RequestMapping(method = RequestMethod.GET, value = "/items")
	public List<Item> getItems(@RequestParam(name = "search", defaultValue = "", required = false) String search,  HttpServletRequest request) {
		HttpSession session = request.getSession();
		Seller seller = this.accountManager.getSellerFromSession(session);
		if ("seller".equals(session.getAttribute("SESS_ATTR_ROLE")) && seller != null){
			return this.itemManager.getSellerItems(seller);
		} else {
			if ("".equals(search)) {
				return this.itemManager.getActiveItems();				
			} else {
				return this.itemManager.getActiveItemsAccordingToSearch(search);
			}
			
		}
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/reservations")
	public List<Reservation> getReservations(HttpServletRequest request) {
		HttpSession session = request.getSession();
		Seller seller = this.accountManager.getSellerFromSession(session);
		if (seller != null) {
			return this.itemManager.getSellerReservations(seller);
		}
		
		Buyer buyer =  this.accountManager.getBuyerFromSession(session);
		if (buyer != null) {
			return this.itemManager.getBuyerReservations(buyer);
		}
		
		throw new UnauthorizedException("Please logged in.");
	}
	
	@RequestMapping(method = RequestMethod.DELETE, value = "/reservations/{reservationId}")
	public Reservation deleteOrRejectReservation(@PathVariable Long reservationId, HttpServletRequest request) {
		
		Reservation reservation = this.itemManager.findReservationById(reservationId);
		if (reservation == null) {
			throw new NotFoundException("Could not find the reservation.");
		}
		
		HttpSession session = request.getSession();
		Seller seller = this.accountManager.getSellerFromSession(session);
		if (seller != null) {
			if (reservation.getSeller() == null || reservation.getSeller().getId() != seller.getId()) {
				throw new ForbiddenException("Only owner can handle the reservation");
			}
			return this.itemManager.rejectReservation(reservation);
		}
		
		Buyer buyer =  this.accountManager.getBuyerFromSession(session);
		if (buyer != null) {
			if (reservation.getBuyer() == null || reservation.getBuyer().getId() != buyer.getId()) {
				throw new ForbiddenException("Only creator can remove the reservation");
			}
			return this.itemManager.removeReservation(reservation);
		}

		throw new UnauthorizedException("Please logged in.");
	}

}
