package br.com.mylist.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.mylist.dao.entities.Card;
import br.com.mylist.services.CardService;

@RestController
@RequestMapping("/doing")
public class DoingServiceController {
		
	@Autowired
	private CardService cardService;
	
	@GetMapping("/cards")
	public List<Card> getAllCardsInDoingColumn() {
		return cardService.getAllCardsInDoingColumn();
	}
	
	@GetMapping("/cards/{id}")
	public Card getCardByIdInDoingColumn(@PathVariable("id") Long id) {
		return cardService.getCardByIdInDoingColumn(id);
	}

	@PostMapping("/cards/{id}/move")
	public ResponseEntity<String> moveCard(@PathVariable("id") Long id, @RequestParam("to") String to) {
		if ("todo".equals(to)) {
			return cardService.moveCardToTodoColumn(id);
		} else if ("done".equals(to)) {
			return cardService.moveCardToDoneColumn(id);
		}
		throw new RuntimeException("Invalid or missing from query param. Should be either 'todo' or 'doing'");
	}
	
	@PostMapping("/cards/receive")
	public ResponseEntity<String> receiveCard(@RequestHeader String from, @RequestBody Card card) {
		cardService.create(card);
		return new ResponseEntity<>("Card " + card.getId() + " received from: " + from , HttpStatus.OK);
	}
}
