package com.doctusoft.dsw.client.comp;

import com.doctusoft.ObservableProperty;
import com.doctusoft.bean.ValueChangeListener;
import com.doctusoft.bean.binding.Bindings;
import com.doctusoft.bean.binding.EmptyEventHandler;
import com.doctusoft.bean.binding.ValueBinding;
import com.doctusoft.dsw.client.comp.model.InputTextModel_;

public class InplaceText extends Container {
	
	Label label;
	InputText inputText;
	
	@ObservableProperty
	private Boolean labelVisible = false;
	@ObservableProperty
	private Boolean inputTextVisible = true;
	
	
	public InplaceText() {
		label = new Label().bindVisible(Bindings.obs(this).get(InplaceText_._labelVisible));
		inputText = new InputText().bindVisible(Bindings.obs(this).get(InplaceText_._inputTextVisible));;
		
		label.getModel().setClickHandler(new EmptyEventHandler() {
			@Override
			public void handle() {
				setLabelVisible(false);
				setInputTextVisible(true);
			}
		});
		
		add(label);
		add(inputText);
		InputTextModel_._value.addChangeListener(inputText.getModel(), new ValueChangeListener<String>() {
			@Override
			public void valueChanged(String newValue) {
				setInputTextVisible(false);
				setLabelVisible(true);
			}
		});
	}
	
	public InplaceText bind(ValueBinding<String> binding) {
		label.bind(binding);
		inputText.bind(binding);
		return this;
	}
	

}
