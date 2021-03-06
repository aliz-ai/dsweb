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
import com.doctusoft.dsw.client.comp.Checkbox;
import com.doctusoft.dsw.client.comp.HtmlContent;
import com.doctusoft.dsw.client.comp.Label;
import com.doctusoft.dsw.client.comp.Typeahead;
import com.doctusoft.dsw.client.comp.TypeaheadRemote;
import com.doctusoft.dsw.sample.client.BaseShowcaseView;
import com.doctusoft.dsw.sample.client.showcase.ShowcaseTypeaheadPresenter.TypeaheadRemoteTestModel;

public class ShowcaseTypeaheadView extends BaseShowcaseView<ShowcaseTypeaheadPresenter> {

	public ShowcaseTypeaheadView() {
		new BaseContainer().withStyleClass("page-header").appendTo(subContainer)
		.add(new HtmlContent("<h1>Typeahead</h1>"));
		new Label("Simple typeahead", "h3").appendTo(subContainer);
		new Typeahead<String>()
		.appendTo(subContainer)
		.bindEnabled(bindOnPresenter().get(ShowcaseTypeaheadPresenter_._editable))
		.bind(bindOnPresenter().get(ShowcaseTypeaheadPresenter_._value))
		.bindSelectItems(bindOnPresenter().get(ShowcaseTypeaheadPresenter_._stringOptions));
		new Label("Typeahead with dropdown", "h3").appendTo(subContainer);
		new Typeahead<String>()
		.showAllOnFocus()
		.bind(bindOnPresenter().get(ShowcaseTypeaheadPresenter_._value))
		.appendTo(subContainer)
		.bindEnabled(bindOnPresenter().get(ShowcaseTypeaheadPresenter_._editable))
		.bindSelectItems(bindOnPresenter().get(ShowcaseTypeaheadPresenter_._stringOptions));

		new Label("Typeahead with query logic", "h3").appendTo(subContainer);
		new TypeaheadRemote<TypeaheadRemoteTestModel>()
		.bind(bindOnPresenter().get(ShowcaseTypeaheadPresenter_._remoteValue))
		.bindQueryString(bindOnPresenter().get(ShowcaseTypeaheadPresenter_._queryString))
		.bindOptions(bindOnPresenter().get(ShowcaseTypeaheadPresenter_._options))
		.bindEnabled(bindOnPresenter().get(ShowcaseTypeaheadPresenter_._editable))
		.appendTo(subContainer);

		new Label("Set typeaheads enabled/disabled", "h3").appendTo(subContainer);
		new Checkbox().bindChecked(bindOnPresenter().get(ShowcaseTypeaheadPresenter_._editable)).appendTo(subContainer);
	}

}
