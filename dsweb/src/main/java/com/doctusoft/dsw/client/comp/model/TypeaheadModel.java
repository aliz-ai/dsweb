package com.doctusoft.dsw.client.comp.model;

import com.doctusoft.bean.ObservableProperty;

public class TypeaheadModel extends SelectModel {
	
	@com.doctusoft.ObservableProperty
	private boolean allVisibleOnFocus = false;

	@Override
	public Iterable<ObservableProperty<?, ?>> getObservableProperties() {
		return TypeaheadModel_._observableProperties;
	}
	
}
