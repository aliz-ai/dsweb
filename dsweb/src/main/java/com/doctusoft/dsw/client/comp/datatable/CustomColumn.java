package com.doctusoft.dsw.client.comp.datatable;

import com.doctusoft.dsw.client.comp.model.DataTableColumnModel;

/**
 * Extend this class and implement the getComponent method to return any kind of cell content you need.  
 */
public abstract class CustomColumn<Item> implements Column<Item> {
	
	private DataTableColumnModel columnModel;

	public CustomColumn(String title) {
		columnModel = new DataTableColumnModel();
		columnModel.setTitle(title);
	}
	
	@Override
	public DataTableColumnModel getHeader() {
		return columnModel;
	}
	
	@Override
	public String getStringContent(Item item) {
		return null;
	}
	
	

}
