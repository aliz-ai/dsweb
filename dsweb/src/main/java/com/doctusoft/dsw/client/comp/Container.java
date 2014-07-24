package com.doctusoft.dsw.client.comp;

import lombok.Getter;

import com.doctusoft.dsw.client.comp.model.ContainerModel;
import com.doctusoft.dsw.client.gwt.BootstrapStyleClasses;

@Getter
public class Container extends AbstractContainer<Container, ContainerModel> {
	
	public Container() {
		super(new ContainerModel());
		addStyleClass(BootstrapStyleClasses.CONTAINER);
	}
	
}
