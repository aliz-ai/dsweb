package com.doctusoft.dsw.sample.client.passwordfield;

import com.doctusoft.bean.binding.Bindings;
import com.doctusoft.bean.binding.ValueBinding;
import com.doctusoft.dsw.client.comp.BaseComponent;
import com.doctusoft.dsw.sample.client.checkbox.CheckboxModel_;

public class PasswordField extends BaseComponent<PasswordField, PasswordFieldModel> {

	public PasswordField() {
		super(new PasswordFieldModel());
	}
	
	public PasswordField(String label) {
		super(new PasswordFieldModel());
		model.setLabel(label);
	}
	
	public PasswordField bindPassword(ValueBinding<String> passwordBinding) {
		Bindings.bind(passwordBinding, Bindings.on(model).get(PasswordFieldModel_._password));
		return this;
	}

}
