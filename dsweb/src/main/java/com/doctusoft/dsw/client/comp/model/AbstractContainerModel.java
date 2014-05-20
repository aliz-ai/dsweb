package com.doctusoft.dsw.client.comp.model;

import lombok.Getter;
import lombok.Setter;

import com.doctusoft.bean.ObservableProperty;
import com.doctusoft.bean.binding.observable.ObservableList;

public abstract class AbstractContainerModel<Model extends BaseComponentModel> extends BaseComponentModel {
	
	@com.doctusoft.ObservableProperty
	private ObservableList<Model> children = new ObservableList<Model>();
	
	@Getter @Setter
	private String elementType = "div";
	
	@Override
	public Iterable<ObservableProperty<?, ?>> getObservableProperties() {
		return AbstractContainerModel_._observableProperties;
	}
	
}
