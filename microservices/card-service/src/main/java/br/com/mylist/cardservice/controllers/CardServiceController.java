package br.com.mylist.cardservice.controllers;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
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
	private Long create(@RequestParam Map<String,String> params) {
		Card card = new Card();
		card.setName(params.get("name"));
		card.setAuthorName(params.get("authorName"));
		card.setDescription(params.get("description"));
		cardService.create(card);
		return card.getId(); 
	}

// TODO a ser chamado por listar todos 
//	@GetMapping("/")
//	public List<Card> getAllCards() {
//		return cardService.getAllCards();
//	}
	
	@GetMapping("/cards/{id}")
	public Card getCardById(@PathVariable("id") Long id) {
		return cardService.getCardById(id);
	}
}
