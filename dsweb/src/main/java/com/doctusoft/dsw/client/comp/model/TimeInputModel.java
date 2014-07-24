package com.doctusoft.dsw.client.comp.model;

import com.doctusoft.bean.ObservableProperty;

public class TimeInputModel extends BaseComponentModel {
	
	@com.doctusoft.ObservableProperty
	private String value = "";
	
	@Override
	public Iterable<ObservableProperty<?, ?>> getObservableProperties() {
		return TimeInputModel_._observableProperties;
	}

}
