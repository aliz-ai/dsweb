package com.doctusoft.dsw.client.comp.model;

import lombok.Getter;
import lombok.Setter;

import com.doctusoft.ObservableProperty;

public class TextareaModel extends BaseComponentModel {
	
	@Getter @Setter
	private int rows = 1;
	
	@ObservableProperty
	private String value;
	
	@Override
	public Iterable<com.doctusoft.bean.ObservableProperty<?, ?>> getObservableProperties() {
		return TextareaModel_._observableProperties;
	}

}
