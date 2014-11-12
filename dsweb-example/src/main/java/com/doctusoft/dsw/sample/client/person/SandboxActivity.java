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
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public
 * License along with this program. If not, see
 * <http://www.gnu.org/licenses/gpl-3.0.html>.
 * #L%
 */

import java.math.BigDecimal;

import com.doctusoft.MethodRef;
import com.doctusoft.ObservableProperty;
import com.doctusoft.bean.binding.observable.ObservableList;
import com.doctusoft.dsw.client.comp.TagOption;
import com.doctusoft.dsw.mvp.client.ViewOf;
import com.doctusoft.dsw.sample.client.ClientFactory;
import com.google.gwt.activity.shared.AbstractActivity;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.user.client.ui.AcceptsOneWidget;

public class SandboxActivity extends AbstractActivity {

	private final ClientFactory clientFactory;

	@ObservableProperty
	private BigDecimal inputNumberValue;
	
	@ObservableProperty
	private boolean enabled = true;
	
	@ObservableProperty
	private String button1 = "Set Disabled all inputs";
	
	@ObservableProperty
	private String button2 = "test Button";
	
	@ObservableProperty
	private ObservableList<TagOption> selectedOptions = new ObservableList<TagOption>();
	
	@ObservableProperty
	private ObservableList<TagOption> suggestions = new ObservableList<TagOption>();

	@ObservableProperty
	private ObservableList<String> selectedStrings = new ObservableList<String>();
	
	@ObservableProperty
	private ObservableList<String> stringSuggestions = new ObservableList<String>();

	public SandboxActivity(ClientFactory clientFactory) {
		this.clientFactory = clientFactory;
	}

	@Override
	public void start(AcceptsOneWidget panel, EventBus eventBus) {
		ViewOf<SandboxActivity> view = clientFactory.getSandboxView();
		view.setPresenter(this);
		panel.setWidget(view);
		view.viewPresented();
		TagOption tag2 = new TagOption("2");
		selectedOptions.clear();
		selectedOptions.add(tag2);
		suggestions.clear();
		suggestions.add(new TagOption("1"));
		suggestions.add(tag2);
		suggestions.add(new TagOption("3"));
		//---
		selectedStrings.clear();
		selectedStrings.add("2");
		stringSuggestions.clear();
		stringSuggestions.add("1");
		stringSuggestions.add("2");
		stringSuggestions.add("3");
	}
	
	@MethodRef
	public void changeEnabled() {
		setEnabled(!isEnabled());
	}

}
