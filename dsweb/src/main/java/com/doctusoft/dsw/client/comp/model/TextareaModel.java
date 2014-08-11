package com.doctusoft.dsw.client.comp.model;

import lombok.Getter;
import lombok.Setter;

import com.doctusoft.ObservableProperty;
import com.doctusoft.bean.ModelObject;

public class TextareaModel extends BaseComponentModel implements ModelObject {
	
	@Getter @Setter
	private int rows = 1;
	
	@ObservableProperty
	private String value;
	
}
