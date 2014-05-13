package com.doctusoft.dsw.client.comp;

import com.doctusoft.bean.binding.Bindings;
import com.doctusoft.bean.binding.ValueBinding;
import com.doctusoft.dsw.client.comp.model.InputTextModel;
import com.doctusoft.dsw.client.comp.model.InputTextModel_;

public abstract class AbstractInputText<Actual, Model extends InputTextModel> extends BaseComponent<Actual, Model> {
	
	public AbstractInputText(Model model) {
		super(model);	// supermodel, hehe :)
	}
	
	public Actual bind(final ValueBinding<String> valueBinding) {
		Bindings.bind(valueBinding, Bindings.obs(model).get(InputTextModel_._value));
		return (Actual) this;
	}

}
