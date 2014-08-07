package com.doctusoft.dsw.client.comp.model;

import com.doctusoft.ObservableProperty;

public class ChartItemClickedEvent extends AbstractParametricEvent<ChartItemClickParam> {

	@ObservableProperty
	private ChartItemClickParam eventParameter;

	@Override
	public Iterable<com.doctusoft.bean.ObservableProperty<?, ?>> getObservableProperties() {
		return ChartItemClickedEvent_._observableProperties;
	}

}
