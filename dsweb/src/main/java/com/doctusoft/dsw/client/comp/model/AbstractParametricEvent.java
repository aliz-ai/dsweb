package com.doctusoft.dsw.client.comp.model;

import com.doctusoft.dsw.client.comp.model.event.ParametricEvent;


public abstract class AbstractParametricEvent<T> extends ComponentEvent implements ParametricEvent<T> {
	
	public abstract T getEventParameter();
	
	public abstract void setEventParameter(T eventParameter);
	
	public void fire(T eventParameter) {
		setEventParameter( eventParameter );
		fire();
	}
	
}
