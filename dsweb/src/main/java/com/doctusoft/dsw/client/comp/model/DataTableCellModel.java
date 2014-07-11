package com.doctusoft.dsw.client.comp.model;

import java.io.Serializable;

import com.doctusoft.ObservableProperty;
import com.doctusoft.bean.ModelObject;

public class DataTableCellModel implements ModelObject, Serializable {
	
	@ObservableProperty
	private String textContent;
	
	@Override
	public Iterable<com.doctusoft.bean.ObservableProperty<?, ?>> getObservableProperties() {
		return DataTableCellModel_._observableProperties;
	}

}
