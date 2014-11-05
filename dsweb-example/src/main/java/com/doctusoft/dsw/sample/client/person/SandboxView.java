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
import com.doctusoft.dsw.client.comp.Datepicker;
import com.doctusoft.dsw.client.comp.InplaceText;
import com.doctusoft.dsw.client.comp.InputNumber;
import com.doctusoft.dsw.client.comp.InputTags;
import com.doctusoft.dsw.client.comp.InputText;
import com.doctusoft.dsw.client.comp.InputTime;
import com.doctusoft.dsw.client.comp.Label;
import com.doctusoft.dsw.client.comp.Link;
import com.doctusoft.dsw.client.comp.PasswordField;
import com.doctusoft.dsw.client.comp.RichTextEditor;
import com.doctusoft.dsw.client.comp.Select;
import com.doctusoft.dsw.client.comp.SelectItems;
import com.doctusoft.dsw.client.comp.TabSheet;
import com.doctusoft.dsw.client.comp.Textarea;
import com.doctusoft.dsw.client.comp.Typeahead;
import com.doctusoft.dsw.client.comp.mvp.ContainerWithPresenter;
import com.google.gwt.user.client.Window;

public class SandboxView extends ContainerWithPresenter<SandboxActivity> {

	public SandboxView() {
		
		new Button()
			.bindCaption(bindOnPresenter()
					.get(SandboxActivity_._button1))
			.click(presenterMethod(SandboxActivity_.__changeEnabled))
			.appendTo(container);
		new Button()
			.bindCaption(bindOnPresenter()
					.get(SandboxActivity_._button2))
			.bindEnabled(bindOnPresenter().get(SandboxActivity_._enabled))
			.click(new EmptyEventHandler() {
				@Override
				public void handle() {
					Window.alert("clicked");
				}
			})
			.appendTo(container);
		new Link("test Link")
		.withStyleClasses("btn", "btn-default", "btn-lg")
		.bindEnabled(bindOnPresenter()
				.get(SandboxActivity_._enabled))
		.appendTo(container);
		new InputText()
			.withPlaceHolder("input text")
			.bindEnabled(bindOnPresenter()
					.get(SandboxActivity_._enabled))
			.appendTo(container);
		new PasswordField()
			.withPlaceHolder("password field")
			.bindEnabled(bindOnPresenter()
				.get(SandboxActivity_._enabled))
			.appendTo(container);
		new InplaceText()
			.bindEnabled(bindOnPresenter()
					.get(SandboxActivity_._enabled))
			.insertTo(2,container);
		new InputNumber()
			.withPlaceHolder("input number")
			.bindEnabled(bindOnPresenter()
					.get(SandboxActivity_._enabled))
			.appendTo(container);
		new InputTags()
			.bindEnabled(bindOnPresenter()
					.get(SandboxActivity_._enabled))
			.appendTo(container);
		Textarea textarea = new Textarea()
			.withPlaceHolder("TextArea")
			.bindEnabled(bindOnPresenter()
					.get(SandboxActivity_._enabled));
			//.appendTo(container);
		new Typeahead<String>()
			.withSelectItems(SelectItems.fromStrings("First item","Second item","Third item"))
			.bindEnabled(bindOnPresenter()
					.get(SandboxActivity_._enabled))
			.appendTo(container);
		new InputTime()
			.bindEnabled(bindOnPresenter()
					.get(SandboxActivity_._enabled))
			.withPlaceHolder("time aaa..")
			.appendTo(container);
		new Datepicker()
			.withPlaceHolder("Datepicker")
			.bindEnabled(bindOnPresenter()
					.get(SandboxActivity_._enabled))
			.insertTo(9, container);
		new Select<String>()
			.prependTo(container)
			.bindEnabled(bindOnPresenter()
					.get(SandboxActivity_._enabled))
			.setSelectItems(SelectItems.fromStrings("First item","Second item","Third item"));
		RichTextEditor richTextEditor = new RichTextEditor().bindContent(bindOnPresenter().get(SandboxActivity_._button1))
			.bindEnabled(bindOnPresenter()
				.get(SandboxActivity_._enabled));		
		
		container.prepend(richTextEditor);
		
		container.insert(6, textarea);
		
		final TabSheet ts = new TabSheet();
		ts.withDefaultTab("hello", new Button().click(new EmptyEventHandler() {
				@Override
				public void handle() {
					ts.withDefaultTabOnSpecifiedIndex("third", new Label("xx"), 0);
				}
			}))
			.withDefaultTab("hello2", new Label("world2"))
			.appendTo(container);
	}
}
