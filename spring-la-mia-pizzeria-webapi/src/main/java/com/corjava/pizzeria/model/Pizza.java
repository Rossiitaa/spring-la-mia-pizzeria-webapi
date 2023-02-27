package com.corjava.pizzeria.model;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "pizze")
public class Pizza {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)

	private Integer id;
	@NotEmpty(message = "deve essere pieno")
	@NotNull(message = "Questo campo non può essere vuoto")
	@Size(min = 5, max = 250)

	private String name;
	@NotEmpty(message = "deve essere pieno")
	@NotNull(message = "Questo campo non può essere vuoto")
	@Size(min = 5, max = 250)

	private String description;
	@NotEmpty(message = "deve essere pieno")
	@NotNull(message = "Questo campo non può essere vuoto")
	@Size(min = 5, max = 250)

	private String img;
	@NotNull(message = "Questo campo non può essere vuoto")
	@DecimalMin("1.00")
	@DecimalMax("60.00")

	private int price;

	@OneToMany(mappedBy = "pizza")
	private List<Offerta> offerte;

	public List<Offerta> getOfferte() {
		return offerte;
	}

	public void setOfferte(List<Offerta> offerte) {
		this.offerte = offerte;
	}
	
	@ManyToMany
	private List<Ingredienti> ingredienti;
	
	public List<Ingredienti> getIngredienti() {
		return ingredienti;
	}
	public void setIngredienti(List<Ingredienti> ingredienti) {
		this.ingredienti = ingredienti;
	}
	
	

	public Integer getId() {
		return id;
	}

	public void setId(int id) {
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

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public String getImg() {
		return img;
	}

	public void setImg(String img) {
		this.img = img;
	}
}