package com.doctusoft.dsw.client;

/*
 * #%L
 * dsweb
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

import com.doctusoft.dsw.client.exc.ApplicaitonExceptionEvent;
import com.doctusoft.dsw.client.exc.ExceptionReporter;
import com.doctusoft.dsw.mvp.client.ViewOf;
import com.google.gwt.activity.shared.AbstractActivity;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.place.shared.Place;
import com.google.gwt.user.client.ui.AcceptsOneWidget;

public abstract class BaseActivity<Presenter, ActualPlace extends Place, ClientFactory> extends AbstractActivity implements ExceptionReporter {

	protected ActualPlace place;
	protected ClientFactory clientFactory;
	protected EventBus eventBus;
	@Getter
	private ViewOf<Presenter> view;
	
	public BaseActivity(ClientFactory clientFactory, ActualPlace place) {
		this.clientFactory = clientFactory;
		this.place = place;
	}

	protected abstract ViewOf<Presenter> createView();
	
	@Override
	public void start(AcceptsOneWidget panel, EventBus eventBus) {
		this.eventBus = eventBus;
		view = createView();
		view.setPresenter((Presenter) this);
		panel.setWidget(view);
	}
	
	@Override
	public void reportException(Throwable t) {
		eventBus.fireEvent(new ApplicaitonExceptionEvent(this, t));
	}
}
