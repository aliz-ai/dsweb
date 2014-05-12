package com.doctusoft.dsw.sample.client.checkbox;

import com.doctusoft.bean.binding.Bindings;
import com.doctusoft.bean.binding.ValueBinding;
import com.doctusoft.dsw.client.comp.BaseComponent;

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
		Bindings.bind(checkedBinding, Bindings.on(model).get(CheckboxModel_._checked));
		return this;
	}

}
