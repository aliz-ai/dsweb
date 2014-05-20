package com.doctusoft.dsw.client.comp;

import com.doctusoft.dsw.client.comp.model.ButtonDropdownModel;


public class ButtonDropdown extends AbstractContainer<ButtonDropdown, ButtonDropdownModel> {
	
	private Link dropdownButton;
	private BaseContainer itemContainer;
	
	public ButtonDropdown(String buttonLabel) {
		super(new ButtonDropdownModel());
		this.addStyleClass("dropdown");
		dropdownButton = new Link(buttonLabel + " ","#");
		dropdownButton.addStyleClass("dropdown-toggle");
		itemContainer = new BaseContainer("ul");
		
		itemContainer.removeStyleClass("container");
		itemContainer.addStyleClass("dropdown-menu");
		
		add(dropdownButton);
		add(itemContainer);
	}
	
	public ButtonDropdown addLink(Link link) {
		BaseContainer linkContainer = new BaseContainer("li");
		linkContainer.add(link);
		itemContainer.add(linkContainer);
		return this;
	}
}
