package com.doctusoft.dsw.client.comp.datatable;

import com.doctusoft.dsw.client.comp.HasComponentModel;
import com.doctusoft.dsw.client.comp.model.DataTableCellModel;

public abstract class ComponentColumn<Item> extends CustomColumn<Item> {
	
	private HasComponentModel component;
	
	public ComponentColumn() {
		super("");
	}

	public ComponentColumn(String title) {
		super(title);
	}
	
	@Override
	public DataTableCellModel getCellModel(Item item) {
		DataTableCellModel cellModel = new DataTableCellModel();
		cellModel.setComponent(getComponent(item).getComponentModel());
		return cellModel;
	}

	public abstract HasComponentModel getComponent(Item item);
	

}
