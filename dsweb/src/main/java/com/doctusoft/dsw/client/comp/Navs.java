package com.doctusoft.dsw.client.comp;

import lombok.Getter;

public class Navs extends BaseContainer {
	
	public Navs() {
		model.setElementType("ul");
		addStyleClass("nav");
		addStyleClass("nav-tabs");
	}
	
	public Navs(boolean stacked) {
		this();
		addStyleClass("nav-stacked");
	}
	
	public Navs addMenuItem(Link menuItem) {
		BaseContainer linkContainer = new BaseContainer("li");
		linkContainer.add(menuItem);
		add(linkContainer);
		if (this.getModel().getChildren().size() == 1) {
			linkContainer.addStyleClass("active");
		}
		return this;
	}
	
	public Navs addDropdownItem(DropdownLink dropdownItem) {
		dropdownItem.getModel().setElementType("li");
		add(dropdownItem);
		return this;
	}
	
	public Navs setType(NavsItemType type) {
		removeStyleClass("nav-tabs");
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
