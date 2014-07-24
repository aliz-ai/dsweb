package com.doctusoft.dsw.client.comp;

import com.doctusoft.bean.binding.Bindings;
import com.doctusoft.bean.binding.ValueBinding;
import com.doctusoft.dsw.client.comp.model.TimeInputModel;
import com.doctusoft.dsw.client.comp.model.TimeInputModel_;

public class TimeInput extends BaseComponent<TimeInput, TimeInputModel>{
	
	public TimeInput() {
		this(new TimeInputModel());
	}

	public TimeInput(TimeInputModel model) {
		super(model);
	}
	
	public TimeInput bind(final ValueBinding<String> valueBinding) {
		Bindings.bind(valueBinding, Bindings.obs(model).get(TimeInputModel_._value));
		return this;
	}


}
