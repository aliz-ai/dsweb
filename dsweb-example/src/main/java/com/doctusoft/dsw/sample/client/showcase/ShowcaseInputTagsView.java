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
import com.doctusoft.dsw.client.comp.InputTags;
import com.doctusoft.dsw.client.comp.Label;
import com.doctusoft.dsw.client.comp.model.BaseComponentModel;
import com.doctusoft.dsw.client.comp.mvp.ContainerWithPresenter;

public class ShowcaseInputTagsView extends ContainerWithPresenter<ShowcaseActivity>{
	
	
	
	public ShowcaseInputTagsView() {
		new BaseContainer().withStyleClass("page-header").appendTo(container)
		.add(new HtmlContent("<h1>Input Tags</h1>"));
		
		new Label("Typeahead", "h3").appendTo(container);
		new InputTags()
			.bind(bindOnPresenter().get(ShowcaseActivity_._tags))
			.bindTagSuggestions(bindOnPresenter().get(ShowcaseActivity_._tagSuggestions))
			.appendTo(container);
		
		new Label("","div").bind(bindOnPresenter().get(ShowcaseActivity_._tagsJoined))
			.appendTo(container);
		
		new Label("Categorizing tags", "h3").appendTo(container);	
		new InputTags()
			.bindTagOption(bindOnPresenter().get(ShowcaseActivity_._tagOptions))
			.bindTagOptionSuggestions(bindOnPresenter().get(ShowcaseActivity_._tagOptionSuggestions)).
		appendTo(container);
	}



	@Override
	public BaseComponentModel getComponentModel() {
		return container.getModel();
	}

}
