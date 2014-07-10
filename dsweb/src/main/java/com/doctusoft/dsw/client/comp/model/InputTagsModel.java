package com.doctusoft.dsw.client.comp.model;

import com.doctusoft.bean.ObservableProperty;
import com.doctusoft.bean.binding.observable.ObservableList;

public class InputTagsModel extends BaseComponentModel {
	
	@com.doctusoft.ObservableProperty
	private ObservableList<String> tagList = new ObservableList<String>();
	
	@Override
	public Iterable<ObservableProperty<?, ?>> getObservableProperties() {
		return InputTagsModel_._observableProperties;
	}

}
