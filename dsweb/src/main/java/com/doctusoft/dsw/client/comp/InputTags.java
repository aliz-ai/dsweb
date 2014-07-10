package com.doctusoft.dsw.client.comp;

import com.doctusoft.bean.binding.Bindings;
import com.doctusoft.bean.binding.observable.ObservableList;
import com.doctusoft.bean.binding.observable.ObservableValueBinding;
import com.doctusoft.dsw.client.comp.model.InputTagsModel;
import com.doctusoft.dsw.client.comp.model.InputTagsModel_;

public class InputTags extends BaseComponent<InputTags, InputTagsModel> {
	
	public InputTags() {
		super(new InputTagsModel());
	}
	
	public InputTags bind(final ObservableValueBinding<? extends ObservableList<String>> listBinding) {
		Bindings.bind((ObservableValueBinding) listBinding, Bindings.obs(model).get(InputTagsModel_._tagList));
		return this;
	}
	
}
