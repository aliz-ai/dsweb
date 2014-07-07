package com.doctusoft.dsw.client.comp;

public class Navbar extends BaseContainer {
	
	private BaseContainer menuItemContainer;

	public Navbar(String title) {
		this(title, false);
	}
	
	protected Navbar(String title, boolean includeContainer) {
		addStyleClass("navbar");
		
		AbstractContainer innerContainer = new BaseContainer();
		innerContainer.addStyleClass("navbar-inner");
		add(innerContainer);
		
		if (includeContainer) {
			innerContainer = new Container().appendTo(innerContainer);
		}
		
		menuItemContainer = new BaseContainer("ul");
		menuItemContainer.addStyleClass("nav");
		
		Link titleItem = new Link(title, "#");
		titleItem.addStyleClass("brand");
		innerContainer.add(titleItem);
		innerContainer.add(menuItemContainer);
		
	}
	
	public Navbar addMenuItem(Link menuItem) {
		BaseContainer linkContainer = new BaseContainer("li");
		linkContainer.add(menuItem);
		menuItemContainer.add(linkContainer);
		return this;
	}
	
	public Navbar addDropdownItem(DropdownLink dropdownItem) {
		dropdownItem.getModel().setElementType("li");
		menuItemContainer.add(dropdownItem);
		return this;
	}
	
	

}
