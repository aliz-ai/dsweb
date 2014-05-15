package com.doctusoft.dsw.client.comp.model;

import com.doctusoft.bean.ObservableProperty;

public class CellModel extends AbstractContainerModel<BaseComponentModel> {
	
	@com.doctusoft.ObservableProperty
	private int span = 1;
	
	@com.doctusoft.ObservableProperty
	private int offset = 0;
	
	@Override
	public Iterable<ObservableProperty<?, ?>> getObservableProperties() {
		return CellModel_._observableProperties;
	}
	
}
