package com.ugo.server.domain.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class ItemPhoto {
	@Id
	@GeneratedValue
	private Long id;
	
	private String url;
	
	@ManyToOne
	@JoinColumn(name = "itemId")
	private Item item;
	
	@ManyToOne
	@JoinColumn(name = "seller")
	private Seller seller;
	
	public ItemPhoto() {}
	
	public ItemPhoto(String url) {
		this.url = url;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}
	
	
	
}
