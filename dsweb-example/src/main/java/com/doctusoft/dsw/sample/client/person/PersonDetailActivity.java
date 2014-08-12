package com.doctusoft.dsw.sample.client.person;

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

import com.doctusoft.ObservableProperty;
import com.doctusoft.dsw.mvp.client.ViewOf;
import com.doctusoft.dsw.sample.client.AbstractCallback;
import com.doctusoft.dsw.sample.client.ClientFactory;
import com.google.gwt.activity.shared.AbstractActivity;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.user.client.ui.AcceptsOneWidget;

public class PersonDetailActivity extends AbstractActivity {
	
	private ClientFactory clientFactory;
	
	@ObservableProperty @Getter
	private PersonDto personDto;

	private long personId;

	public PersonDetailActivity(ClientFactory clientFactory, long personId) {
		this.clientFactory = clientFactory;
		this.personId = personId;
		loadPersonDto();
	}
	
	@Override
	public void start(AcceptsOneWidget panel, EventBus eventBus) {
		ViewOf<PersonDetailActivity> view = clientFactory.getPersonDetailView();
		view.setPresenter(this);
		panel.setWidget(view);
	}
	
	protected void loadPersonDto() {
		clientFactory.getPersonRemoteServiceAsync().getPerson(personId, new AbstractCallback<PersonDto>(clientFactory) {
			@Override
			public void onSuccess(PersonDto result) {
				setPersonDto(result);
			}
		});
	}

}
