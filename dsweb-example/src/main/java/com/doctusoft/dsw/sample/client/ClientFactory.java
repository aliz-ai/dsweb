
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


import com.doctusoft.dsw.mvp.client.IPlaceController;
import com.doctusoft.dsw.mvp.client.ViewOf;
import com.doctusoft.dsw.sample.client.person.PersonDetailActivity;
import com.doctusoft.dsw.sample.client.person.PersonListActivity;
import com.doctusoft.dsw.sample.client.person.PersonRemoteServiceAsync;
import com.doctusoft.dsw.sample.client.person.SandboxActivity;
import com.doctusoft.dsw.sample.client.showcase.ShowcaseActivity;
import com.google.web.bindery.event.shared.EventBus;

public interface ClientFactory {
	
	public EventBus getEventBus();
	
	public IPlaceController getPlaceController();
	
	public ViewOf<PersonListActivity> getPersonListView();
	
	public ViewOf<PersonDetailActivity> getPersonDetailView();
	
	public ViewOf<ShowcaseActivity> getShowcaseView();
	
	public ViewOf<SandboxActivity> getSandboxView();
	
	public PersonRemoteServiceAsync getPersonRemoteServiceAsync();
	
}
