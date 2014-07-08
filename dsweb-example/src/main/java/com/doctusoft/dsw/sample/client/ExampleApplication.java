package com.doctusoft.dsw.sample.client;

import com.doctusoft.dsw.client.comp.BaseContainer;
import com.doctusoft.dsw.client.comp.Container;
import com.doctusoft.dsw.client.comp.HasComponentModel;
import com.doctusoft.dsw.client.comp.Link;
import com.doctusoft.dsw.client.comp.TopNavbar;
import com.doctusoft.dsw.client.exc.BasicExceptionDisplayer;
import com.doctusoft.dsw.sample.client.person.PersonListPlace;
import com.google.gwt.place.shared.Place;

public class ExampleApplication extends AbstractMVPApplication {

	private BaseContainer rootContainer;
	
	private Container contentContainer;

	private ClientFactory clientFactory;
	
	public ExampleApplication(ClientFactory clientFactory) {
		this.clientFactory = clientFactory;
	}
	
	@Override
	public HasComponentModel createFrameWidgets() {
		rootContainer = new BaseContainer();
		new TopNavbar("dsweb example")
			.addMenuItem(new Link("Example MVP List", "#PersonListPlace:null"))
			.addMenuItem(new Link("Component showcase", "#ShowcasePlace:Buttons"))
			.withStyleClasses("navbar-inverse", "navbar-fixed-top")
			.appendTo(rootContainer);
		BaseContainer bottomPart = new BaseContainer().appendTo(rootContainer).withStyle("padding-top: 40px");
		new BasicExceptionDisplayer(clientFactory.getEventBus(), new BaseContainer().appendTo(bottomPart));
		contentContainer = new Container().appendTo(bottomPart);
		return rootContainer;
	}
	@Override
	public void showView(HasComponentModel view) {
		contentContainer.clear();
		if (view != null) {
			contentContainer.add(view);
		}
	}
	@Override
	public Place getDefaultPlace() {
		return new PersonListPlace();
	}

}
