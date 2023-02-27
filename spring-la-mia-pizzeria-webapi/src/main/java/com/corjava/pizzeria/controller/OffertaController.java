package com.corjava.pizzeria.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.corjava.pizzeria.model.Offerta;
import com.corjava.pizzeria.model.Pizza;
import com.corjava.pizzeria.repository.OffertaRepo;
import com.corjava.pizzeria.repository.PizzaRepo;

import ch.qos.logback.core.model.Model;
import jakarta.validation.Valid;

@Controller
@RequestMapping("/offerte")
public class OffertaController {

	@Autowired
	PizzaRepo pizzaRepo;

	@Autowired
	OffertaRepo offertaRepo;

	@GetMapping("/create")
	public String create(@RequestParam(name = "pizzaId", required = true) Integer pizzaId, ModelMap model) {
		Offerta offerta = new Offerta();
		Pizza pizza = pizzaRepo.getReferenceById(pizzaId);
		offerta.setPizza(pizza);
		model.addAttribute("offerta", offerta);
		return "create-offerta";
	}

	@PostMapping("/create")
	public String store(@Valid @ModelAttribute("offerta") Offerta formOfferta, BindingResult bindingResult,
			Model model) {

		if (bindingResult.hasErrors()) {
			return "create-offerta";
		}
		offertaRepo.save(formOfferta);
		return "redirect:/details/" + formOfferta.getPizza().getId();
	}

}
