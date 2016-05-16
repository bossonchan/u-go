package com.ugo.server.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.ugo.server.domain.entity.Item;
import com.ugo.server.domain.entity.Reservation;
import com.ugo.server.domain.entity.ShoppingCartItem;
import com.ugo.server.service.AccountManager;
import com.ugo.server.service.ItemManager;

public class ItemController {

	final AccountManager accountManager;
	final ItemManager itemManager;
	
	@Autowired
	public ItemController(AccountManager accountManager, ItemManager itemManager) {
		this.accountManager = accountManager;
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
	public Item postItem() {
		return null;
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
