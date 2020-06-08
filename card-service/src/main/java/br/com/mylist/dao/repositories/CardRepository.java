package br.com.mylist.dao.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.mylist.dao.entities.Card;

@Repository
public interface CardRepository extends JpaRepository<Card, Long> {

}
