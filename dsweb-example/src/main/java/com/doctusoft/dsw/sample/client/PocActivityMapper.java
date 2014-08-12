
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


import com.doctusoft.dsw.sample.client.person.PersonDetailActivity;
import com.doctusoft.dsw.sample.client.person.PersonDetailPlace;
import com.doctusoft.dsw.sample.client.person.PersonListActivity;
import com.doctusoft.dsw.sample.client.person.PersonListPlace;
import com.doctusoft.dsw.sample.client.person.SandboxActivity;
import com.doctusoft.dsw.sample.client.person.SandboxPlace;
import com.doctusoft.dsw.sample.client.showcase.ShowcaseActivity;
import com.doctusoft.dsw.sample.client.showcase.ShowcasePlace;
import com.google.gwt.activity.shared.Activity;
import com.google.gwt.activity.shared.ActivityMapper;
import com.google.gwt.place.shared.Place;

public class PocActivityMapper implements ActivityMapper {
	
	private final ClientFactory clientFactory;
	
	public PocActivityMapper( ClientFactory clientFactory ) {
		this.clientFactory = clientFactory;
	}
	
	@Override
	public Activity getActivity( Place place ) {
		if (place instanceof PersonListPlace) {
			return new PersonListActivity( clientFactory );
		}
		if (place instanceof PersonDetailPlace) {
			return new PersonDetailActivity( clientFactory, ((PersonDetailPlace) place).getPersonId() );
		}
		if (place instanceof ShowcasePlace) {
			return new ShowcaseActivity( clientFactory, (ShowcasePlace) place );
		}
		if (place instanceof SandboxPlace) {
			return new SandboxActivity( clientFactory );
		}
		return null;
	}
}
