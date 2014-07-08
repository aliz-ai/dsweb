package com.doctusoft.dsw.sample.client.showcase;

import com.doctusoft.bean.binding.EmptyEventHandler;
import com.doctusoft.dsw.client.comp.BaseContainer;
import com.doctusoft.dsw.client.comp.Container;
import com.doctusoft.dsw.client.comp.DropdownLink;
import com.doctusoft.dsw.client.comp.HasComponentModel;
import com.doctusoft.dsw.client.comp.HtmlContent;
import com.doctusoft.dsw.client.comp.Label;
import com.doctusoft.dsw.client.comp.Link;
import com.doctusoft.dsw.client.comp.Navs;
import com.doctusoft.dsw.client.comp.Navs.NavsItemType;
import com.doctusoft.dsw.client.comp.model.BaseComponentModel;

public class ShowcaseNavsView implements HasComponentModel {

	private Container container = new Container();
	private DropdownLink menuDropdown;

	public ShowcaseNavsView() {
		new BaseContainer().withStyleClass("page-header").appendTo(container)
			.add(new HtmlContent("<h1>Navs</h1>"));
		menuDropdown = new DropdownLink("Dropdown button").
				addLink(new Link("External link", "http://www.tehcute.com/pics/201201/Pug-wants-cookie.jpg").newWindow())
				.addLink(new Link("Link with handler").click(new EmptyEventHandler() {
					@Override
					public void handle() {
						menuDropdown.withLabel("Clicked");
					}
				}));
		new Label("Simple navs", "h3").appendTo(container);
		new Navs()
			.addMenuItem(new Link().withText("First item"))
			.addMenuItem(new Link().withText("Second item"))
			.addDropdownItem(menuDropdown)
			.appendTo(container);
		new Label("Stacked navs", "h3").appendTo(container);
		new Navs().stacked()
			.addMenuItem(new Link().withText("First item"))
			.addMenuItem(new Link().withText("Second item"))
			.appendTo(container);
		new Label("Normal navs with pills", "h3").appendTo(container);
		new Navs()
			.addMenuItem(new Link().withText("First item"))
			.addMenuItem(new Link().withText("Second item"))
			.withItemType(NavsItemType.Pills)
			.appendTo(container);
		new Label("Stacked navs with pills", "h3").appendTo(container);
		new Navs().stacked()
			.addMenuItem(new Link().withText("First item"))
			.addMenuItem(new Link().withText("Second item"))
			.withItemType(NavsItemType.Pills)
			.appendTo(container);
	}
	
	@Override
	public BaseComponentModel getComponentModel() {
		return container.getModel();
	}

}
