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
	
	public DropdownLink withLabel(String label) {
		dropdownButton.withText(label);
		return this;
	}

	public DropdownLink addLink(Link link) {
		BaseContainer linkContainer = new BaseContainer("li");
		linkContainer.add(link);
		itemContainer.add(linkContainer);
		return this;
	}

}
