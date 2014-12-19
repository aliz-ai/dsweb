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


import com.doctusoft.dsw.client.comp.BaseContainer;
import com.doctusoft.dsw.client.comp.DatePicker;
import com.doctusoft.dsw.client.comp.HtmlContent;
import com.doctusoft.dsw.client.comp.InputText;
import com.doctusoft.dsw.client.comp.Label;
import com.doctusoft.dsw.sample.client.BaseShowcaseView;

public class ShowcaseDatepickerView extends BaseShowcaseView<ShowcaseDatepickerPresenter> {

	public ShowcaseDatepickerView() {
		new BaseContainer().withStyleClass("page-header").appendTo(subContainer)
		.add(new HtmlContent("<h1>DatePicker</h1>"));

		new DatePicker().bind( bindOnPresenter().get(ShowcaseDatepickerPresenter_._date) )
			.bindFormat(bindOnPresenter().get(ShowcaseDatepickerPresenter_._format))
			.withPlaceHolder("Pick a date!")
			.appendTo(subContainer);


		BaseContainer stringRow = new BaseContainer().appendTo(subContainer);
		new Label("toString(): ").appendTo(stringRow);
		new Label().bind(bindOnPresenter().get(ShowcaseDatepickerPresenter_._dateAsString)).appendTo(stringRow);
		
		BaseContainer formatRow = new BaseContainer().appendTo(subContainer);
		new Label("Format: ").appendTo(formatRow);
		new InputText().bind(bindOnPresenter().get(ShowcaseDatepickerPresenter_._format)).appendTo(formatRow);
		

	}



}
