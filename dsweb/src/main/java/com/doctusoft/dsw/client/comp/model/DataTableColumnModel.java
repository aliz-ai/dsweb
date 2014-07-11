package com.doctusoft.dsw.client.comp.model;

import java.io.Serializable;

import com.doctusoft.ObservableProperty;
import com.doctusoft.bean.ModelObject;

public class DataTableColumnModel implements ModelObject, Serializable {
	
	@ObservableProperty
	private String title;

	@Override
	public Iterable<com.doctusoft.bean.ObservableProperty<?, ?>> getObservableProperties() {
		return DataTableColumnModel_._observableProperties;
	}

}
