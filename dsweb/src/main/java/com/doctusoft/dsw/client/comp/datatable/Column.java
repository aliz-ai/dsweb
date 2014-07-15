package com.doctusoft.dsw.client.comp.datatable;

import java.io.Serializable;

import com.doctusoft.dsw.client.comp.model.DataTableCellModel;
import com.doctusoft.dsw.client.comp.model.DataTableColumnModel;

public interface Column<Item> extends Serializable {
	
	public DataTableColumnModel getHeader();
	
	public DataTableCellModel getCellModel(Item item);
	

}
