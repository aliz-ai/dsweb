package com.doctusoft.dsw.client.comp.model;

import com.doctusoft.ObservableProperty;
import com.doctusoft.dsw.client.comp.model.BaseComponentModel;

public class HtmlContentModel extends BaseComponentModel {

	@ObservableProperty
	private String htmlContent;

	@Override
	public Iterable<com.doctusoft.bean.ObservableProperty<?, ?>> getObservableProperties() {
		return HtmlContentModel_._observableProperties;
	}

}
