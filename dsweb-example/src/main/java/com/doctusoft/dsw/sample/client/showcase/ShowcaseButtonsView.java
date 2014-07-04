package com.doctusoft.dsw.sample.client.showcase;

import com.doctusoft.bean.binding.EmptyEventHandler;
import com.doctusoft.dsw.client.comp.Alert;
import com.doctusoft.dsw.client.comp.BaseContainer;
import com.doctusoft.dsw.client.comp.Button;
import com.doctusoft.dsw.client.comp.Container;
import com.doctusoft.dsw.client.comp.DropdownLink;
import com.doctusoft.dsw.client.comp.HasComponentModel;
import com.doctusoft.dsw.client.comp.HtmlContent;
import com.doctusoft.dsw.client.comp.Label;
import com.doctusoft.dsw.client.comp.Link;
import com.doctusoft.dsw.client.comp.model.BaseComponentModel;
import com.doctusoft.dsw.client.gwt.BootstrapStyleClasses;

public class ShowcaseButtonsView implements HasComponentModel {
	
	private Container container = new Container();
	private DropdownLink menuDropdown;
	
	public ShowcaseButtonsView() {
		new BaseContainer().withStyleClass("page-header").appendTo(container)
			.add(new HtmlContent("<h1>Buttons</h1>"));
		new Button("Normal button").appendTo(container);
		new Button("Small success button")
				.withStyleClasses(BootstrapStyleClasses.BTN_SUCCESS, BootstrapStyleClasses.BTN_SMALL).appendTo(container);
		new Button("Large primary button")
				.withStyleClasses(BootstrapStyleClasses.BTN_PRIMARY, BootstrapStyleClasses.BTN_LARGE).appendTo(container);
		new Label("Dropdown button", "h3").appendTo(container);
		menuDropdown = new DropdownLink("Dropdown button").
				addLink(new Link("External link", "http://www.tehcute.com/pics/201201/Pug-wants-cookie.jpg").newWindow())
				.addLink(new Link("Link with handler", new EmptyEventHandler() {
					@Override
					public void handle() {
						container.add(new Alert("Clicked"));
					}
				})).appendTo(container);
	}
	
	@Override
	public BaseComponentModel getComponentModel() {
		return container.getModel();
	}

}
