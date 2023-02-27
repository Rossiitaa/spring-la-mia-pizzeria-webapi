package com.corjava.pizzeria.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.corjava.pizzeria.model.Offerta;

public interface OffertaRepo extends JpaRepository<Offerta, Integer> {

}
