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
