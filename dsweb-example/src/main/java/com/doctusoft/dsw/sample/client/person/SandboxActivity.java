
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


import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.doctusoft.MethodRef;
import com.doctusoft.ObservableProperty;
import com.doctusoft.bean.binding.observable.ObservableList;
import com.doctusoft.dsw.client.comp.SelectItem;
import com.doctusoft.dsw.client.comp.SelectItems;
import com.doctusoft.dsw.client.comp.model.ComponentEvent;
import com.doctusoft.dsw.client.comp.model.SelectionMode;
import com.doctusoft.dsw.mvp.client.ViewOf;
import com.doctusoft.dsw.sample.client.ClientFactory;
import com.google.gwt.activity.shared.AbstractActivity;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.user.client.ui.AcceptsOneWidget;

public class SandboxActivity extends AbstractActivity {
	
	private final ClientFactory clientFactory;
	
	@ObservableProperty
	private Integer activePage = 1;
	
	@ObservableProperty
	private Integer numberOfPages = 1;
	
	@ObservableProperty
	private String pageInfo = "";
	
	@ObservableProperty
	private ObservableList<SelectItem<String>> locationItems = new ObservableList<SelectItem<String>>();
	
	@ObservableProperty
	private ObservableList<PersonDto> personList = new ObservableList<PersonDto>();
	
	@ObservableProperty
	private SelectionMode selectionMode = SelectionMode.Single;
	
	@ObservableProperty
	private ObservableList<PersonDto> selection = new ObservableList<PersonDto>();
	
	@ObservableProperty
	private boolean visibility = false;
	
	@ObservableProperty
	private ComponentEvent focus = new ComponentEvent();
	
	List<PersonDto> dummy = new ArrayList<PersonDto>();
	
	public SandboxActivity( ClientFactory clientFactory ) {
		this.clientFactory = clientFactory;
	}
	
	@MethodRef
	public void checkBindings() {
		System.out.println( "visibility: " + visibility );
	}
	
	@Override
	public void start( AcceptsOneWidget panel, EventBus eventBus ) {
		ViewOf<SandboxActivity> view = clientFactory.getSandboxView();
		locationItems.addAll( SelectItems.fromStrings( "asd", "blup", "blip" ) );
		
		dummy.add(new PersonDto(1l, "Compay Segundo", "compay@buena.cu", new Date(7, 10, 18)));
		dummy.add(new PersonDto(2l, "Omara Portuondo", "omara@buena.cu", new Date(30, 9, 29)));
		dummy.add(new PersonDto(3l, "Ibrahim Ferrer", "ibrahim@buena.cu", new Date(6, 7, 27)));
		setActivePage(1);
		setNumberOfPages(dummy.size());
		setPageInfo(getActivePage() + "/" + getNumberOfPages());
		pagination();
		view.setPresenter( this );
		panel.setWidget( view );
		view.viewPresented();
		
	}
	
	@MethodRef
	public void pagination() {
		personList.clear();
		setPageInfo(getActivePage() + "/" + getNumberOfPages());
		personList.add(dummy.get(activePage-1));
	}
	
	@MethodRef
	public void hideLabel() {
		personList.removeAll(selection);
		setVisibility(false);
	}
}
