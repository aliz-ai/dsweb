package com.doctusoft.dsw.sample.client.showcase;

import com.doctusoft.dsw.client.comp.BaseContainer;
import com.doctusoft.dsw.client.comp.Container;
import com.doctusoft.dsw.client.comp.HasComponentModel;
import com.doctusoft.dsw.client.comp.HtmlContent;
import com.doctusoft.dsw.client.comp.Label;
import com.doctusoft.dsw.client.comp.SelectItems;
import com.doctusoft.dsw.client.comp.Typeahead;
import com.doctusoft.dsw.client.comp.model.BaseComponentModel;

public class ShowcaseTypeaheadView implements HasComponentModel {
	
	private Container container = new Container();
	
	public ShowcaseTypeaheadView() {
		new BaseContainer().withStyleClass("page-header").appendTo(container)
			.add(new HtmlContent("<h1>Typeahead</h1>"));
		new Label("Simple typeahead", "h3").appendTo(container);
		new Typeahead<String>()
			.appendTo(container)
			.setSelectItems(SelectItems.fromStrings("First item", "Second item", "Third item"));
		new Label("Typeahead with dropdown", "h3").appendTo(container);
		new Typeahead<String>()
			.showAllOnFocus()
			.appendTo(container)
			.setSelectItems(SelectItems.fromStrings("First item", "Second item", "Third item"));
	}
	
	@Override
	public BaseComponentModel getComponentModel() {
		return container.getModel();
	}

}