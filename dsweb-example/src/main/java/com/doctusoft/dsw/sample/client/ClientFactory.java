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


import com.doctusoft.dsw.client.mvp.PlaceController;
import com.doctusoft.dsw.mvp.client.ViewOf;
import com.doctusoft.dsw.sample.client.person.PersonDetailPresenter;
import com.doctusoft.dsw.sample.client.person.PersonListPresenter;
import com.doctusoft.dsw.sample.client.person.PersonRemoteServiceAsync;
import com.doctusoft.dsw.sample.client.person.SandboxPresenter;
import com.doctusoft.dsw.sample.client.showcase.ShowcaseActivity;
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
import com.google.web.bindery.event.shared.EventBus;

public interface ClientFactory {
	
	public EventBus getEventBus();
	
	public ViewOf<PersonListPresenter> getPersonListView();
	
	public ViewOf<PersonDetailPresenter> getPersonDetailView();
	
	public ViewOf<ShowcaseActivity> getShowcaseView();
	
	public ViewOf<SandboxPresenter> getSandboxView();
	
	public ViewOf<ShowcaseButtonsPresenter> getShowcaseButtonsView();
	
	public ViewOf<ShowcaseDatepickerPresenter> getShowcaseDatepickerView();
	
	public ViewOf<ShowcaseExceptionsPresenter> getShowcaseExceptionsView();
	
	public ViewOf<ShowcaseInputsPresenter> getShowcaseInputsView();

	public ViewOf<ShowcaseInputTagsPresenter> getShowcaseInputTagsView();
	
	public ViewOf<ShowcaseNavsPresenter> getShowcaseNavsView();
	
	public ViewOf<ShowcaseProgressBarPresenter> getShowcaseProgressBarView();
	
	public ViewOf<ShowcaseRichTextEditorPresenter> getShowcaseRichTextEditorView();
	
	public ViewOf<ShowcaseSelectPresenter> getShowcaseSelectView();
	
	public ViewOf<ShowcaseTablePresenter> getShowcaseTableView();
	
	public ViewOf<ShowcaseTabsheetPresenter> getShowcaseTabsheetView();
	
	public ViewOf<ShowcaseTypeaheadPresenter> getShowcaseTypeaheadView();
	
	public ViewOf<ShowcaseChartsPresenter> getShowcaseChartsView();
	
	public PersonRemoteServiceAsync getPersonRemoteServiceAsync();

	public void setPlaceController(PlaceController placeController);
	
	public ViewOf<ShowcaseContextMenuPresenter> getShowcaseContextMenuView();
	
	public PlaceController getPlaceController();
	
}
