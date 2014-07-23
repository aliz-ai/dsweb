package com.doctusoft.dsw.client.comp.model.event;

import com.doctusoft.ObservableProperty;
import com.doctusoft.dsw.client.comp.model.ComponentEvent;

public class KeyPressedEvent extends ComponentEvent implements ParametricEvent<KeyEvent> {
	
	@ObservableProperty
	private KeyEvent keyEvent;
	
	@Override
	public KeyEvent getEventParameter() {
		return keyEvent;
	}

	public void fire(int code) {
		setKeyEvent(new KeyEvent());
		keyEvent.setCode(code);
		fire();
	}
	
	@Override
	public Iterable<com.doctusoft.bean.ObservableProperty<?, ?>> getObservableProperties() {
		return KeyPressedEvent_._observableProperties;
	}
}
