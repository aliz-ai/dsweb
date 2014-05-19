package com.doctusoft.dsw.client.comp.model;

import com.doctusoft.ObservableProperty;
import com.doctusoft.bean.ListenerRegistration;
import com.doctusoft.bean.ValueChangeListener;
import com.doctusoft.bean.binding.EmptyEventHandler;

/**
 * This is a temporal 'hack' class to make parameterless events go through the observation based synchronization layer.
 * TODO make a part this into ds-bean and make it through the binder interfaces
 */
public class ComponentEvent {
	
	@ObservableProperty
	private boolean fired = false;
	
	public void fire() {
		setFired(true);
		setFired(false);
	}
	
	public ListenerRegistration add(final EmptyEventHandler handler) {
		return ComponentEvent_._fired.addChangeListener(this, new ValueChangeListener<Boolean>() {
			@Override
			public void valueChanged(Boolean newValue) {
				if (newValue == true) {
					handler.handle();
				}
			}
		});
	}
	
}
