package com.doctusoft.dsw.client.comp.model;

import com.doctusoft.bean.ObservableProperty;

public class LabelModel extends BaseComponentModel {

	@com.doctusoft.ObservableProperty
	private String label;
	
	@com.doctusoft.ObservableProperty
	private String elementName = "span";
	
	public LabelModel() {
	}
	
	public LabelModel(String elementName) {
		this.elementName = elementName;
	}
	
	@Override
	public Iterable<ObservableProperty<?, ?>> getObservableProperties() {
		return LabelModel_._observableProperties;
	}

}
