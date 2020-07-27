package br.com.mylist.cardservice.controllers;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.mylist.cardservice.dao.entities.Card;
import br.com.mylist.cardservice.services.CardService;

@RestController
@RequestMapping("/cards")
public class CardServiceController {
	
	@Autowired
	private CardService cardService;

	@PostMapping
	public Long create(@RequestParam Map<String,String> params) {
		Card card = new Card();
		card.setName(params.get("name"));
		card.setAuthorName(params.get("authorName"));
		card.setDescription(params.get("description"));
		cardService.create(card);
		return card.getId(); 
	}

	@PutMapping
	public Long update(@RequestParam Map<String,String> params) {
		Card card = new Card();
		card.setId(Long.valueOf(params.get("id")));
		card.setName(params.get("name"));
		card.setAuthorName(params.get("authorName"));
		card.setDescription(params.get("description"));
		cardService.create(card);
		return card.getId(); 
	}
	
	@GetMapping("/{id}")
	public Card getCardById(@PathVariable("id") Long id) {
		return cardService.getCardById(id);
	}
}
