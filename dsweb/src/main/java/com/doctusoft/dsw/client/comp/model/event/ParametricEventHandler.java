package com.doctusoft.dsw.client.comp.model.event;

import java.io.Serializable;

public interface ParametricEventHandler<T> extends Serializable {
	
	public void handle(T parameter);

}
