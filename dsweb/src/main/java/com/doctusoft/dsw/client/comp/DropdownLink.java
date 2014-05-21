package com.doctusoft.dsw.client.comp;

import com.doctusoft.dsw.client.comp.model.DropdownButtonModel;

public class DropdownLink extends AbstractContainer<DropdownLink, DropdownButtonModel> {

	protected Link dropdownButton;
	protected BaseContainer itemContainer;

	public DropdownLink(String label) {
		super(new DropdownButtonModel());
		this.addStyleClass("dropdown");
		dropdownButton = new Link(label + " ", "#");
		dropdownButton.addStyleClass("dropdown-toggle");
		itemContainer = new BaseContainer("ul");

		itemContainer.removeStyleClass("container");
		itemContainer.addStyleClass("dropdown-menu");

		add(dropdownButton);
		add(itemContainer);
	}

	public DropdownLink addLink(Link link) {
		BaseContainer linkContainer = new BaseContainer("li");
		linkContainer.add(link);
		itemContainer.add(linkContainer);
		return this;
	}

}
