package br.com.mylist.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.mylist.dao.entities.Card;
import br.com.mylist.dao.repositories.CardRepository;

@Service
public class CardService {

	@Autowired
	private CardRepository repository;

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
}
