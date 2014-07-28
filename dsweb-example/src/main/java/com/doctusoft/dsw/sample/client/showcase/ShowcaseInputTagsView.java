package com.doctusoft.dsw.sample.client.showcase;

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
		new InputTags().bind(bindOnPresenter().get(ShowcaseActivity_._tags)).bindTagSuggestions(bindOnPresenter().get(ShowcaseActivity_._tagSuggestions)).appendTo(container);
		
		new Label("Categorizing tags", "h3").appendTo(container);	
		new InputTags().bindTagOption(bindOnPresenter().get(ShowcaseActivity_._tagOptions))
		.bindTagOptionSuggestions(bindOnPresenter().get(ShowcaseActivity_._tagOptionSuggestions)).
		appendTo(container);
	}



	@Override
	public BaseComponentModel getComponentModel() {
		return container.getModel();
	}

}
