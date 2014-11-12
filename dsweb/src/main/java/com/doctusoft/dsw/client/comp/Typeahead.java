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
import com.doctusoft.dsw.client.comp.model.TypeaheadModel;
import com.doctusoft.dsw.client.comp.model.TypeaheadModel_;


public class Typeahead<T> extends AbstractSelect<Typeahead<T>, TypeaheadModel, T> {
	
	public Typeahead() {
		super(new TypeaheadModel());
	}
	
	public Typeahead<T> showAllOnFocus() {
		model.setAllVisibleOnFocus(true);
		return this;
	}
	
	public Typeahead<T> withAllowCustomText(boolean allow) {
		model.setAllowCustomText(allow);
		return this;
	}
	
	public Typeahead<T> withPlaceHolder(String placeHolder) {
		model.setPlaceHolder(placeHolder);
		return this;
	}
	
	public Typeahead<T> allowCustomText() {
		model.setAllowCustomText(true);
		return this;
	}
	
	public Typeahead<T> bindCustomText(final ValueBinding<String> valueBinding) {
		Bindings.bind(valueBinding, Bindings.obs(model).get(TypeaheadModel_._customText));
		return this;
	}
	
	public Typeahead<T> bindPlaceHolder(ValueBinding<String> placeHolderBinding) {
		Bindings.bind(placeHolderBinding, Bindings.obs(model).get(TypeaheadModel_._placeHolder));
		return this;
	}
}
