package com.doctusoft.dsw.client.comp;

import com.doctusoft.bean.binding.Bindings;
import com.doctusoft.bean.binding.ValueBinding;
import com.doctusoft.dsw.client.comp.model.InputTimeModel;
import com.doctusoft.dsw.client.comp.model.InputTimeModel_;

public class InputTime extends BaseComponent<InputTime, InputTimeModel>{
	
	public InputTime() {
		this(new InputTimeModel());
	}

	public InputTime(InputTimeModel model) {
		super(model);
	}
	
	public InputTime bind(final ValueBinding<String> valueBinding) {
		Bindings.bind(valueBinding, Bindings.obs(model).get(InputTimeModel_._value));
		return this;
	}


}
