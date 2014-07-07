package com.doctusoft.dsw.sample.client.showcase;

import com.doctusoft.dsw.client.comp.BaseContainer;
import com.doctusoft.dsw.client.comp.Container;
import com.doctusoft.dsw.client.comp.HasComponentModel;
import com.doctusoft.dsw.client.comp.HtmlContent;
import com.doctusoft.dsw.client.comp.Link;
import com.doctusoft.dsw.client.comp.Navbar;
import com.doctusoft.dsw.client.comp.model.BaseComponentModel;

public class ShowcaseNavbarView implements HasComponentModel {

	private Container container = new Container();
	
	public ShowcaseNavbarView() {
		new BaseContainer().withStyleClass("page-header").appendTo(container)
		.add(new HtmlContent("<h1>Navbars</h1>"));
		new Navbar("Brand")
			.addMenuItem(new Link("External link", "http://www.tehcute.com/pics/201201/Pug-wants-cookie.jpg").newWindow())
			.appendTo(container);
	}

	@Override
	public BaseComponentModel getComponentModel() {
		return container.getModel();
	}
}
