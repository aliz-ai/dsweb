package com.doctusoft.dsw.client.comp.datatable;

import com.doctusoft.bean.Property;
import com.doctusoft.dsw.client.comp.model.DataTableColumnModel;

public class PropertyColumn<Item> implements Column<Item> {
	
	private DataTableColumnModel columnModel;
	private Property<Item, ?> property;

	public PropertyColumn(String title, Property<Item, ?> property) {
		this.property = property;
		columnModel = new DataTableColumnModel();
		columnModel.setTitle(title);
	}
	
	public DataTableColumnModel getHeader() {
		return columnModel; 
	}
	
	@Override
	public String getStringContent(Item item) {
		if (item == null)
			return "";
		Object value = property.getValue(item);
		if (value == null)
			return "";
		return value.toString();
	}

}
