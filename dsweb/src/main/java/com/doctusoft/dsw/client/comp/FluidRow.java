package com.doctusoft.dsw.client.comp;

import com.doctusoft.dsw.client.comp.model.ContainerModel;
import com.doctusoft.dsw.client.gwt.BootstrapStyleClasses;

public class FluidRow extends AbstractContainer<FluidRow, ContainerModel> {
	
	public FluidRow() {
		super(new ContainerModel());
		this.model.getStyleClasses().add(BootstrapStyleClasses.ROW_FLUID);
	}
	
}
