package com.doctusoft.dsw.client.comp;

public class Navs extends BaseContainer {
	
	public Navs() {
		model.setElementType("ul");
		addStyleClass("nav nav-tabs");
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
	
	public Navs addDropdownItem(ButtonDropdown dropdownItem) {
		dropdownItem.getModel().setElementType("li");
		dropdownItem.removeStyleClass("btn");
		add(dropdownItem);
		return this;
	}
	
}
