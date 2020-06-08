package br.com.mylist.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import br.com.mylist.dao.entities.Card;
import br.com.mylist.dao.repositories.CardRepository;
import br.com.mylist.services.external.DoneServiceFacade;
import br.com.mylist.services.external.TodoServiceFacade;

@Service
public class CardService {

	@Autowired
	private CardRepository repository;

	@Autowired 
	private TodoServiceFacade tokenServiceFacade;
	
	@Autowired 
	private DoneServiceFacade doneServiceFacade;
	
	public List<Card> getAllCardsInDoingColumn() {
		return repository.findAll();
	}

	public void create(Card card) {
		repository.save(card);
	}

	public Card getCardByIdInDoingColumn(Long id) {
		return repository.findById(id)
						 .orElseGet(() -> new Card());
	}

	public ResponseEntity<String> moveCardToTodoColumn(Long id) {
		Card card = repository.findById(id).orElseThrow(() -> new RuntimeException("Invalid id"));
		ResponseEntity<String> response = tokenServiceFacade.receiveCard("doing", card);
		if (HttpStatus.OK.equals(response.getStatusCode())) {
			repository.deleteById(id);
		}
		return new ResponseEntity<>("Card " + id + " moved to Todo column and removed from Doing column succesfully!", HttpStatus.OK);
	}

	public ResponseEntity<String> moveCardToDoneColumn(Long id) {
		Card card = repository.findById(id).orElseThrow(() -> new RuntimeException("Invalid id"));
		ResponseEntity<String> response = doneServiceFacade.receiveCard("doing", card);
		if (HttpStatus.OK.equals(response.getStatusCode())) {
			repository.deleteById(id);
		}
		return new ResponseEntity<>("Card " + id + " moved to Done column and removed from Doing column succesfully!", HttpStatus.OK);
	}
}
