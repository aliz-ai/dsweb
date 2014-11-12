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

import com.doctusoft.bean.binding.EmptyEventHandler;
import com.doctusoft.dsw.client.comp.Button;
import com.doctusoft.dsw.client.comp.InputTags;
import com.doctusoft.dsw.client.comp.mvp.ContainerWithPresenter;

public class SandboxView extends ContainerWithPresenter<SandboxActivity> {

	public SandboxView() {
		
		new InputTags().withPlaceHolder("xxx").withId("inputTags")
			.bindTagOptionSuggestions(bindOnPresenter().get(SandboxActivity_._suggestions))
			.bindTagOption(bindOnPresenter().get(SandboxActivity_._selectedOptions))
			.appendTo(container);
		
		new InputTags().withPlaceHolder("xxx").withId("inputTags")
			.bindTagSuggestions(bindOnPresenter().get(SandboxActivity_._stringSuggestions))
			.bind(bindOnPresenter().get(SandboxActivity_._selectedStrings))
			.appendTo(container);
		
		new Button("xx").appendTo(container)
			.click(new EmptyEventHandler() {
				@Override
				public void handle() {
					getPresenter().getStringSuggestions().add("4");
				}
			});
	}
}
