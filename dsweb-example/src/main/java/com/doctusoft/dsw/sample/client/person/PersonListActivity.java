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


import java.util.List;

import com.doctusoft.MethodRef;
import com.doctusoft.ObservableProperty;
import com.doctusoft.bean.binding.observable.ObservableList;
import com.doctusoft.dsw.mvp.client.ViewOf;
import com.doctusoft.dsw.sample.client.AbstractCallback;
import com.doctusoft.dsw.sample.client.ClientFactory;
import com.google.gwt.activity.shared.AbstractActivity;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.user.client.ui.AcceptsOneWidget;

public class PersonListActivity extends AbstractActivity {

	private ClientFactory clientFactory;

	public PersonListActivity(ClientFactory clientFactory) {
		this.clientFactory = clientFactory;
	}

	@ObservableProperty
	private ObservableList<PersonDto> personList = new ObservableList<PersonDto>();

	@Override
	public void start(AcceptsOneWidget panel, EventBus eventBus) {
		ViewOf<PersonListActivity> view = clientFactory.getPersonListView();
		view.setPresenter(this);
		panel.setWidget(view);
		loadList();
	}

	private void loadList() {
		clientFactory.getPersonRemoteServiceAsync().getPersonDtos(new AbstractCallback<List<PersonDto>>(clientFactory) {
			public void onSuccess(List<PersonDto> result) {
				personList.clear();
				personList.addAll(result);
			};
		});
	}

	@MethodRef
	public void addPerson() {
		final PersonDto dto = new PersonDto();
		dto.setName("New person");
		clientFactory.getPersonRemoteServiceAsync().save(dto, new AbstractCallback<Long>(clientFactory) {
			@Override
			public void onSuccess(Long result) {
				dto.setId(result);
				personList.add(dto);
			}
		});
	}

	@MethodRef
	public void deletePerson(final PersonDto dto) {
		clientFactory.getPersonRemoteServiceAsync().delete(dto.getId(), new AbstractCallback<Void>(clientFactory) {
			@Override
			public void onSuccess(Void result) {
				personList.remove(dto);
			}
		});
	}

}