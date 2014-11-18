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
import com.doctusoft.dsw.client.comp.Button;
import com.doctusoft.dsw.client.comp.FreeInputTags;
import com.doctusoft.dsw.client.comp.Label;
import com.doctusoft.dsw.sample.client.BaseShowcaseView;

public class ShowcaseFreeInputTagsView extends BaseShowcaseView<ShowcaseFreeInputTagsPresenter>{
	
	public ShowcaseFreeInputTagsView() {
		new BaseContainer().withStyleClass("page-header").appendTo(subContainer)
		.add(new Label("Free Input Tags", "h1"));
		
		new FreeInputTags()
			.bind(bindOnPresenter().get(ShowcaseFreeInputTagsPresenter_._tags))
			.bindTagSuggestions(bindOnPresenter().get(ShowcaseFreeInputTagsPresenter_._tagSuggestions))
			.appendTo(subContainer);
		
		new Label("","div").bind(bindOnPresenter().get(ShowcaseFreeInputTagsPresenter_._tagsJoined))
			.appendTo(subContainer);
		
		new Button("Clear all")
			.click(presenterMethod(ShowcaseFreeInputTagsPresenter_.__clearTags))
			.appendTo(subContainer);
		
	}

}
