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
import com.doctusoft.dsw.client.comp.model.BaseComponentModel;
import com.doctusoft.dsw.client.exc.BasicExceptionDisplayer;
import com.doctusoft.dsw.client.mvp.AbstractPlace;
import com.doctusoft.dsw.client.mvp.NavigationHandler;
import com.doctusoft.dsw.client.mvp.PlaceController;
import com.doctusoft.dsw.client.mvp.PlaceController.PresenterStartedListener;
import com.doctusoft.dsw.client.mvp.Presenter;
import com.doctusoft.dsw.sample.client.person.PersonDetailPresenter;
import com.doctusoft.dsw.sample.client.person.PersonListPresenter;
import com.doctusoft.dsw.sample.client.showcase.ShowcaseButtonsPresenter;
import com.doctusoft.dsw.sample.client.showcase.ShowcaseChartsPresenter;
import com.doctusoft.dsw.sample.client.showcase.ShowcaseContextMenuPresenter;
import com.doctusoft.dsw.sample.client.showcase.ShowcaseDatepickerPresenter;
import com.doctusoft.dsw.sample.client.showcase.ShowcaseExceptionsPresenter;
import com.doctusoft.dsw.sample.client.showcase.ShowcaseInputTagsPresenter;
import com.doctusoft.dsw.sample.client.showcase.ShowcaseInputsPresenter;
import com.doctusoft.dsw.sample.client.showcase.ShowcaseNavsPresenter;
import com.doctusoft.dsw.sample.client.showcase.ShowcaseProgressBarPresenter;
import com.doctusoft.dsw.sample.client.showcase.ShowcaseRichTextEditorPresenter;
import com.doctusoft.dsw.sample.client.showcase.ShowcaseSelectPresenter;
import com.doctusoft.dsw.sample.client.showcase.ShowcaseTablePresenter;
import com.doctusoft.dsw.sample.client.showcase.ShowcaseTabsheetPresenter;
import com.doctusoft.dsw.sample.client.showcase.ShowcaseTypeaheadPresenter;

public class ExampleApplication implements HasComponentModel {

	private BaseContainer rootContainer;
	
	private Container contentContainer;

	private ClientFactory clientFactory;
	
	public ExampleApplication(ClientFactory clientFactory) {
		this.clientFactory = clientFactory;
		init();
	}
	
	private void init() {
		rootContainer = new BaseContainer();
		new TopNavbar("dsweb example")
			.addMenuItem(new Link("Example MVP List", "#personlist"))
			.addMenuItem(new Link("Component showcase", "#showcasebuttons"))
			.withStyleClasses("navbar-inverse", "navbar-fixed-top")
			.appendTo(rootContainer);
		BaseContainer bottomPart = new BaseContainer().appendTo(rootContainer).withStyle("padding-top: 40px");
		new BasicExceptionDisplayer(clientFactory.getEventBus(), new BaseContainer().appendTo(bottomPart));
		contentContainer = new Container().appendTo(bottomPart);
		
		PlaceController placeController = new PlaceController(new ExamplePlacePresenterMapper(clientFactory));
        clientFactory.setPlaceController(placeController);
        placeController.addPresenterStartedListener(new PresenterStartedListener() {
            @Override
            public void presenterStarted(Presenter<?> presenter, AbstractPlace<?> place) {
            	contentContainer.getComponentModel().getChildren().clear();
            	contentContainer.add(((HasComponentModel) presenter.getView()));
            }
        });
        
		HistoryHandler historyHandler = new HistoryHandler().appendTo(rootContainer);
		 NavigationHandler navigationHandler = new NavigationHandler(historyHandler.getModel(), placeController,
	                new ShowcaseButtonsPresenter.Place(), new ExamplePlaceFactory());
	        navigationHandler.registerPlaces(
	                new ShowcaseButtonsPresenter.Place(),
	                new ShowcaseDatepickerPresenter.Place(),
	                new ShowcaseRichTextEditorPresenter.Place(),
	                new ShowcaseInputTagsPresenter.Place(),
	                new ShowcaseExceptionsPresenter.Place(),
	                new ShowcaseSelectPresenter.Place(),
	                new ShowcaseNavsPresenter.Place(),
	                new ShowcaseTablePresenter.Place(),
	                new ShowcaseTabsheetPresenter.Place(),
	                new ShowcaseTablePresenter.Place(),
	                new ShowcaseTypeaheadPresenter.Place(),
	                new ShowcaseProgressBarPresenter.Place(),
	                new ShowcaseChartsPresenter.Place(),
	                new ShowcaseInputsPresenter.Place(),
	                new ShowcaseContextMenuPresenter.Place(),
	                new PersonListPresenter.Place(),
	                new PersonDetailPresenter.Place()
	                );
	        navigationHandler.handleCurrentHistory();
	}

	@Override
	public BaseComponentModel getComponentModel() {
		return rootContainer.getComponentModel();
	}

}
