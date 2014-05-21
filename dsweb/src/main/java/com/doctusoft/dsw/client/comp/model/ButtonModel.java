package com.doctusoft.dsw.client.comp.model;

import com.doctusoft.bean.ObservableProperty;

public class ButtonModel extends BaseComponentModel {
	
	@com.doctusoft.ObservableProperty
	private String caption;

	@Override
	public Iterable<ObservableProperty<?, ?>> getObservableProperties() {
		return ButtonModel_._observableProperties;
	}
}
