package com.doctusoft.dsw.client.comp;

import com.doctusoft.dsw.client.gwt.BootstrapStyleClasses;

public abstract class ContainerRepeat<T> extends Repeat<T> {
	
	public ContainerRepeat() {
		addStyleClass(BootstrapStyleClasses.CONTAINER);
	}
	
}
