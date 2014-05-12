package com.doctusoft.dsw.sample.client.custom;

import com.doctusoft.bean.binding.Bindings;
import com.doctusoft.bean.binding.ValueBinding;
import com.doctusoft.dsw.client.comp.BaseComponent;

public class CustomComponent extends BaseComponent<CustomComponent, CustomComponentModel> {
	
	public CustomComponent(String label) {
		super(new CustomComponentModel());
		model.setLabel(label);
	}
	
	public CustomComponent bindLabel(ValueBinding<String> labelBinding) {
		Bindings.bind(labelBinding, Bindings.on(model).get(CustomComponentModel_._label));
		return this;
	}
	
}
