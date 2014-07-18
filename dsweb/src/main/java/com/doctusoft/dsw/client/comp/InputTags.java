package com.doctusoft.dsw.client.comp;

import java.util.List;

import javax.swing.text.html.HTML.Tag;

import com.doctusoft.bean.binding.Bindings;
import com.doctusoft.bean.binding.observable.ObservableList;
import com.doctusoft.bean.binding.observable.ObservableValueBinding;
import com.doctusoft.dsw.client.comp.model.InputTagsModel;
import com.doctusoft.dsw.client.comp.model.InputTagsModel_;
import com.doctusoft.dsw.client.comp.model.TypeaheadModel;

public class InputTags extends BaseComponent<InputTags, InputTagsModel>{
	
	public InputTags() {
		super(new InputTagsModel());
	}
	
	public InputTags withTagSuggestions(List<String> tagSuggestions) {
		model.setTagSuggestions(new ObservableList<String>(tagSuggestions));
		return this;
	}
	
	public InputTags bind(final ObservableValueBinding<? extends ObservableList<String>> listBinding) {
		Bindings.bind((ObservableValueBinding) listBinding, Bindings.obs(model).get(InputTagsModel_._tagList));
		return this;
	}
	
	public InputTags bindTagSuggestions(final ObservableValueBinding<? extends ObservableList<String>> listBinding) {
		Bindings.bind((ObservableValueBinding) listBinding, Bindings.obs(model).get(InputTagsModel_._tagSuggestions));
		return this;
	}
	
	public InputTags bindTagOption(final ObservableValueBinding<? extends ObservableList<TagOption>> listBinding) {
		Bindings.bind((ObservableValueBinding) listBinding, Bindings.obs(model).get(InputTagsModel_._tagOptionList));
		return this;
	}
	
	public InputTags bindTagOptionSuggestions(final ObservableValueBinding<? extends ObservableList<TagOption>> listBinding) {
		Bindings.bind((ObservableValueBinding) listBinding, Bindings.obs(model).get(InputTagsModel_._tagOptionSuggestions));
		return this;
	}
	
	
	
	
	
}
