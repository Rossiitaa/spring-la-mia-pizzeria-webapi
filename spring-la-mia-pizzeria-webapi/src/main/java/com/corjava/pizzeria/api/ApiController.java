package com.corjava.pizzeria.api;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.corjava.pizzeria.model.Pizza;
import com.corjava.pizzeria.repository.PizzaRepo;

@RestController
@CrossOrigin
@RequestMapping("/api/pizze")
public class ApiController {
	@Autowired
	PizzaRepo pizzaRepo;

	@GetMapping()
	public List<Pizza> index() {
		return pizzaRepo.findAll();
	}

	@GetMapping("{id}")
	public ResponseEntity<Pizza> detail(@PathVariable("id") Integer id) {
		Optional<Pizza> res = pizzaRepo.findById(id);
		if (res.isPresent())
			return new ResponseEntity<Pizza>(res.get(), HttpStatus.OK);
		else
			return new ResponseEntity<Pizza>(HttpStatus.NOT_FOUND);
	}

	@PostMapping("/create")
	public Pizza create(@RequestBody Pizza pizza) {
		return pizzaRepo.save(pizza);
	}

	@PutMapping("{id}")
	public Pizza update(@RequestBody Pizza pizza, @PathVariable("id") Integer id) {
		pizza.setId(id);
		return pizzaRepo.save(pizza);
	}

	@DeleteMapping("{id}")
	public void delete(@PathVariable("id") Integer id) {
		pizzaRepo.deleteById(id);
	}

}
