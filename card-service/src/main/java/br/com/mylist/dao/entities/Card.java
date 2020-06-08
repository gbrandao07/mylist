package br.com.mylist.dao.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import lombok.Data;

@Entity
@Data
public class Card {

	@Id
	@GeneratedValue
	private Long id;
	private String name;
	private String description;
	private String authorName;
}
