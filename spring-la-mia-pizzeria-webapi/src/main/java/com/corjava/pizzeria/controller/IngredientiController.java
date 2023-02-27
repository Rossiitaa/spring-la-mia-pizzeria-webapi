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
import com.corjava.pizzeria.repository.IngredientiRepo;
import com.corjava.pizzeria.repository.PizzaRepo;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/ingredienti")
public class IngredientiController {

	@Autowired
	private PizzaRepo pizzaRepo;

	@Autowired
	private IngredientiRepo ingredientiRepo;

	@GetMapping
	public String index(@RequestParam(name = "nome", required = false) String nome, Model model) {
		List<Ingredienti> ing;
		if (nome != null && !nome.isEmpty()) {
			ing = ingredientiRepo.findByNomeLike("%" + nome + "%");
		} else {
			ing = ingredientiRepo.findAll(Sort.by("nome"));
		}
		model.addAttribute("ingrediente", ing);
		return "ingredienti";
	}

	@GetMapping("/create")
	public String create(Model model) {
		Ingredienti ingredienti = new Ingredienti();

		model.addAttribute("ingredienti", ingredienti);

		return "ingredienti-create";
	}

	@PostMapping("/create")
	public String store(@ModelAttribute("ingredienti") Ingredienti formIngrediente, Model model) {

		ingredientiRepo.save(formIngrediente);

		return "redirect:/";
	}

	@GetMapping("/edit/{id}")
	public String edit(@PathVariable("id") Integer id, Model model) {
		Ingredienti ingredienti = ingredientiRepo.getReferenceById(id);
		model.addAttribute("ingredienti", ingredienti);

		return "ingredienti-edit";
	}

	@PostMapping("/edit/{id}")
	public String update(@Valid @ModelAttribute("ingredienti") Ingredienti formIngredienti, BindingResult bindingResult,
			Model model) {

		ingredientiRepo.save(formIngredienti);

		return "redirect:/";
	}

	@PostMapping("/delete/{id}")
	public String delete(@PathVariable("id") Integer id) {
		ingredientiRepo.deleteById(id);
		return "redirect:/ingredienti";
	}
}
