package br.com.mylist.todoservice.dao.entities;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Data;

@Entity
@Data
public class Card {

	@Id
	private Long id;
	private String cachedName;
}
