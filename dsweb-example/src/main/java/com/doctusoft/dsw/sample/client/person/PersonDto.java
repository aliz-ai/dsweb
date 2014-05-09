package com.doctusoft.dsw.sample.client.person;

import java.io.Serializable;

import lombok.Getter;

import com.doctusoft.ObservableProperty;

@Getter
public class PersonDto implements Serializable {
	
	@ObservableProperty
	private Long id;
	
	@ObservableProperty
	private String name;
	
	@ObservableProperty
	private String email;

}
