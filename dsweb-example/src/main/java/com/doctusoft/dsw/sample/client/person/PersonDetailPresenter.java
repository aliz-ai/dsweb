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


import java.io.Serializable;

import lombok.Getter;

import com.doctusoft.ObservableProperty;
import com.doctusoft.dsw.client.mvp.AbstractPresenter;
import com.doctusoft.dsw.mvp.client.ViewOf;
import com.doctusoft.dsw.sample.client.AbstractCallback;
import com.doctusoft.dsw.sample.client.ClientFactory;

public class PersonDetailPresenter extends AbstractPresenter<PersonDetailPresenter> {
	
	@Getter
	private ViewOf<PersonDetailPresenter> view;
	
	private ClientFactory clientFactory;
	
	@ObservableProperty
	public PersonDto personDto;

	private long personId;

	public PersonDetailPresenter(Place place, ClientFactory clientFactory) {
		view = clientFactory.getPersonDetailView();
		this.clientFactory = clientFactory;
		/*
		 * TODO get personId from place param
		 */
		//this.personId = personId;
		loadPersonDto();
	}
	
	public static class Place extends com.doctusoft.dsw.client.mvp.AbstractPlace<PersonDetailPresenter> implements Serializable {
		public Place() {
			super("persondetail", PersonDetailPresenter.class );
		}
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
