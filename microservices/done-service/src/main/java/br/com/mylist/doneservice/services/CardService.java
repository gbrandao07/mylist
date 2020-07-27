package br.com.mylist.doneservice.services;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.mylist.doneservice.dao.entities.Card;
import br.com.mylist.doneservice.dao.repositories.CardRepository;
import br.com.mylist.doneservice.services.external.CardServiceFacade;

@Service
public class CardService {

	@Autowired
	private CardRepository repository;
	
	@Autowired
	private CardServiceFacade cardServiceFacade;

	public List<Card> getAllCardsInDoneColumn() {
		return repository.findAll();
	}

	public void create(Card card) {
		repository.save(card);
	}

	public Card getCardByIdInDoneColumn(Long id) {
		return repository.findById(id)
						 .orElseGet(() -> new Card());
	}
	
	public Long updateInCardServiceApi(Map<String, String> params) {
		return cardServiceFacade.updateCard(params);
	}
}
