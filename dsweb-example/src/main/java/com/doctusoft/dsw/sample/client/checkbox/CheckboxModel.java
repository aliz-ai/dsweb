package com.doctusoft.dsw.sample.client.checkbox;

import com.doctusoft.ObservableProperty;
import com.doctusoft.dsw.client.comp.model.BaseComponentModel;

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
