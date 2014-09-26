package com.doctusoft.dsw.client;

import lombok.Getter;

import com.doctusoft.dsw.client.comp.model.event.KeyEvent;
import com.doctusoft.dsw.client.comp.model.event.ParametricEventHandler;

public class KeyEventHandlerMock implements ParametricEventHandler<KeyEvent> {
	
	@Getter
	private int receivedKeyCode;
	
	@Override
	public void handle(KeyEvent parameter) {
		receivedKeyCode = parameter.getCode();
	}

}
