package com.doctusoft.dsw.client.comp;

import lombok.Getter;

import com.doctusoft.bean.binding.Bindings;
import com.doctusoft.bean.binding.ValueBinding;
import com.doctusoft.dsw.client.comp.model.InputTextModel;
import com.doctusoft.dsw.client.comp.model.InputTextModel_;

@Getter
public class InputText extends BaseComponent<InputText, InputTextModel> {
	
	public InputText() {
		super(new InputTextModel());
	}
	
	public InputText bind(final ValueBinding<String> valueBinding) {
		Bindings.bind(valueBinding, Bindings.obs(model).get(InputTextModel_._value));
		return this;
	}
	
}
