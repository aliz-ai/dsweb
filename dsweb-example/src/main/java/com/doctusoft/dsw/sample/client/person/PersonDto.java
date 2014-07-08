package com.doctusoft.dsw.sample.client.person;

import java.io.Serializable;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import com.doctusoft.ObservableProperty;
import com.google.common.collect.Lists;

@NoArgsConstructor
@AllArgsConstructor
public class PersonDto implements Serializable {
	
	@ObservableProperty
	private Long id;
	
	@ObservableProperty
	private String name;
	
	@ObservableProperty
	private String email;
	
	@ObservableProperty
	private List<String> someList;

}
