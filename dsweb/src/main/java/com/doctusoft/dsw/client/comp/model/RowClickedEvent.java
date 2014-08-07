package com.doctusoft.dsw.client.comp.model;

import com.doctusoft.ObservableProperty;

public class RowClickedEvent extends AbstractParametricEvent<Integer> {

	@ObservableProperty
	private Integer eventParameter;

	@Override
	public Iterable<com.doctusoft.bean.ObservableProperty<?, ?>> getObservableProperties() {
		return RowClickedEvent_._observableProperties;
	}

}
