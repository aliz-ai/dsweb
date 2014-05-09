package com.doctusoft.dsw.client.comp.model;

import com.doctusoft.bean.ObservableProperty;
import com.doctusoft.bean.binding.observable.ObservableList;

public class ContainerModel extends BaseComponentModel {
	
	@com.doctusoft.ObservableProperty
	private ObservableList<BaseComponentModel> children = new ObservableList<BaseComponentModel>();
	

	@Override
	public Iterable<ObservableProperty<?, ?>> getObservableProperties() {
		return ContainerModel_._observableProperties;
	}
	
}
