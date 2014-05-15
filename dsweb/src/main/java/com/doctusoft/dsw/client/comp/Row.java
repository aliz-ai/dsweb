package com.doctusoft.dsw.client.comp;

import com.doctusoft.dsw.client.comp.model.ContainerModel;
import com.doctusoft.dsw.client.gwt.BootstrapStyleClasses;

public class Row extends AbstractContainer<Row, ContainerModel> {
	
	public Row() {
		super(new ContainerModel());
		this.model.getStyleClasses().add(BootstrapStyleClasses.ROW);
	}
	
}
