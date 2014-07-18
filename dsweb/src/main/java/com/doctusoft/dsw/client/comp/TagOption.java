package com.doctusoft.dsw.client.comp;

import com.doctusoft.ObservableProperty;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

public class TagOption {
	
	@ObservableProperty
	private String name;
	
	@ObservableProperty
	private String styleClass = "";

}
