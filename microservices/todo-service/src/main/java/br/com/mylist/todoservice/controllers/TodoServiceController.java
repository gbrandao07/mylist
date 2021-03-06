package br.com.mylist.todoservice.controllers;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.mylist.todoservice.dao.entities.Card;
import br.com.mylist.todoservice.services.CardService;

@RestController
@RequestMapping("/todo")
public class TodoServiceController {
	
	@Autowired
	private CardService cardService;

	@PostMapping("/cards")
	public ResponseEntity<Card> createCardInTodoList(@RequestParam Map<String,String> params) {

		// first, create in card-service api
		Long createdCardId = cardService.createInCardServiceApi(params);
		
		// now, if id in hands, create the card in this column
		Card card = new Card();
		card.setId(createdCardId);
		card.setCachedName(params.get("name"));
		cardService.create(card);
		
		return new ResponseEntity<>(card , HttpStatus.CREATED);
	}
	
	@PutMapping("/cards")
	public ResponseEntity<Card> updateCardInTodoList(@RequestParam Map<String,String> params) {

		// first, update in card-service api
		Long createdCardId = cardService.updateInCardServiceApi(params);
		
		// now, if id in hands, create the card in this column
		Card card = new Card();
		card.setId(createdCardId);
		card.setCachedName(params.get("name"));
		cardService.create(card);
		
		return new ResponseEntity<>(card , HttpStatus.CREATED);
	}
	
	@GetMapping("/cards")
	public List<Card> getAllCardsInTodoColumn() {
		return cardService.getAllCardsInTodoColumn();
	}
	
	@GetMapping("/cards/{id}")
	public Card getCardByIdInTodoColumn(@PathVariable("id") Long id) {
		return cardService.getCardByIdInTodoColumn(id);
	}
	
	@PostMapping("/cards/{id}/move")
	public ResponseEntity<String> moveCardToDoingColumn(@PathVariable("id") Long id) {
		return cardService.moveCardToDoingColumn(id);
	}
	
	@PostMapping("/cards/receive")
	public ResponseEntity<String> receiveCard(@RequestHeader String from, @RequestBody Card card) {
		cardService.create(card);
		return new ResponseEntity<>("Card " + card.getId() + " received from: " + from , HttpStatus.OK);
	}
}
