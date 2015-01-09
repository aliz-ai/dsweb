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
import com.doctusoft.dsw.sample.client.person.PersonDetailPresenter;
import com.doctusoft.dsw.sample.client.person.PersonDetailView;
import com.doctusoft.dsw.sample.client.person.PersonListPresenter;
import com.doctusoft.dsw.sample.client.person.PersonListView;
import com.doctusoft.dsw.sample.client.person.PersonRemoteService;
import com.doctusoft.dsw.sample.client.person.PersonRemoteServiceAsync;
import com.doctusoft.dsw.sample.client.sandbox.SandboxPresenter;
import com.doctusoft.dsw.sample.client.sandbox.SandboxView;
import com.doctusoft.dsw.sample.client.showcase.ShowcaseActivity;
import com.doctusoft.dsw.sample.client.showcase.ShowcaseButtonsPresenter;
import com.doctusoft.dsw.sample.client.showcase.ShowcaseButtonsView;
import com.doctusoft.dsw.sample.client.showcase.ShowcaseChartsPresenter;
import com.doctusoft.dsw.sample.client.showcase.ShowcaseChartsView;
import com.doctusoft.dsw.sample.client.showcase.ShowcaseContextMenuPresenter;
import com.doctusoft.dsw.sample.client.showcase.ShowcaseContextMenuView;
import com.doctusoft.dsw.sample.client.showcase.ShowcaseDatepickerPresenter;
import com.doctusoft.dsw.sample.client.showcase.ShowcaseDatepickerView;
import com.doctusoft.dsw.sample.client.showcase.ShowcaseExceptionsPresenter;
import com.doctusoft.dsw.sample.client.showcase.ShowcaseExceptionsView;
import com.doctusoft.dsw.sample.client.showcase.ShowcaseFixedInputTagsPresenter;
import com.doctusoft.dsw.sample.client.showcase.ShowcaseFixedInputTagsView;
import com.doctusoft.dsw.sample.client.showcase.ShowcaseFreeInputTagsPresenter;
import com.doctusoft.dsw.sample.client.showcase.ShowcaseFreeInputTagsView;
import com.doctusoft.dsw.sample.client.showcase.ShowcaseInputsPresenter;
import com.doctusoft.dsw.sample.client.showcase.ShowcaseInputsView;
import com.doctusoft.dsw.sample.client.showcase.ShowcaseNavsPresenter;
import com.doctusoft.dsw.sample.client.showcase.ShowcaseNavsView;
import com.doctusoft.dsw.sample.client.showcase.ShowcaseOnbeforeunloadPresenter;
import com.doctusoft.dsw.sample.client.showcase.ShowcaseOnbeforeunloadView;
import com.doctusoft.dsw.sample.client.showcase.ShowcaseProgressBarPresenter;
import com.doctusoft.dsw.sample.client.showcase.ShowcaseProgressBarView;
import com.doctusoft.dsw.sample.client.showcase.ShowcaseRichTextEditorPresenter;
import com.doctusoft.dsw.sample.client.showcase.ShowcaseRichTextEditorView;
import com.doctusoft.dsw.sample.client.showcase.ShowcaseSelectPresenter;
import com.doctusoft.dsw.sample.client.showcase.ShowcaseSelectView;
import com.doctusoft.dsw.sample.client.showcase.ShowcaseTablePresenter;
import com.doctusoft.dsw.sample.client.showcase.ShowcaseTableView;
import com.doctusoft.dsw.sample.client.showcase.ShowcaseTabsheetPresenter;
import com.doctusoft.dsw.sample.client.showcase.ShowcaseTabsheetView;
import com.doctusoft.dsw.sample.client.showcase.ShowcaseTypeaheadPresenter;
import com.doctusoft.dsw.sample.client.showcase.ShowcaseTypeaheadView;
import com.doctusoft.dsw.sample.client.showcase.ShowcaseView;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.shared.SimpleEventBus;
import com.google.web.bindery.event.shared.EventBus;

public class ClientFactoryImpl implements ClientFactory {

	@Getter
	private final EventBus eventBus = new SimpleEventBus();

	@Setter @Getter
	private  PlaceController placeController;

	@Getter(lazy=true)
	private final ViewOf<PersonListPresenter> personListView = new PersonListView();

	@Getter(lazy=true)
	private final ViewOf<PersonDetailPresenter> personDetailView = new PersonDetailView();

	@Getter(lazy=true)
	private final ViewOf<ShowcaseActivity> showcaseView = new ShowcaseView();

	@Getter(lazy=true)
	private final ViewOf<ShowcaseButtonsPresenter> showcaseButtonsView = new ShowcaseButtonsView();

	@Getter(lazy=true)
	private final ViewOf<ShowcaseDatepickerPresenter> showcaseDatepickerView = new ShowcaseDatepickerView();

	@Getter(lazy=true)
	private final ViewOf<ShowcaseExceptionsPresenter> showcaseExceptionsView = new ShowcaseExceptionsView();

	@Getter(lazy=true)
	private final ViewOf<ShowcaseInputsPresenter> showcaseInputsView = new ShowcaseInputsView();

	@Getter(lazy=true)
	private final ViewOf<ShowcaseFreeInputTagsPresenter> showcaseFreeInputTagsView = new ShowcaseFreeInputTagsView();

	@Getter(lazy=true)
	private final ViewOf<ShowcaseFixedInputTagsPresenter> showcaseFixedInputTagsView = new ShowcaseFixedInputTagsView();

	@Getter(lazy=true)
	private final ViewOf<ShowcaseOnbeforeunloadPresenter> showcaseOnbeforeunloadView = new ShowcaseOnbeforeunloadView();

	@Getter(lazy=true)
	private final ViewOf<ShowcaseNavsPresenter> showcaseNavsView = new ShowcaseNavsView();

	@Getter(lazy=true)
	private final ViewOf<ShowcaseProgressBarPresenter> showcaseProgressBarView = new ShowcaseProgressBarView();

	@Getter(lazy=true)
	private final ViewOf<ShowcaseRichTextEditorPresenter> showcaseRichTextEditorView = new ShowcaseRichTextEditorView();

	@Getter(lazy=true)
	private final ViewOf<ShowcaseSelectPresenter> showcaseSelectView = new ShowcaseSelectView();

	@Getter(lazy=true)
	private final ViewOf<ShowcaseTablePresenter> showcaseTableView = new ShowcaseTableView();

	@Getter(lazy=true)
	private final ViewOf<ShowcaseTabsheetPresenter> showcaseTabsheetView = new ShowcaseTabsheetView();

	@Getter(lazy=true)
	private final ViewOf<ShowcaseTypeaheadPresenter> showcaseTypeaheadView = new ShowcaseTypeaheadView();

	@Getter(lazy=true)
	private final ViewOf<ShowcaseChartsPresenter> showcaseChartsView = new ShowcaseChartsView();

	@Getter(lazy=true)
	private final ViewOf<ShowcaseContextMenuPresenter> showcaseContextMenuView = new ShowcaseContextMenuView();

	@Getter(lazy=true)
	private final ViewOf<SandboxPresenter> sandboxView = new SandboxView();

	@Getter(lazy=true)
	private final PersonRemoteServiceAsync personRemoteServiceAsync = GWT.create(PersonRemoteService.class);

}
