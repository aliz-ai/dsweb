package com.doctusoft.dsw.client.comp;

import lombok.Getter;

import com.doctusoft.bean.binding.Bindings;
import com.doctusoft.bean.binding.ValueBinding;
import com.doctusoft.dsw.client.comp.model.ButtonModel;
import com.doctusoft.dsw.client.comp.model.ButtonModel_;

@Getter
public class Button extends BaseComponent<Button, ButtonModel> {
	
	public Button() {
		super(new ButtonModel());
	}
	
	public Button(String caption) {
		this();
		model.setCaption(caption);
	}
	
	public Button bindCaption(final ValueBinding<String> captionBinding) {
		Bindings.bind(captionBinding, Bindings.obs(model).get(ButtonModel_._caption));
		return this;
	}
	
}
