package com.doctusoft.dsw.sample.client.showcase;

import com.doctusoft.dsw.client.comp.BaseContainer;
import com.doctusoft.dsw.client.comp.Container;
import com.doctusoft.dsw.client.comp.HasComponentModel;
import com.doctusoft.dsw.client.comp.HtmlContent;
import com.doctusoft.dsw.client.comp.Select;
import com.doctusoft.dsw.client.comp.SelectItems;
import com.doctusoft.dsw.client.comp.model.BaseComponentModel;

public class ShowcaseSelectView implements HasComponentModel {
	
	private Container container = new Container();
	
	public ShowcaseSelectView() {
		new BaseContainer().withStyleClass("page-header").appendTo(container)
			.add(new HtmlContent("<h1>Select</h1>"));
		new Select<String>()
			.appendTo(container)
			.setSelectItems(SelectItems.fromStrings("First item","Second item","Third item"));
	}
	
	@Override
	public BaseComponentModel getComponentModel() {
		return container.getModel();
	}

}
