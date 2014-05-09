package com.doctusoft.dsw.client.comp;

import lombok.Getter;

import com.doctusoft.bean.ModelObject;
import com.doctusoft.bean.binding.Bindings;
import com.doctusoft.bean.binding.ValueBinding;

@Getter
public class InputText extends BaseComponent<InputText> implements ModelObject {
	
	@Override
	public Iterable<com.doctusoft.bean.ObservableProperty<?, ?>> getObservableProperties() {
		return InputText_._observableProperties;
	}

	@com.doctusoft.ObservableProperty
	private String value = "";
	
	public InputText bind(final ValueBinding<String> valueBinding) {
		Bindings.bind(valueBinding, Bindings.obs(this).get(InputText_._value));
		return this;
	}
	
}
