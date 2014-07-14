package com.doctusoft.dsw.client.comp.model;

import com.doctusoft.ObservableProperty;
import com.doctusoft.bean.binding.observable.ObservableList;

public class DataTableModel extends BaseComponentModel {

	@ObservableProperty
	private ObservableList<DataTableColumnModel> columns = new ObservableList<DataTableColumnModel>();
	
	@ObservableProperty
	private ObservableList<DataTableRowModel> rows = new ObservableList<DataTableRowModel>();
	
	@ObservableProperty
	private SelectionMode selectionMode = SelectionMode.None;
	
	@ObservableProperty
	private ObservableList<Integer> selectedIndices = new ObservableList<Integer>();
	
	@Override
	public Iterable<com.doctusoft.bean.ObservableProperty<?, ?>> getObservableProperties() {
		return DataTableModel_._observableProperties;
	}

}
