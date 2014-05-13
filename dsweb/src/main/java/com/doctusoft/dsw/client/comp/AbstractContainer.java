package com.doctusoft.dsw.client.comp;

import com.doctusoft.dsw.client.comp.model.ContainerModel;

public abstract class AbstractContainer<Actual> extends BaseComponent<Actual, ContainerModel> implements IsContainer {
	
	public AbstractContainer() {
		super(new ContainerModel());
	}

	@Override
	public void add(HasComponentModel component) {
		model.getChildren().add(component.getComponentModel());
	}

}
