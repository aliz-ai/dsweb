package com.doctusoft.dsw.client.comp;

import com.doctusoft.dsw.client.gwt.BootstrapStyleClasses;

public abstract class FluidRowRepeat<T> extends Repeat<T> {
	
	public FluidRowRepeat() {
		this.model.getStyleClasses().add(BootstrapStyleClasses.ROW_FLUID);
	}
	
	@Override
	protected abstract Cell renderItem(T item, int rowNum);
	
}
