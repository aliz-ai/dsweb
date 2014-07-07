package com.doctusoft.dsw.sample.client;

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
