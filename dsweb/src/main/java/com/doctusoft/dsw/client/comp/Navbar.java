package com.doctusoft.dsw.client.comp;

public class Navbar extends BaseContainer {
	
	private BaseContainer menuItemContainer;
	
	public Navbar(String title) {
		addStyleClass("navbar");
		
		BaseContainer innerContainer = new BaseContainer();
		innerContainer.addStyleClass("navbar-inner");
		
		menuItemContainer = new BaseContainer("ul");
		menuItemContainer.addStyleClass("nav");
		
		Link titleItem = new Link(title, "#");
		titleItem.addStyleClass("brand");
		innerContainer.add(titleItem);
		innerContainer.add(menuItemContainer);
		
		add(innerContainer);
		
	}
	
	public Navbar addMenuItem(Link menuItem) {
		BaseContainer linkContainer = new BaseContainer("li");
		linkContainer.add(menuItem);
		menuItemContainer.add(linkContainer);
		if (menuItemContainer.getModel().getChildren().size() == 1) {
			linkContainer.addStyleClass("active");
		}
		return this;
	}
	
	public Navbar addDropdownItem(ButtonDropdown dropdownItem) {
		dropdownItem.getModel().setElementType("li");
		dropdownItem.getDropdownButton().removeStyleClass("btn");
		menuItemContainer.add(dropdownItem);
		return this;
	}
	
	

}
