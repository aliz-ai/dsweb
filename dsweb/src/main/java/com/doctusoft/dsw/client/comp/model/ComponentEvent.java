package com.doctusoft.dsw.client.comp.model;

/*
 * #%L
 * dsweb
 * %%
 * Copyright (C) 2014 Doctusoft Ltd.
 * %%
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public
 * License along with this program.  If not, see
 * <http://www.gnu.org/licenses/gpl-3.0.html>.
 * #L%
 */


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
