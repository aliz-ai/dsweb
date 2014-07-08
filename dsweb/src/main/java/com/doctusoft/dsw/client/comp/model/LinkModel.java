package com.doctusoft.dsw.client.comp.model;

import com.doctusoft.ObservableProperty;

public class LinkModel extends BaseComponentModel {

	@ObservableProperty
	private String text;

	@ObservableProperty
	private String href;
	
	@ObservableProperty
	private String target;

	@Override
	public Iterable<com.doctusoft.bean.ObservableProperty<?, ?>> getObservableProperties() {
		return LinkModel_._observableProperties;
	}

}
