package com.doctusoft.dsw.sample.client.custom;

import com.doctusoft.ObservableProperty;
import com.doctusoft.dsw.client.comp.model.BaseComponentModel;

public class CustomComponentModel extends BaseComponentModel {

	@ObservableProperty
	private String label;

	@Override
	public Iterable<com.doctusoft.bean.ObservableProperty<?, ?>> getObservableProperties() {
		return CustomComponentModel_._observableProperties;
	}
}
