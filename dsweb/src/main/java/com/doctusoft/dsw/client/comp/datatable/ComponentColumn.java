package com.doctusoft.dsw.client.comp.datatable;

import com.doctusoft.dsw.client.comp.HasComponentModel;

public abstract class ComponentColumn<Item> extends CustomColumn<Item> {
	
	private HasComponentModel component;
	
	public ComponentColumn(String title) {
		super(title);
	}
	

}
