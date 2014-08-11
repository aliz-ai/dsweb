
package com.doctusoft.dsw.client.comp.model;

import com.doctusoft.ObservableProperty;
import com.doctusoft.bean.ModelObject;
import com.doctusoft.bean.binding.observable.ObservableList;

public class DataTableModel extends BaseComponentModel implements ModelObject {
	
	@ObservableProperty
	private ObservableList<DataTableColumnModel> columns = new ObservableList<DataTableColumnModel>();
	
	@ObservableProperty
	private ObservableList<DataTableRowModel> rows = new ObservableList<DataTableRowModel>();
	
	@ObservableProperty
	private SelectionMode selectionMode = SelectionMode.None;
	
	@ObservableProperty
	private ObservableList<Integer> selectedIndices = new ObservableList<Integer>();
	
	@ObservableProperty
	private RowClickedEvent rowClickedEvent = new RowClickedEvent();
	
}
