package com.doctusoft.dsw.client.comp.model;

import com.doctusoft.bean.ObservableProperty;

public class InputTagsModel extends ContainerModel {
	
	@com.doctusoft.ObservableProperty
	private String inputText = "";
	
	@Override
	public Iterable<ObservableProperty<?, ?>> getObservableProperties() {
		return InputTagsModel_._observableProperties;
	}

}
