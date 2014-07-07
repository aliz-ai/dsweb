package com.doctusoft.dsw.client.comp.datatable;

import com.doctusoft.dsw.client.comp.model.DataTableColumnModel;

public interface Column<Item> {
	
	public DataTableColumnModel getHeader();
	
	/**
	 * Return the string to write in a given cell. If it's not a single textual cell, return null 
	 */
	public String getStringContent(Item item);

}
