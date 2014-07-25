package com.doctusoft.dsw.client.comp.model;

import com.doctusoft.bean.ObservableProperty;
import com.doctusoft.dsw.client.comp.model.event.ParametricEvent;


public abstract class AbstractParametricEvent<T> extends ComponentEvent implements ParametricEvent<T> {
	
	@com.doctusoft.ObservableProperty
	private T eventParameter;
	
	public void fire(T eventParameter) {
		setEventParameter( eventParameter );
		fire();
	}
	
	@Override
	public T getEventParameter() {
		return eventParameter;
	}
	
	@Override
	public Iterable<ObservableProperty<?, ?>> getObservableProperties() {
		return AbstractParametricEvent_._observableProperties;
	}
	
}
