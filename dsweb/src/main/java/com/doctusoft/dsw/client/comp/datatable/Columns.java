package com.doctusoft.dsw.client.comp.datatable;

import com.doctusoft.bean.Property;

public class Columns {
	
	public static <Item, Value> PropertyColumn<Item, Value> from(String title, Property<Item, Value> property) {
		return new PropertyColumn<Item, Value>(title, property);
	}
	
}
