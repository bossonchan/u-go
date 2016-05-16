package com.ugo.server.web;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ugo.server.domain.entity.Item;
import com.ugo.server.domain.entity.Reservation;
import com.ugo.server.domain.entity.Seller;
import com.ugo.server.domain.entity.ShoppingCartItem;
import com.ugo.server.domain.entity.Status;
import com.ugo.server.service.ItemManager;

@RestController
public class ItemController {

	final ItemManager itemManager;
	
	@Autowired
	public ItemController(ItemManager itemManager) {
		this.itemManager = itemManager;
	}
	
	// ----------- Buyer APIs ----------
	@RequestMapping(method = RequestMethod.GET, value = "/items/{itemId}")
	public Item getItemById() {
		return null;
	}

	@RequestMapping(method = RequestMethod.POST, value = "/items/{itemId}/reservation")
	public Reservation reserveItem() {
		return null;
	}
	
	@RequestMapping(method = RequestMethod.POST, value = "/items/{itemId}/shoppingcart")
	public ShoppingCartItem addItemToShoppiingCart() {
		return null;
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/shoppingcart")
	public List<ShoppingCartItem> getShopingCartItems() {
		return null;
	}
	
	@RequestMapping(method = RequestMethod.DELETE, value = "/shoppingcart/{itemId}")
	public ShoppingCartItem deleteItemFromShoppingCart() {
		return null;
	}
	
	// --------- Seller APIs ----------
	
	@RequestMapping(method = RequestMethod.POST, value = "/photos")
	public String uploadPhoto() {
		return "";
	}
	
	@RequestMapping(method = RequestMethod.POST, value = "/items")
	public Item postItem(@RequestBody Item item, HttpServletRequest request) {
		HttpSession session = request.getSession();
		Seller seller = (Seller) session.getAttribute("SESS_ATTR_USER");
		if (seller == null) {
			throw new UnauthorizedException("You should logged in as seller");
		}
		if (!("seller".equals(session.getAttribute("SESS_ATTR_ROLE")))) {
			throw new ForbiddenException("Only seller can create item");
		}
		
		if (item.getName() == null) {
			throw new BadRequestException("name required");
		}
		if (item.getDescription() == null) {
			throw new BadRequestException("description required");
		}
		if (item.getCategory() == null) {
			throw new BadRequestException("category required");
		}
		item.setSeller(seller);
		item.setTime(new Date());
		item.setStatus(Status.ACTIVE);
		return this.itemManager.createItem(item);
	}
	
	@RequestMapping(method = RequestMethod.DELETE, value = "/items/{itemId}")
	public Item deleteItem() {
		return null;
	}
	
	// ----- same API entry, different behavior according to role ----
	@RequestMapping(method = RequestMethod.GET, value = "/reservations")
	public List<Reservation> getReservations() {
		return null;
	}
	
	@RequestMapping(method = RequestMethod.DELETE, value = "/reservations/{reservationId}")
	public Reservation rejectReservation() {
		return null;
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/items")
	public List<Item> getItems() {
		return null;
	}
}
