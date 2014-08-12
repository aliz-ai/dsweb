package com.doctusoft.dsw.client.comp;

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


import lombok.Getter;

public class Navs extends BaseContainer {
	
	NavsItemType itemType = null;
	
	public Navs() {
		model.setElementType("ul");
		addStyleClass("nav");
		withItemType(NavsItemType.Tabs);
	}
	
	public Navs stacked() {
		addStyleClass("nav-stacked");
		return this;
	}
	
	public Navs addMenuItem(Link menuItem) {
		BaseContainer linkContainer = new BaseContainer("li");
		linkContainer.add(menuItem);
		add(linkContainer);
		return this;
	}
	
	public Navs addDropdownItem(DropdownLink dropdownItem) {
		dropdownItem.getModel().setElementType("li");
		add(dropdownItem);
		return this;
	}
	
	public Navs withItemType(NavsItemType type) {
		if (itemType != null) {
			removeStyleClass(itemType.getName());
		}
		itemType = type;
		addStyleClass(type.getName());
		return this;
	}
	
	public static enum NavsItemType {
		
		Tabs("nav-tabs"),
		Pills("nav-pills");
		
		@Getter
		private String name;
		
		NavsItemType(String name) {
			this.name = name;
		}
	}
	
}
