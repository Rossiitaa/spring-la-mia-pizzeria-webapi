package com.corjava.pizzeria.repository;

import java.util.List;


import org.springframework.data.jpa.repository.JpaRepository;

import com.corjava.pizzeria.model.Pizza;

public interface PizzaRepo extends JpaRepository<Pizza, Integer> {

	public List<Pizza> findByNameLike(String keyword); 

}