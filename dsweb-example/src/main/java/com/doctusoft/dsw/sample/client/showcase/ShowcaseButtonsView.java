package com.doctusoft.dsw.sample.client.showcase;

/*
 * #%L
 * dsweb-example
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


import com.doctusoft.bean.binding.EmptyEventHandler;
import com.doctusoft.dsw.client.comp.Alert;
import com.doctusoft.dsw.client.comp.BaseContainer;
import com.doctusoft.dsw.client.comp.Button;
import com.doctusoft.dsw.client.comp.Container;
import com.doctusoft.dsw.client.comp.DropdownLink;
import com.doctusoft.dsw.client.comp.HasComponentModel;
import com.doctusoft.dsw.client.comp.HtmlContent;
import com.doctusoft.dsw.client.comp.Label;
import com.doctusoft.dsw.client.comp.Link;
import com.doctusoft.dsw.client.comp.model.BaseComponentModel;
import com.doctusoft.dsw.client.gwt.BootstrapStyleClasses;

public class ShowcaseButtonsView implements HasComponentModel {
	
	private Container container = new Container();
	private DropdownLink menuDropdown;
	
	public ShowcaseButtonsView() {
		new BaseContainer().withStyleClass("page-header").appendTo(container)
			.add(new HtmlContent("<h1>Buttons</h1>"));
		new Button("Normal button").appendTo(container);
		new Button("Small success button")
				.withStyleClasses(BootstrapStyleClasses.BTN_SUCCESS, BootstrapStyleClasses.BTN_SMALL).appendTo(container);
		new Button("Large primary button")
				.withStyleClasses(BootstrapStyleClasses.BTN_PRIMARY, BootstrapStyleClasses.BTN_LARGE).appendTo(container);
		new Label("Dropdown button", "h3").appendTo(container);
		menuDropdown = new DropdownLink("Dropdown button").
				addLink(new Link("External link", "http://www.tehcute.com/pics/201201/Pug-wants-cookie.jpg").newWindow())
				.addLink(new Link("Link with handler").click(new EmptyEventHandler() {
					@Override
					public void handle() {
						container.add(new Alert("Clicked"));
					}
				})).appendTo(container);
	}
	
	@Override
	public BaseComponentModel getComponentModel() {
		return container.getModel();
	}

}
