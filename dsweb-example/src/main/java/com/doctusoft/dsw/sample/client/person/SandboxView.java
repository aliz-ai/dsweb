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

import com.doctusoft.dsw.client.comp.Button;
import com.doctusoft.dsw.client.comp.Datepicker;
import com.doctusoft.dsw.client.comp.InplaceText;
import com.doctusoft.dsw.client.comp.InputNumber;
import com.doctusoft.dsw.client.comp.InputTags;
import com.doctusoft.dsw.client.comp.InputText;
import com.doctusoft.dsw.client.comp.InputTime;
import com.doctusoft.dsw.client.comp.Link;
import com.doctusoft.dsw.client.comp.PasswordField;
import com.doctusoft.dsw.client.comp.RichTextEditor;
import com.doctusoft.dsw.client.comp.Select;
import com.doctusoft.dsw.client.comp.SelectItems;
import com.doctusoft.dsw.client.comp.Textarea;
import com.doctusoft.dsw.client.comp.Typeahead;
import com.doctusoft.dsw.client.comp.mvp.ContainerWithPresenter;

public class SandboxView extends ContainerWithPresenter<SandboxPresenter> {

	public SandboxView() {
		
		new Button()
			.bindCaption(bindOnPresenter()
					.get(SandboxPresenter_._button1))
			.click(presenterMethod(SandboxPresenter_.__changeDisabled))
			.appendTo(container);
		new Button()
			.bindCaption(bindOnPresenter()
					.get(SandboxPresenter_._button2))
			.bindDisabled(bindOnPresenter().get(SandboxPresenter_._disabled))
			.appendTo(container);
		new Link("test Link")
		.withStyleClasses("btn", "btn-default", "btn-lg")
		.bindDisabled(bindOnPresenter()
				.get(SandboxPresenter_._disabled))
		.appendTo(container);
		new InputText()
			.withPlaceHolder("input text")
			.bindDisabled(bindOnPresenter()
					.get(SandboxPresenter_._disabled))
			.appendTo(container);
		new PasswordField()
			.withPlaceHolder("password field")
			.bindDisabled(bindOnPresenter()
				.get(SandboxPresenter_._disabled))
			.appendTo(container);
		new InplaceText()
			.bindDisabled(bindOnPresenter()
					.get(SandboxPresenter_._disabled))
			.insertTo(2,container);
		new InputNumber()
			.withPlaceHolder("input number")
			.bindDisabled(bindOnPresenter()
					.get(SandboxPresenter_._disabled))
			.appendTo(container);
		new InputTags()
			.bindDisabled(bindOnPresenter()
					.get(SandboxPresenter_._disabled))
			.appendTo(container);
		Textarea textarea = new Textarea()
			.withPlaceHolder("TextArea")
			.bindDisabled(bindOnPresenter()
					.get(SandboxPresenter_._disabled));
			//.appendTo(container);
		new Typeahead<String>()
			.withSelectItems(SelectItems.fromStrings("First item","Second item","Third item"))
			.bindDisabled(bindOnPresenter()
					.get(SandboxPresenter_._disabled))
			.appendTo(container);
		new InputTime()
			.bindDisabled(bindOnPresenter()
					.get(SandboxPresenter_._disabled))
			.withPlaceHolder("time aaa..")
			.appendTo(container);
		new Datepicker()
			.withPlaceHolder("Datepicker")
			.bindDisabled(bindOnPresenter()
					.get(SandboxPresenter_._disabled))
			.insertTo(9, container);
		new Select<String>()
			.prependTo(container)
			.bindDisabled(bindOnPresenter()
					.get(SandboxPresenter_._disabled))
			.setSelectItems(SelectItems.fromStrings("First item","Second item","Third item"));
		RichTextEditor richTextEditor = new RichTextEditor().bindContent(bindOnPresenter().get(SandboxPresenter_._button1))
			.bindDisabled(bindOnPresenter()
				.get(SandboxPresenter_._disabled));		
		
		container.prepend(richTextEditor);
		
		container.insert(6, textarea);
	}
}
