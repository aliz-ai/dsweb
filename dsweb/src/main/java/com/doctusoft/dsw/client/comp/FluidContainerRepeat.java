package com.doctusoft.dsw.client.comp;

import com.doctusoft.dsw.client.gwt.BootstrapStyleClasses;

public abstract class FluidContainerRepeat<T> extends Repeat<T> {
	
	public FluidContainerRepeat() {
		addStyleClass(BootstrapStyleClasses.CONTAINER_FLUID);
	}
	
}
