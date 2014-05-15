package com.doctusoft.dsw.client.comp;

import com.doctusoft.dsw.client.comp.model.ContainerModel;
import com.doctusoft.dsw.client.gwt.BootstrapStyleClasses;

public class FluidContainer extends AbstractContainer<FluidContainer, ContainerModel> {
	
	public FluidContainer() {
		super(new ContainerModel());
		this.model.getStyleClasses().add(BootstrapStyleClasses.CONTAINER_FLUID);
	}
	
}
