package com.doctusoft.dsw.client.comp.model;

import com.doctusoft.bean.ObservableProperty;

public class InputTimeModel extends BaseComponentModel {
	
	@com.doctusoft.ObservableProperty
	private String value = "";
	
	@Override
	public Iterable<ObservableProperty<?, ?>> getObservableProperties() {
		return InputTimeModel_._observableProperties;
	}

}
