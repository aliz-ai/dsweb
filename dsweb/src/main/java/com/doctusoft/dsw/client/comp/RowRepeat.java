package com.doctusoft.dsw.client.comp;

import com.doctusoft.dsw.client.gwt.BootstrapStyleClasses;

public abstract class RowRepeat<T> extends Repeat<T> {
	
	public RowRepeat() {
		this.model.getStyleClasses().add(BootstrapStyleClasses.ROW);
	}
	
	@Override
	protected abstract Cell renderItem(T item, int rowNum);
	
}
