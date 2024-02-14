package com.app.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Table(name = "comments")
@Entity
@Getter
@Setter
public class Comments {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;

	private String content;
	
	@ManyToOne
	private Post post;
}
