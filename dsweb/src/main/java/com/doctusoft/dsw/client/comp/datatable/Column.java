package com.doctusoft.dsw.client.comp.datatable;

import java.io.Serializable;

import com.doctusoft.dsw.client.comp.HasComponentModel;
import com.doctusoft.dsw.client.comp.model.DataTableColumnModel;

public interface Column<Item> extends Serializable {
	
	public DataTableColumnModel getHeader();
	
	/**
	 * Return the string to write in a given cell. If it's not a single textual cell, return null 
	 */
	public String getStringContent(Item item);
	
	/**
	 * Return the component to be rendered for this cell. If it's a single textual cell, return null 
	 */
	public HasComponentModel getComponent(Item item);

}
