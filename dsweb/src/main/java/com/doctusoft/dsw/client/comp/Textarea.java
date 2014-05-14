package com.doctusoft.dsw.client.comp;

import com.doctusoft.bean.binding.Bindings;
import com.doctusoft.bean.binding.ValueBinding;
import com.doctusoft.dsw.client.comp.model.TextareaModel;
import com.doctusoft.dsw.client.comp.model.TextareaModel_;

public class Textarea extends BaseComponent<Textarea, TextareaModel> {
	
	public Textarea() {
		super(new TextareaModel());
	}
	
	public Textarea(int rows) {
		super(new TextareaModel());
		model.setRows(rows);
	}
	
	public Textarea bind(ValueBinding<String> valueBinding) {
		Bindings.bind(valueBinding, Bindings.obs(model).get(TextareaModel_._value));
		return this;
	}

}
