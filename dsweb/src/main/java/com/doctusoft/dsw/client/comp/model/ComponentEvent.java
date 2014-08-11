package com.doctusoft.dsw.client.comp.model;

import java.io.Serializable;

import com.doctusoft.ObservableProperty;
import com.doctusoft.bean.ModelObject;

/**
 * This is a temporal 'hack' class to make parameterless events go through the observation based synchronization layer.
 * TODO make a part this into ds-bean and make it through the binder interfaces
 */
public class ComponentEvent implements ModelObject, Serializable {
	
	@ObservableProperty
	private boolean fired = false;
	
	/**
	 * If the event has no listeners on the UI side, the renderer won't attach the JQuery event listener.
	 * This is essential for performance, since for example all components have a Clicked event. Without this attribute
	 * an innumerable events would get communicated in vain, just consuming resources.
	 */
	@ObservableProperty
	private boolean hasListeners = false;
	
	public void fire() {
		if (hasListeners) {
			setFired(true);
			setFired(false);
		}
	}
	
}
