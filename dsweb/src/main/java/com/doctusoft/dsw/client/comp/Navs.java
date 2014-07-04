package com.doctusoft.dsw.client.comp;

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
