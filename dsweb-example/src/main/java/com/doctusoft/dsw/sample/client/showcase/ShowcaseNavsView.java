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
import com.doctusoft.dsw.client.comp.BaseContainer;
import com.doctusoft.dsw.client.comp.DropdownLink;
import com.doctusoft.dsw.client.comp.HtmlContent;
import com.doctusoft.dsw.client.comp.Label;
import com.doctusoft.dsw.client.comp.Link;
import com.doctusoft.dsw.client.comp.Navs;
import com.doctusoft.dsw.client.comp.Navs.NavsItemType;
import com.doctusoft.dsw.sample.client.AbstractViewWithNavBar;

public class ShowcaseNavsView extends AbstractViewWithNavBar<ShowcaseNavsPresenter> {

	private DropdownLink menuDropdown;

	public ShowcaseNavsView() {
		new BaseContainer().withStyleClass("page-header").appendTo(subContainer)
			.add(new HtmlContent("<h1>Navs</h1>"));
		menuDropdown = new DropdownLink("Dropdown button").
				addLink(new Link("External link", "http://www.tehcute.com/pics/201201/Pug-wants-cookie.jpg").newWindow())
				.addLink(new Link("Link with handler").click(new EmptyEventHandler() {
					@Override
					public void handle() {
						menuDropdown.withLabel("Clicked");
					}
				}));
		new Label("Simple navs", "h3").appendTo(subContainer);
		new Navs()
			.addMenuItem(new Link().withText("First item"))
			.addMenuItem(new Link().withText("Second item"))
			.addDropdownItem(menuDropdown)
			.appendTo(subContainer);
		new Label("Stacked navs", "h3").appendTo(subContainer);
		new Navs().stacked()
			.addMenuItem(new Link().withText("First item"))
			.addMenuItem(new Link().withText("Second item"))
			.appendTo(subContainer);
		new Label("Normal navs with pills", "h3").appendTo(subContainer);
		new Navs()
			.addMenuItem(new Link().withText("First item"))
			.addMenuItem(new Link().withText("Second item"))
			.withItemType(NavsItemType.Pills)
			.appendTo(subContainer);
		new Label("Stacked navs with pills", "h3").appendTo(subContainer);
		new Navs().stacked()
			.addMenuItem(new Link().withText("First item"))
			.addMenuItem(new Link().withText("Second item"))
			.withItemType(NavsItemType.Pills)
			.appendTo(subContainer);
	}

}
