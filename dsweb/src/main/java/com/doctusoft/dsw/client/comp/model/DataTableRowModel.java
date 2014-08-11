package com.doctusoft.dsw.client.comp.model;

import java.io.Serializable;

import com.doctusoft.ObservableProperty;
import com.doctusoft.bean.ModelObject;
import com.doctusoft.bean.binding.observable.ObservableList;

public class DataTableRowModel implements ModelObject, Serializable {
	
	@ObservableProperty
	private ObservableList<DataTableCellModel> cells = new ObservableList<DataTableCellModel>();
	
}
