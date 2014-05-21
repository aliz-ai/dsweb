package com.doctusoft.dsw.client.comp;

public class DropdownButton extends DropdownLink {
	
	public DropdownButton(String buttonLabel) {
		super(buttonLabel);
		dropdownButton.addStyleClass("btn");
	}
	
	@Override
	public DropdownButton addLink(Link link) {
		BaseContainer linkContainer = new BaseContainer("li");
		linkContainer.add(link);
		itemContainer.add(linkContainer);
		return this;
	}
	
}
