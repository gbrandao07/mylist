package br.com.mylist.doingservice.services;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import br.com.mylist.doingservice.dao.entities.Card;
import br.com.mylist.doingservice.dao.repositories.CardRepository;
import br.com.mylist.doingservice.services.external.CardServiceFacade;
import br.com.mylist.doingservice.services.external.DoneServiceFacade;
import br.com.mylist.doingservice.services.external.TodoServiceFacade;

@Service
public class CardService {

	@Autowired
	private CardRepository repository;

	@Autowired 
	private TodoServiceFacade tokenServiceFacade;
	
	@Autowired 
	private DoneServiceFacade doneServiceFacade;
	
	@Autowired
	private CardServiceFacade cardServiceFacade;
	
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
	
	public Long updateInCardServiceApi(Map<String, String> params) {
		return cardServiceFacade.updateCard(params);
	}
}
