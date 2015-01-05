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
import com.doctusoft.dsw.client.comp.Checkbox;
import com.doctusoft.dsw.client.comp.DropdownLink;
import com.doctusoft.dsw.client.comp.HtmlContent;
import com.doctusoft.dsw.client.comp.Label;
import com.doctusoft.dsw.client.comp.Link;
import com.doctusoft.dsw.client.gwt.BootstrapIcon;
import com.doctusoft.dsw.client.gwt.BootstrapStyleClasses;
import com.doctusoft.dsw.sample.client.BaseShowcaseView;

public class ShowcaseButtonsView extends BaseShowcaseView<ShowcaseButtonsPresenter> {

	private DropdownLink menuDropdown;

	public ShowcaseButtonsView() {

		new BaseContainer().withStyleClass("page-header").appendTo(subContainer)
		.add(new HtmlContent("<h1>Buttons</h1>"));
		new Button("Normal button")
		.bindEnabled(bindOnPresenter().get(ShowcaseButtonsPresenter_._editable))
		.appendTo(subContainer);
		new Button("Small success button")
		.bindEnabled(bindOnPresenter().get(ShowcaseButtonsPresenter_._editable))
		.withStyleClasses(BootstrapStyleClasses.BTN_SUCCESS, BootstrapStyleClasses.BTN_SMALL).appendTo(subContainer);
		new Button("Large primary button")
		.bindEnabled(bindOnPresenter().get(ShowcaseButtonsPresenter_._editable))
		.withStyleClasses(BootstrapStyleClasses.BTN_PRIMARY, BootstrapStyleClasses.BTN_LARGE).appendTo(subContainer);
		new Label("Dropdown button", "h3").appendTo(subContainer);
		menuDropdown = new DropdownLink("Dropdown button").
				addLink(new Link("External link", "http://www.tehcute.com/pics/201201/Pug-wants-cookie.jpg").newWindow())
				.addLink(new Link("Link with handler").click(new EmptyEventHandler() {
					@Override
					public void handle() {
						subContainer.add(new Alert("Clicked"));
					}
				})).appendTo(subContainer);
		new Button("Button with icon")
		.bindEnabled(bindOnPresenter().get(ShowcaseButtonsPresenter_._editable))
		.withIcon(BootstrapIcon.ICON_BOOK).appendTo(subContainer);

		new Label("Set buttons enabled/disabled", "h3").appendTo(subContainer);
		new Checkbox().bindChecked(bindOnPresenter().get(ShowcaseButtonsPresenter_._editable)).appendTo(subContainer);
	}

}
