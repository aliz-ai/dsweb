package com.doctusoft.dsw.client.comp.datatable;

/*
 * #%L
 * dsweb
 * %%
 * Copyright (C) 2014 Doctusoft Ltd.
 * %%
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public
 * License along with this program.  If not, see
 * <http://www.gnu.org/licenses/gpl-3.0.html>.
 * #L%
 */


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
	
}
