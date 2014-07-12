package com.doctusoft.dsw.client.comp;

import com.doctusoft.bean.binding.observable.ObservableList;
import com.doctusoft.dsw.client.comp.model.SelectModel;

/**
 * TODO: support binding and {@link ObservableList} of {@link SelectItem}s 
 */
public class Select<T> extends AbstractSelect<Select<T>, SelectModel, T> {
	
	public Select() {
		super(new SelectModel());
	}
	
}
