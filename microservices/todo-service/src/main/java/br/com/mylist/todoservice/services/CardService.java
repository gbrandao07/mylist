package br.com.mylist.todoservice.services;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import br.com.mylist.todoservice.dao.entities.Card;
import br.com.mylist.todoservice.dao.repositories.CardRepository;
import br.com.mylist.todoservice.services.external.CardServiceFacade;
import br.com.mylist.todoservice.services.external.DoingServiceFacade;

@Service
public class CardService {

	@Autowired
	private CardRepository repository;

	@Autowired 
	private DoingServiceFacade doingServiceFacade;
	
	@Autowired
	private CardServiceFacade cardServiceFacade; 
	
	public List<Card> getAllCardsInTodoColumn() {
		return repository.findAll();
	}
	
	public void create(Card card) {
		repository.save(card);
	}

	public Card getCardByIdInTodoColumn(Long id) {
		return repository.findById(id)
						 .orElseGet(() -> new Card());
	}

	public ResponseEntity<String> moveCardToDoingColumn(Long id) {
		Card card = repository.findById(id).orElseThrow(() -> new RuntimeException("Invalid card id"));
		ResponseEntity<String> response = doingServiceFacade.receiveCard("todo", card);
		if (HttpStatus.OK.equals(response.getStatusCode())) {
			repository.deleteById(id);
		}
		return new ResponseEntity<>("Card " + id + " moved to Doing column and removed from Todo column succesfully!", HttpStatus.OK);
	}
	
	public Long createInCardServiceApi(Map<String, String> params) {
		return cardServiceFacade.createCard(params);
	}
	
	public Long updateInCardServiceApi(Map<String, String> params) {
		return cardServiceFacade.updateCard(params);
	}
}
