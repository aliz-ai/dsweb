package com.doctusoft.dsw.sample.server.person;

import lombok.Getter;
import lombok.Setter;

import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;

@Entity @Getter @Setter
public class Person {
	
	@Id
	private Long id;
	
	private String name;
	
	private String email;

	
}
