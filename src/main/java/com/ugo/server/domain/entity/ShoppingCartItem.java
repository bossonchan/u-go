package com.ugo.server.domain.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class ShoppingCartItem {
	@Id
	@GeneratedValue
	private Long id;
	
	private Date time;
	
	@Enumerated(value=EnumType.STRING)
	private Status status;
	
	@ManyToOne
	@JoinColumn(name = "buyerId")
	private Buyer buyer;
	
	@ManyToOne
	@JoinColumn(name = "itemId")
	private Item item;
	
	public ShoppingCartItem() {}

	public ShoppingCartItem(Buyer buyer, Item item) {
		this.time = new Date();
		this.buyer = buyer;
		this.item = item;
		this.status = Status.ACTIVE;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getTime() {
		return time;
	}

	public void setTime(Date time) {
		this.time = time;
	}

	public Buyer getBuyer() {
		return buyer;
	}

	public void setBuyer(Buyer buyer) {
		this.buyer = buyer;
	}

	public Item getItem() {
		return item;
	}

	public void setItem(Item item) {
		this.item = item;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}
	
	
}
