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


import lombok.Getter;
import lombok.Setter;

import com.doctusoft.dsw.client.mvp.PlaceController;
import com.doctusoft.dsw.mvp.client.ViewOf;
import com.doctusoft.dsw.sample.client.person.PersonDetailActivity;
import com.doctusoft.dsw.sample.client.person.PersonDetailView;
import com.doctusoft.dsw.sample.client.person.PersonListActivity;
import com.doctusoft.dsw.sample.client.person.PersonListView;
import com.doctusoft.dsw.sample.client.person.PersonRemoteService;
import com.doctusoft.dsw.sample.client.person.PersonRemoteServiceAsync;
import com.doctusoft.dsw.sample.client.person.SandboxActivity;
import com.doctusoft.dsw.sample.client.person.SandboxView;
import com.doctusoft.dsw.sample.client.showcase.ShowcaseActivity;
import com.doctusoft.dsw.sample.client.showcase.ShowcaseButtonsActivity;
import com.doctusoft.dsw.sample.client.showcase.ShowcaseButtonsView;
import com.doctusoft.dsw.sample.client.showcase.ShowcaseDatepickerActivity;
import com.doctusoft.dsw.sample.client.showcase.ShowcaseDatepickerView;
import com.doctusoft.dsw.sample.client.showcase.ShowcaseExceptionsActivity;
import com.doctusoft.dsw.sample.client.showcase.ShowcaseExceptionsView;
import com.doctusoft.dsw.sample.client.showcase.ShowcaseInputTagsActivity;
import com.doctusoft.dsw.sample.client.showcase.ShowcaseInputTagsView;
import com.doctusoft.dsw.sample.client.showcase.ShowcaseInputsActivity;
import com.doctusoft.dsw.sample.client.showcase.ShowcaseInputsView;
import com.doctusoft.dsw.sample.client.showcase.ShowcaseNavsActivity;
import com.doctusoft.dsw.sample.client.showcase.ShowcaseNavsView;
import com.doctusoft.dsw.sample.client.showcase.ShowcaseProgressBarActivity;
import com.doctusoft.dsw.sample.client.showcase.ShowcaseProgressBarView;
import com.doctusoft.dsw.sample.client.showcase.ShowcaseRichTextEditorActivity;
import com.doctusoft.dsw.sample.client.showcase.ShowcaseRichTextEditorView;
import com.doctusoft.dsw.sample.client.showcase.ShowcaseSelectActivity;
import com.doctusoft.dsw.sample.client.showcase.ShowcaseSelectView;
import com.doctusoft.dsw.sample.client.showcase.ShowcaseTableActivity;
import com.doctusoft.dsw.sample.client.showcase.ShowcaseTableView;
import com.doctusoft.dsw.sample.client.showcase.ShowcaseTabsheetActivity;
import com.doctusoft.dsw.sample.client.showcase.ShowcaseTabsheetView;
import com.doctusoft.dsw.sample.client.showcase.ShowcaseTypeaheadActivity;
import com.doctusoft.dsw.sample.client.showcase.ShowcaseTypeaheadView;
import com.doctusoft.dsw.sample.client.showcase.ShowcaseView;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.shared.SimpleEventBus;
import com.google.web.bindery.event.shared.EventBus;

public class ClientFactoryImpl implements ClientFactory {
	
	@Getter
	private final EventBus eventBus = new SimpleEventBus();
	
	@Setter @Getter 
	private  PlaceController placeController; //= new GwtPlaceControllerWrapper(new PlaceController(eventBus));
	
	@Getter
	private final ViewOf<PersonListActivity> personListView = new PersonListView();
	
	@Getter
	private final ViewOf<PersonDetailActivity> personDetailView = new PersonDetailView();
	
	@Getter
	private final ViewOf<ShowcaseActivity> showcaseView = new ShowcaseView();
	
	@Getter
	private final ViewOf<ShowcaseButtonsActivity> showcaseButtonsView = new ShowcaseButtonsView();
	
	@Getter
	private final ViewOf<ShowcaseDatepickerActivity> showcaseDatepickerView = new ShowcaseDatepickerView();
	
	@Getter
	private final ViewOf<ShowcaseExceptionsActivity> showcaseExceptionsView = new ShowcaseExceptionsView();
	
	@Getter
	private final ViewOf<ShowcaseInputsActivity> showcaseInputsView = new ShowcaseInputsView();
	
	@Getter
	private final ViewOf<ShowcaseInputTagsActivity> showcaseInputTagsView = new ShowcaseInputTagsView();
	
	@Getter
	private final ViewOf<ShowcaseNavsActivity> showcaseNavsView = new ShowcaseNavsView();
	
	@Getter
	private final ViewOf<ShowcaseProgressBarActivity> showcaseProgressBarView = new ShowcaseProgressBarView();
	
	@Getter
	private final ViewOf<ShowcaseRichTextEditorActivity> showcaseRichTextEditorView = new ShowcaseRichTextEditorView();
	
	@Getter
	private final ViewOf<ShowcaseSelectActivity> showcaseSelectView = new ShowcaseSelectView();
	
	@Getter
	private final ViewOf<ShowcaseTableActivity> showcaseTableView = new ShowcaseTableView();
	
	@Getter
	private final ViewOf<ShowcaseTabsheetActivity> showcaseTabsheetView = new ShowcaseTabsheetView();
	
	@Getter
	private final ViewOf<ShowcaseTypeaheadActivity> showcaseTypeaheadView = new ShowcaseTypeaheadView();
	
	@Getter
	private final ViewOf<SandboxActivity> sandboxView = new SandboxView();
	
	@Getter
	private final PersonRemoteServiceAsync personRemoteServiceAsync = GWT.create(PersonRemoteService.class);
	
}
