package br.com.mylist.doneservice.controllers;

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

import br.com.mylist.doneservice.dao.entities.Card;
import br.com.mylist.doneservice.services.CardService;

@RestController
@RequestMapping("/done")
public class DoneServiceController {
	
	@Autowired
	private CardService cardService;
	
	@GetMapping("/cards")
	public List<Card> getAllCardsInDoneColumn() {
		return cardService.getAllCardsInDoneColumn();
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
	
	@GetMapping("/cards/{id}")
	public Card getCardByIdInDoneColumn(@PathVariable("id") Long id) {
		return cardService.getCardByIdInDoneColumn(id);
	}
	
	@PostMapping("/cards/receive")
	public ResponseEntity<String> receiveCard(@RequestHeader String from, @RequestBody Card card) {
		cardService.create(card);
		return new ResponseEntity<>("Card " + card.getId() + " received from: " + from , HttpStatus.OK);
	}
}
