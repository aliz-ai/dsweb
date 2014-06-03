package com.doctusoft.dsw.client.comp;

import com.doctusoft.bean.binding.ValueBinding;
import com.doctusoft.dsw.client.comp.model.InputTagsModel;

public class InputTags extends AbstractContainer<InputTags, InputTagsModel> {
	
	private InputText inputText;

	public InputTags() {
		super(new InputTagsModel());
		inputText = new InputText();
		add(inputText);
	}
	
	public InputTags bind(final ValueBinding<String> valueBinding) {
		inputText.bind(valueBinding);
		return this;
	}
}
