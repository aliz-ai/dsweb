package com.doctusoft.dsw.client.comp.model.event;

import com.doctusoft.ObservableProperty;
import com.doctusoft.bean.ModelObject;
import com.doctusoft.dsw.client.comp.model.ComponentEvent;

public class KeyPressedEvent extends ComponentEvent implements ParametricEvent<KeyEvent>, ModelObject {
	
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
	
}
