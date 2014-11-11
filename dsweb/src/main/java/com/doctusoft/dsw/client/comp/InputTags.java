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


import java.util.List;

import com.doctusoft.bean.binding.Bindings;
import com.doctusoft.bean.binding.observable.ObservableList;
import com.doctusoft.bean.binding.observable.ObservableValueBinding;
import com.doctusoft.dsw.client.comp.model.InputTagsModel;
import com.doctusoft.dsw.client.comp.model.InputTagsModel_;

public class InputTags extends BaseComponent<InputTags, InputTagsModel>{
	
	public InputTags() {
		super(new InputTagsModel());
	}
	
	public InputTags withTagSuggestions(List<String> tagSuggestions) {
		model.setTagSuggestions(new ObservableList<String>(tagSuggestions));
		return this;
	}
	
	public InputTags widthPlaceHolder(String placeHolderText) {
		model.setPlaceHolder(placeHolderText);
		return this;
	}
	
	public InputTags bind(final ObservableValueBinding<? extends ObservableList<String>> listBinding) {
		Bindings.bind((ObservableValueBinding) listBinding, Bindings.obs(model).get(InputTagsModel_._tagList));
		return this;
	}
	
	public InputTags bindTagSuggestions(final ObservableValueBinding<? extends ObservableList<String>> listBinding) {
		Bindings.bind((ObservableValueBinding) listBinding, Bindings.obs(model).get(InputTagsModel_._tagSuggestions));
		return this;
	}
	
	public InputTags bindTagOption(final ObservableValueBinding<? extends ObservableList<TagOption>> listBinding) {
		Bindings.bind((ObservableValueBinding) listBinding, Bindings.obs(model).get(InputTagsModel_._tagOptionList));
		return this;
	}
	
	public InputTags bindTagOptionSuggestions(final ObservableValueBinding<? extends ObservableList<TagOption>> listBinding) {
		Bindings.bind((ObservableValueBinding) listBinding, Bindings.obs(model).get(InputTagsModel_._tagOptionSuggestions));
		return this;
	}
	
	public InputTags bindPlaceHolder(ObservableValueBinding<String> placeHolderBinding) {
		Bindings.bind(placeHolderBinding, Bindings.obs(model).get(InputTagsModel_._placeHolder));
		return this;
	}
	
	
	
	
	
}
