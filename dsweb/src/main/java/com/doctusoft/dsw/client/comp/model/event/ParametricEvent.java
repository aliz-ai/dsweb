package com.doctusoft.dsw.client.comp.model.event;

import java.io.Serializable;

public interface ParametricEvent<T> extends Serializable {
	
	public T getEventParameter();

}
