package com.doctusoft.dsw.client.comp;

import lombok.Getter;

import com.doctusoft.bean.binding.Bindings;
import com.doctusoft.bean.binding.ValueBinding;
import com.doctusoft.dsw.client.comp.model.LabelModel;
import com.doctusoft.dsw.client.comp.model.LabelModel_;

@Getter
public class Label extends BaseComponent<Label, LabelModel> {
	
	public Label() {
		super(new LabelModel());
	}
	
	public Label(String label) {
		this();
		model.setLabel(label);
	}
	
	public Label bind(final ValueBinding<String> labelBinding) {
		Bindings.bind(labelBinding, Bindings.obs(model).get(LabelModel_._label));
		return this;
	}
	
}
