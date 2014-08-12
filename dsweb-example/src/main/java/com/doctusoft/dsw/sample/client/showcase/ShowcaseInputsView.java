package com.doctusoft.dsw.sample.client.showcase;

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


import com.doctusoft.dsw.client.comp.BaseContainer;
import com.doctusoft.dsw.client.comp.Container;
import com.doctusoft.dsw.client.comp.HasComponentModel;
import com.doctusoft.dsw.client.comp.HtmlContent;
import com.doctusoft.dsw.client.comp.InplaceText;
import com.doctusoft.dsw.client.comp.InputText;
import com.doctusoft.dsw.client.comp.Label;
import com.doctusoft.dsw.client.comp.PasswordField;
import com.doctusoft.dsw.client.comp.Textarea;
import com.doctusoft.dsw.client.comp.model.BaseComponentModel;
import com.doctusoft.dsw.client.comp.model.event.KeyEvent;
import com.doctusoft.dsw.client.comp.model.event.ParametricEventHandler;

public class ShowcaseInputsView implements HasComponentModel {
	
	private Container container = new Container();
	
	public ShowcaseInputsView() {
		new BaseContainer().withStyleClass("page-header").appendTo(container)
			.add(new HtmlContent("<h1>Inputs</h1>"));
		new Label("Simple text input", "h3").appendTo(container);
		new InputText().appendTo(container)
			.keypress(new ParametricEventHandler<KeyEvent>() {
				@Override
				public void handle(KeyEvent parameter) {
					System.out.println("pressed: " + parameter.getCode());
				}
			});
		new Label("Textarea", "h3").appendTo(container);
		new Textarea().setRows(4).appendTo(container);
		new Label("Password input", "h3").appendTo(container);
		new PasswordField().appendTo(container);
		new Label("Inplace text", "h3").appendTo(container);
		new InplaceText().appendTo(container);
	}
	
	@Override
	public BaseComponentModel getComponentModel() {
		return container.getModel();
	}

}
