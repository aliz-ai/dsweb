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
import com.doctusoft.dsw.client.comp.HtmlContent;
import com.doctusoft.dsw.client.comp.Label;
import com.doctusoft.dsw.client.comp.Typeahead;
import com.doctusoft.dsw.sample.client.BaseShowcaseView;

public class ShowcaseTypeaheadView extends BaseShowcaseView<ShowcaseTypeaheadPresenter> {
	
	public ShowcaseTypeaheadView() {
		new BaseContainer().withStyleClass("page-header").appendTo(subContainer)
			.add(new HtmlContent("<h1>Typeahead</h1>"));
		new Label("Simple typeahead", "h3").appendTo(subContainer);
		new Typeahead<String>()
			.appendTo(subContainer)
			.bind(bindOnPresenter().get(ShowcaseTypeaheadPresenter_._value))
			.bindSelectItems(bindOnPresenter().get(ShowcaseTypeaheadPresenter_._stringOptions));
		new Label("Typeahead with dropdown", "h3").appendTo(subContainer);
		new Typeahead<String>()
			.showAllOnFocus()
			.bind(bindOnPresenter().get(ShowcaseTypeaheadPresenter_._value))
			.appendTo(subContainer)
			.bindSelectItems(bindOnPresenter().get(ShowcaseTypeaheadPresenter_._stringOptions));
	}

}
