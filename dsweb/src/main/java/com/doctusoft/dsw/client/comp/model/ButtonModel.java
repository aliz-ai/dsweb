package com.doctusoft.dsw.client.comp.model;

import com.doctusoft.bean.ObservableProperty;

public class ButtonModel extends BaseComponentModel {
	
	@com.doctusoft.ObservableProperty
	private String caption;

	/**
	 * temporal hack until the events get properly supported on models
	 */
	@com.doctusoft.ObservableProperty
	private boolean clicked;

	@Override
	public Iterable<ObservableProperty<?, ?>> getObservableProperties() {
		return ButtonModel_._observableProperties;
	}
}
