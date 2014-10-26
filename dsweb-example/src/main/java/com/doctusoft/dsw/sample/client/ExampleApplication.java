package com.doctusoft.dsw.sample.client;

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
import com.doctusoft.dsw.client.comp.Container;
import com.doctusoft.dsw.client.comp.HasComponentModel;
import com.doctusoft.dsw.client.comp.HistoryHandler;
import com.doctusoft.dsw.client.comp.Link;
import com.doctusoft.dsw.client.comp.TopNavbar;
import com.doctusoft.dsw.client.exc.BasicExceptionDisplayer;
import com.doctusoft.dsw.client.mvp.AbstractPlace;
import com.doctusoft.dsw.client.mvp.NavigationHandler;
import com.doctusoft.dsw.client.mvp.PlaceController;
import com.doctusoft.dsw.client.mvp.PlaceController.PresenterStartedListener;
import com.doctusoft.dsw.client.mvp.Presenter;
import com.doctusoft.dsw.sample.client.person.PersonListPlace;
import com.doctusoft.dsw.sample.client.showcase.ShowcaseButtonsActivity;
import com.doctusoft.dsw.sample.client.showcase.ShowcaseInputsActivity;
import com.doctusoft.dsw.sample.client.showcase.ShowcaseRichTextEditorActivity;
import com.google.gwt.place.shared.Place;

public class ExampleApplication extends AbstractMVPApplication {

	private BaseContainer rootContainer;
	
	private Container contentContainer;

	private ClientFactory clientFactory;
	
	public ExampleApplication(ClientFactory clientFactory) {
		this.clientFactory = clientFactory;
		
		HistoryHandler historyHandler = new HistoryHandler().appendTo(rootContainer);

        PlaceController placeController = new PlaceController(new ExamplePlacePresenterMapper(clientFactory));
        clientFactory.setPlaceController(placeController);
        placeController.addPresenterStartedListener(new PresenterStartedListener() {
            @Override
            public void presenterStarted(Presenter<?> presenter, AbstractPlace<?> place) {
            	rootContainer.getComponentModel().getChildren().clear();
            	rootContainer.add(((HasComponentModel) presenter.getView()).getComponentModel());
            }
        });
        NavigationHandler navigationHandler = new NavigationHandler(historyHandler.getModel(), placeController,
                new ShowcaseRichTextEditorActivity.Place(), new ExamplePlaceFactory());
        navigationHandler.registerPlaces(
                new ShowcaseButtonsActivity.Place(),
                new ShowcaseInputsActivity.Place()
                );

        navigationHandler.handleCurrentHistory();
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
