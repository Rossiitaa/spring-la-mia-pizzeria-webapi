package com.corjava.pizzeria.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.corjava.pizzeria.model.Ingredienti;

public interface IngredientiRepo extends JpaRepository<Ingredienti, Integer> {

	List<Ingredienti> findByNomeLike(String string);

}
