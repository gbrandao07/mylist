package br.com.mylist.doingservice.dao.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.mylist.doingservice.dao.entities.Card;

@Repository
public interface CardRepository extends JpaRepository<Card, Long> {

}
