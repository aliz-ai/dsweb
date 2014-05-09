package com.doctusoft.dsw.client.comp;

import lombok.Getter;

import com.doctusoft.dsw.client.comp.model.ContainerModel;

@Getter
public class Container extends BaseComponent<Container, ContainerModel> implements IsContainer {
	
	public Container() {
		super(new ContainerModel());
	}
	
	@Override
	public void add(HasComponentModel component) {
		model.getChildren().add(component.getComponentModel());
	}
}
