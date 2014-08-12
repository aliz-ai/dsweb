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
import com.doctusoft.dsw.client.comp.model.CheckboxModel;
import com.doctusoft.dsw.client.comp.model.CheckboxModel_;

public class Checkbox extends BaseComponent<Checkbox, CheckboxModel> {
	
	public Checkbox() {
		super(new CheckboxModel());
	}
	
	public Checkbox(String label) {
		this();
		model.setLabel(label);
	}
	
	public Checkbox bindLabel(ValueBinding<String> labelBinding) {
		Bindings.bind(labelBinding, Bindings.on(model).get(CheckboxModel_._label));
		return this;
	}
	
	public Checkbox bindChecked(ValueBinding<Boolean> checkedBinding) {
		Bindings.bind(checkedBinding, Bindings.obs(model).get(CheckboxModel_._checked));
		return this;
	}

}
