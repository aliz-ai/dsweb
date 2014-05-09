package com.doctusoft.dsw.client.comp;

import lombok.Getter;

import com.doctusoft.bean.binding.Bindings;
import com.doctusoft.bean.binding.ValueBinding;

@Getter
public class Label extends BaseComponent<Label> {
	
	@Override
	public Iterable<com.doctusoft.bean.ObservableProperty<?, ?>> getObservableProperties() {
		return Label_._observableProperties;
	}
	
	@com.doctusoft.ObservableProperty
	private String label;
	
	public Label bind(final ValueBinding<String> labelBinding) {
		Bindings.bind(labelBinding, Bindings.obs((Label) this).get(Label_._label));
		return (Label) this;
	}
	
	public Label() {
	}
	
	public Label(String label) {
		setLabel(label);
	}

}
