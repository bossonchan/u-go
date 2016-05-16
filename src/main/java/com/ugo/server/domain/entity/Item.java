package com.ugo.server.domain.entity;

import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Item {
	@Id
	@GeneratedValue
	private Long id;
	
	private String name;
	private String description;
	private float price;
	private Date time;
	private Category category;
	private Status status;
	
	@OneToMany(mappedBy = "item", fetch = FetchType.LAZY)
	@JsonIgnore
	private List<Reservation> reservations;
	
	@OneToMany(mappedBy = "item", fetch = FetchType.LAZY)
	@JsonIgnore
	private List<ShoppingCartItem> shoppingCartItems;
	
	@ManyToOne
	@JoinColumn(name = "sellerId")
	private Seller seller;
	
	@OneToMany(mappedBy = "item", fetch = FetchType.EAGER)
	private List<ItemPhoto> itemPhotos;
	
	public Item() {}
	
	public Item(String name, String description, float price, Category category) {
		this.name= name;
		this.description = description;
		this.price = price;
		this.category = category;
		this.time =new Date();
		this.status = Status.ACTIVE;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	public Date getTime() {
		return time;
	}

	public void setTime(Date time) {
		this.time = time;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public List<Reservation> getReservations() {
		return reservations;
	}

	public void setReservations(List<Reservation> reservations) {
		this.reservations = reservations;
	}

	public List<ShoppingCartItem> getShoppingCartItems() {
		return shoppingCartItems;
	}

	public void setShoppingCartItems(List<ShoppingCartItem> shoppingCartItems) {
		this.shoppingCartItems = shoppingCartItems;
	}

	public Seller getSeller() {
		return seller;
	}

	public void setSeller(Seller seller) {
		this.seller = seller;
	}

	public List<ItemPhoto> getItemPhotos() {
		return itemPhotos;
	}

	public void setItemPhotos(List<ItemPhoto> itemPhotos) {
		this.itemPhotos = itemPhotos;
	}
	
}

