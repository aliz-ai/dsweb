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

import com.doctusoft.MethodRef;
import com.doctusoft.ObservableProperty;
import com.doctusoft.bean.binding.observable.ObservableList;
import com.doctusoft.dsw.mvp.client.ViewOf;
import com.doctusoft.dsw.sample.client.ClientFactory;
import com.google.gwt.activity.shared.AbstractActivity;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.user.client.ui.AcceptsOneWidget;

public class SandboxActivity extends AbstractActivity {

	private final ClientFactory clientFactory;

	private static int counter = 0;

	@ObservableProperty
	private String content2 = "<p>ohplease</p><p>hello</p>";

	@ObservableProperty
	ObservableList<String> options = new ObservableList<String>();

	public SandboxActivity(ClientFactory clientFactory) {
		this.clientFactory = clientFactory;
		options.add("Bela");
		options.add("jozsi");
		options.add("Nora");
		options.add("ELemer");

	}

	@MethodRef
	public void checkBindings() {
	}

	@Override
	public void start(AcceptsOneWidget panel, EventBus eventBus) {
		ViewOf<SandboxActivity> view = clientFactory.getSandboxView();
		view.setPresenter(this);
		panel.setWidget(view);
		view.viewPresented();

	}

	@MethodRef
	public void addOption() {
		options.add(1, "option" + counter++);
	}
}
