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


import com.doctusoft.bean.Property;
import com.doctusoft.bean.binding.Converter;
import com.doctusoft.dsw.client.comp.model.DataTableCellModel;
import com.doctusoft.dsw.client.comp.model.DataTableColumnModel;

public class PropertyColumn<Item, Value> implements Column<Item> {
	
	private DataTableColumnModel columnModel;
	private Property<Item, Value> property;
	private Converter<Value, String> converter = null;

	/**
	 * You are encouraged to use {@link Columns#from(String, Property)} instead 
	 */
	public PropertyColumn(String title, Property<Item, Value> property) {
		this.property = property;
		columnModel = new DataTableColumnModel();
		columnModel.setTitle(title);
	}
	
	public DataTableColumnModel getHeader() {
		return columnModel; 
	}
	
	public PropertyColumn<Item, Value> format(Converter<Value, String> converter) {
		this.converter = converter;
		return this;
	}
	
	@Override
	public DataTableCellModel getCellModel(Item item) {
		DataTableCellModel cellModel = new DataTableCellModel();
		cellModel.setTextContent(getStringContent(item));
		return cellModel;
	}
	
	public String getStringContent(Item item) {
		if (item == null)
			return "";
		Value value = property.getValue(item);
		if (converter == null) {
			// use the default toString converting
			if (value == null)
				return "";
			return value.toString();
		} else
			return converter.convertSource(value);
	}

}
