package com.doctusoft.dsw.client.comp;

/*
 * #%L
 * dsweb
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


import com.doctusoft.ObservableProperty;
import com.doctusoft.bean.ValueChangeListener;
import com.doctusoft.bean.binding.Bindings;
import com.doctusoft.bean.binding.EmptyEventHandler;
import com.doctusoft.bean.binding.ValueBinding;
import com.doctusoft.dsw.client.comp.model.InputTextModel_;

public class InplaceText extends Container {

	Label label;
	InputText inputText;

	@ObservableProperty
	private Boolean labelVisible = false;
	@ObservableProperty
	private Boolean inputTextVisible = true;


	public InplaceText() {
		label = new Label().bindVisible(Bindings.obs(this).get(InplaceText_._labelVisible));
		inputText = new InputText().bindVisible(Bindings.obs(this).get(InplaceText_._inputTextVisible));;

		label.click(new EmptyEventHandler() {
			@Override
			public void handle() {
				setLabelVisible(false);
				setInputTextVisible(true);
			}
		});

		add(label);
		add(inputText);
		InputTextModel_._value.addChangeListener(inputText.getModel(), new ValueChangeListener<String>() {
			@Override
			public void valueChanged(final String newValue) {
				label.withLabel(newValue);
				setInputTextVisible(false);
				setLabelVisible(true);
			}
		});
	}

	public InplaceText bind(final ValueBinding<String> binding) {
		label.bind(binding);
		inputText.bind(binding);
		return this;
	}

	@Override
	public Container bindEnabled(final ValueBinding<Boolean> enabledBinding) {
		inputText.bindEnabled(enabledBinding);
		return this;
	}

}
