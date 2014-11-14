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


import com.doctusoft.bean.binding.ParametricEventHandler;
import com.doctusoft.dsw.client.comp.BaseContainer;
import com.doctusoft.dsw.client.comp.HtmlContent;
import com.doctusoft.dsw.client.comp.InplaceText;
import com.doctusoft.dsw.client.comp.InputText;
import com.doctusoft.dsw.client.comp.Label;
import com.doctusoft.dsw.client.comp.PasswordField;
import com.doctusoft.dsw.client.comp.Textarea;
import com.doctusoft.dsw.client.comp.model.event.KeyEvent;
import com.doctusoft.dsw.sample.client.AbstractViewWithNavBar;

public class ShowcaseInputsView extends AbstractViewWithNavBar<ShowcaseInputsPresenter> {
	
	public ShowcaseInputsView() {
		new BaseContainer().withStyleClass("page-header").appendTo(subContainer)
			.add(new HtmlContent("<h1>Inputs</h1>"));
		new Label("Simple text input", "h3").appendTo(subContainer);
		new InputText().withPlaceHolder("placeholder").appendTo(subContainer)
			.keypress(new ParametricEventHandler<KeyEvent>() {
				@Override
				public void handle(KeyEvent parameter) {
					System.out.println("pressed: " + parameter.getCode());
				}
			});
		new Label("Textarea", "h3").appendTo(subContainer);
		new Textarea().setRows(4).appendTo(subContainer);
		new Label("Password input", "h3").appendTo(subContainer);
		new PasswordField().appendTo(subContainer);
		new Label("Inplace text", "h3").appendTo(subContainer);
		new InplaceText().appendTo(subContainer);
	}

}
