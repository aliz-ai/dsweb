package com.doctusoft.dsw.sample.client.passwordfield;

import lombok.Getter;
import lombok.Setter;

import com.doctusoft.ObservableProperty;
import com.doctusoft.dsw.client.comp.model.BaseComponentModel;

public class PasswordFieldModel extends BaseComponentModel {
	
	@ObservableProperty
	private String password;
	
	@Setter @Getter
	private String label;
	
	@Override
	public Iterable<com.doctusoft.bean.ObservableProperty<?, ?>> getObservableProperties() {
		return PasswordFieldModel_._observableProperties;
	}
	
}
