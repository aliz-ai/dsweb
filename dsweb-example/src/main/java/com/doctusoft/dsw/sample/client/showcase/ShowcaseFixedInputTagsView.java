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
import com.doctusoft.dsw.client.comp.FixedInputTagsRemote;
import com.doctusoft.dsw.client.comp.Label;
import com.doctusoft.dsw.sample.client.BaseShowcaseView;
import com.doctusoft.dsw.sample.client.showcase.ExampleData.OldComputer;

public class ShowcaseFixedInputTagsView extends BaseShowcaseView<ShowcaseFixedInputTagsPresenter>{

	public ShowcaseFixedInputTagsView() {
		new BaseContainer().withStyleClass("page-header").appendTo(subContainer)
		.add(new Label("Fixed Input Tags", "h1"));

//		new FixedInputTags<OldComputer>()
//		.bind(bindOnPresenter().get(ShowcaseFixedInputTagsPresenter_._tags))
//		.bindTagSuggestions(bindOnPresenter().get(ShowcaseFixedInputTagsPresenter_._tagSuggestions))
//		.bindEnabled(bindOnPresenter().get(ShowcaseFixedInputTagsPresenter_._editable))
//		.appendTo(subContainer);
//
//		new Label("","div").bind(bindOnPresenter().get(ShowcaseFixedInputTagsPresenter_._tagsJoined))
//		.appendTo(subContainer);
//
//
//
//		new Button("Clear all")
//		.click(presenterMethod(ShowcaseFixedInputTagsPresenter_.__clearTags))
//		.appendTo(subContainer);
//		new Button("Add random")
//		.click(presenterMethod(ShowcaseFixedInputTagsPresenter_.__addRandom))
//		.appendTo(subContainer);
//
//		new Label("Set fixed input tag enabled/disabled", "h3").appendTo(subContainer);
//		new Checkbox().bindChecked(bindOnPresenter().get(ShowcaseFixedInputTagsPresenter_._editable)).appendTo(subContainer);

		// test remote
		new Label("--------------", "h4").appendTo(subContainer);
		new FixedInputTagsRemote<OldComputer>()
		.bind(bindOnPresenter().get(ShowcaseFixedInputTagsPresenter_._tags))
		.bindTagSuggestions(bindOnPresenter().get(ShowcaseFixedInputTagsPresenter_._tagSuggestions))
		.bindQueryString(bindOnPresenter().get(ShowcaseFixedInputTagsPresenter_._queryString))
		.bindEnabled(bindOnPresenter().get(ShowcaseFixedInputTagsPresenter_._editable))
		.appendTo(subContainer);

	}

}
