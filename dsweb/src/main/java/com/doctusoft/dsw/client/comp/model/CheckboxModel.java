package com.doctusoft.dsw.client.comp.model;

import com.doctusoft.ObservableProperty;

public class CheckboxModel extends BaseComponentModel {
	
	@ObservableProperty
	private String label;
	
	@ObservableProperty
	private Boolean checked = false;
	
	@Override
	public Iterable<com.doctusoft.bean.ObservableProperty<?, ?>> getObservableProperties() {
		return CheckboxModel_._observableProperties;
	}

}
