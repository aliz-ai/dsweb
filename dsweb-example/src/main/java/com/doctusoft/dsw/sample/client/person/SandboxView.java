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

import com.doctusoft.dsw.client.comp.InputText;
import com.doctusoft.dsw.client.comp.RichTextEditor;
import com.doctusoft.dsw.client.comp.mvp.ContainerWithPresenter;

public class SandboxView extends ContainerWithPresenter<SandboxActivity> {

	public SandboxView() {
		new RichTextEditor().bindContent(bindOnPresenter().get(SandboxActivity_._content2))
		.bindAutoCompleteOptions(bindOnPresenter().get(SandboxActivity_._options))
		.withAutocompleteTriggerCharacter('@').withTextToInsertBeforeAutoCompleteValue("@{")
		.appendTo(container);
		new InputText().bind(bindOnPresenter().get(SandboxActivity_._content2)).appendTo(container);
		new com.doctusoft.dsw.client.comp.Button("Add option").click(presenterMethod(SandboxActivity_.__addOption))
				.appendTo(
				container);
	}
}
