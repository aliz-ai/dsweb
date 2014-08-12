package com.doctusoft.dsw.sample.client;

/*
 * #%L
 * dsweb-example
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


import com.doctusoft.dsw.client.comp.HasComponentModel;
import com.doctusoft.dsw.client.comp.model.ContainerModel;
import com.google.common.base.Preconditions;
import com.google.gwt.place.shared.Place;

public abstract class AbstractMVPApplication {
	
	/**
	 * This is the default root container where the current Activity inserts its view, if this behaviour is not overriden
	 * by createFrameWidgets and showView
	 */
	private ContainerModel viewTarget = null;

	public abstract Place getDefaultPlace();
	
	/**
	 * Override this method (without calling super) if you want to create components that are not derived from the current Activity 
	 */
	public HasComponentModel createFrameWidgets() {
		if (viewTarget == null) {
			viewTarget = new ContainerModel();
		}
		return viewTarget;
	}
	
	public void showView(HasComponentModel view) {
		Preconditions.checkNotNull(viewTarget, "The default viewTarget element is not set. Make sure to properly override AbstractMVPApplication.showView if you use a custom appication frame");
		viewTarget.getChildren().clear();
		if (view != null) {
			viewTarget.getChildren().add(view.getComponentModel());
		}
	}

}
