package com.doctusoft.dsw.client.comp.model.event;

import java.io.Serializable;

import com.doctusoft.bean.ModelObject;
import com.doctusoft.bean.ObservableProperty;

public class KeyEvent implements ModelObject, Serializable {
	
	@com.doctusoft.ObservableProperty
	private Integer code; 
	
	@Override
	public Iterable<ObservableProperty<?, ?>> getObservableProperties() {
		return KeyEvent_._observableProperties;
	}

}
