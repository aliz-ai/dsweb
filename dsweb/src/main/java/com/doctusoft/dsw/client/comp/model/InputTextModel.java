package com.doctusoft.dsw.client.comp.model;

import lombok.Getter;
import lombok.Setter;

import com.doctusoft.bean.ObservableProperty;

public class InputTextModel extends BaseComponentModel {

	@com.doctusoft.ObservableProperty
	private String value = "";
	
	@Getter @Setter
	private String inputType = "text";

	@Override
	public Iterable<ObservableProperty<?, ?>> getObservableProperties() {
		return InputTextModel_._observableProperties;
	}

}
