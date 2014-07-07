package com.doctusoft.dsw.client.comp.model;

import java.util.List;

import com.doctusoft.ObservableProperty;
import com.doctusoft.bean.ModelObject;

public class DataTableRowModel implements ModelObject {
	
	@ObservableProperty
	private List<DataTableCellModel> cells;
	
	@Override
	public Iterable<com.doctusoft.bean.ObservableProperty<?, ?>> getObservableProperties() {
		return DataTableRowModel_._observableProperties;
	}

}
