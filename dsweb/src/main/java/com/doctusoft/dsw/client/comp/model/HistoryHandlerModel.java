package com.doctusoft.dsw.client.comp.model;

import com.doctusoft.ObservableProperty;

public class HistoryHandlerModel extends BaseComponentModel {

	@ObservableProperty
	private String historyToken;

	@Override
	public Iterable<com.doctusoft.bean.ObservableProperty<?, ?>> getObservableProperties() {
		return HistoryHandlerModel_._observableProperties;
	}
}
