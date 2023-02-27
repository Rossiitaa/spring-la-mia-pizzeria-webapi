package com.corjava.pizzeria.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.corjava.pizzeria.model.Ingredienti;
import com.corjava.pizzeria.model.Pizza;
import com.corjava.pizzeria.repository.IngredientiRepo;
import com.corjava.pizzeria.repository.PizzaRepo;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/")
public class PizzaController {
	@Autowired
	PizzaRepo pizzaRepo;
	
	@Autowired
	private IngredientiRepo ingredientiRepo;

	@GetMapping
	public String index(@RequestParam(name = "keyword", required = false) String keyword, Model model) {
		List<Pizza> elencoPizze;
		if (keyword == null) {
			elencoPizze = pizzaRepo.findAll();
		} else {
			elencoPizze = pizzaRepo.findByNameLike("%" + keyword + "%");
		}

		model.addAttribute("pizze", elencoPizze);
		return "index";
	}

	@GetMapping("/details/{id}")
	public String details(@PathVariable("id") Integer id, Model model) {
		Pizza pizza = pizzaRepo.getReferenceById(id);
		model.addAttribute("pizza", pizza);
		return "details";
	}

	@GetMapping("/create")
	public String create(Model model) {
		Pizza pizza = new Pizza();
		List<Ingredienti> ingredienti = ingredientiRepo.findAll(Sort.by("name"));
		model.addAttribute("ingredienti", ingredienti);
		model.addAttribute("pizza", pizza);
		return "create";
	}

	@PostMapping("/create")
	public String store(@Valid @ModelAttribute("pizza") Pizza formPizza, BindingResult bindingResult, Model model) {
		if (bindingResult.hasErrors())
			return "create";

		pizzaRepo.save(formPizza);
		return "redirect:/";
	}

	@GetMapping("/edit/{id}")
	public String edit(@PathVariable("id") Integer id, Model model) {
		Pizza pizza = pizzaRepo.getReferenceById(id);
		model.addAttribute("pizza", pizza);
		List<Ingredienti> ingredienti = ingredientiRepo.findAll(Sort.by("name"));
		model.addAttribute("ingredienti", ingredienti);
		return "edit";
	}

	@PostMapping("/edit/{id}")
	public String update(@Valid @ModelAttribute Pizza formPizza, BindingResult bindingResult, Model model) {

		if (bindingResult.hasErrors())
			return "edit";

		pizzaRepo.save(formPizza);

		return "redirect:/";
	}

	@PostMapping("/delete/{id}")
	public String delete(@PathVariable("id") Integer id) {

		pizzaRepo.deleteById(id);

		return "redirect:/";
	}
}