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


import com.doctusoft.bean.binding.Bindings;
import com.doctusoft.bean.binding.ValueBinding;
import com.doctusoft.dsw.client.comp.model.TextareaModel;
import com.doctusoft.dsw.client.comp.model.TextareaModel_;

public class Textarea extends BaseComponent<Textarea, TextareaModel> {

	public Textarea() {
		super(new TextareaModel());
	}

	public Textarea setRows(final int rows) {
		model.setRows(rows);
		return this;
	}

	public Textarea bind(final ValueBinding<String> valueBinding) {
		Bindings.bind(valueBinding, Bindings.obs(model).get(TextareaModel_._value));
		return this;
	}

	public Textarea withPlaceHolder(final String placeHolder) {
		model.setPlaceHolder(placeHolder);
		return this;
	}

	public Textarea bindPlaceHolder(final ValueBinding<String> placeholderBinding) {
		Bindings.bind(placeholderBinding, Bindings.obs(model).get(TextareaModel_._placeHolder));
		return this;
	}

}
