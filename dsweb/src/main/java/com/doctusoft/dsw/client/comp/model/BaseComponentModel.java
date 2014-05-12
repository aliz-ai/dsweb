package com.doctusoft.dsw.client.comp.model;

import com.doctusoft.bean.ModelObject;
import com.doctusoft.bean.ObservableProperty;
import com.doctusoft.bean.binding.observable.ObservableList;
import com.doctusoft.dsw.client.comp.HasComponentModel;

public class BaseComponentModel implements ModelObject, HasComponentModel {
	
	@com.doctusoft.ObservableProperty
	private boolean visible = true;
	
	@com.doctusoft.ObservableProperty
	private ObservableList<String> styleClasses = new ObservableList<String>();

	@Override
	public BaseComponentModel getComponentModel() {
		return this;
	}

	@Override
	public Iterable<ObservableProperty<?, ?>> getObservableProperties() {
		return BaseComponentModel_._observableProperties;
	}

}
