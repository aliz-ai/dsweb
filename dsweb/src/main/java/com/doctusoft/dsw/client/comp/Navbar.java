package com.doctusoft.dsw.client.comp;

import com.doctusoft.dsw.client.comp.model.ContainerModel;

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


public class Navbar extends AbstractContainer<Navbar, ContainerModel> {
	
	private BaseContainer menuItemContainer;

	public Navbar(String title) {
		this(title, false);
	}
	
	protected Navbar(String title, boolean includeContainer) {
		super(new ContainerModel());
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
