package com.doctusoft.dsw.client.comp;

import com.doctusoft.dsw.client.comp.model.ContainerModel;

public class BaseContainer extends AbstractContainer<Container, ContainerModel> {

	public BaseContainer() {
		super(new ContainerModel());
	}
	
	public BaseContainer(String elementType) {
		super(new ContainerModel());
		model.setElementType(elementType);
	}
}
