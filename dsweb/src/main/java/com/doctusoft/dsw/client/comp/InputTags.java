package com.doctusoft.dsw.client.comp;

import java.util.Collections;
import java.util.List;

import com.doctusoft.bean.ValueChangeListener;
import com.doctusoft.bean.binding.Bindings;
import com.doctusoft.bean.binding.observable.ListBindingListener;
import com.doctusoft.bean.binding.observable.ObservableList;
import com.doctusoft.bean.binding.observable.ObservableValueBinding;
import com.doctusoft.dsw.client.comp.model.InputTagsModel;
import com.doctusoft.dsw.client.comp.model.InputTagsModel_;
import com.doctusoft.dsw.client.comp.model.InputTextModel;
import com.doctusoft.dsw.client.comp.model.InputTextModel_;
import com.google.common.base.Strings;
import com.google.common.collect.Lists;

public class InputTags extends AbstractContainer<InputTags, InputTagsModel> {
	
	private InputText inputText;

	public InputTags() {
		super(new InputTagsModel());
		inputText = new InputText();
		Bindings.bind(Bindings.obs(inputText.getModel()).get(InputTextModel_._value), Bindings.obs(model).get(InputTagsModel_._inputText));
		add(inputText);
	}
	
	
	
	public <T> InputTags bind(final ObservableValueBinding<? extends List<T>> listBinding) {
		final InputTextModel inputTextModel = inputText.getModel();
		InputTextModel_._value.addChangeListener(inputText.getModel(), new ValueChangeListener<String>() {
			@Override
			public void valueChanged(String newValue) {
				
				// to avoid infinite loop
				int latestInputLength = inputTextModel.getValue().split(",").length;
				if (listBinding.getValue().size() < latestInputLength) {
					String[] newTags = newValue.split(",");
					for (String v : newTags) {
						if (!listBinding.getValue().contains(v)) {
							listBinding.getValue().add((T) v);
						}
					}
				} else if (listBinding.getValue().size() > latestInputLength) {
					String[] newTags = newValue.split(",");
					List<String> newTagList = Lists.newArrayList(newTags);
					List<T> helper = listBinding.getValue(); 
					for (T v : helper) {
						if (!newTagList.contains(v.toString())) {
							listBinding.getValue().remove(v);
						}
					}
				}
				
			}
		});
		new ListBindingListener<T>(listBinding) {
			
			@Override
			public void inserted(ObservableList<T> list, int index, T element) {
				if (Collections.frequency(list, element) > 1) {
					list.remove(element);
					return;
				}
				
				// to avoid infinite loop
				int latestInputLength = inputTextModel.getValue().split(",").length;
				if (list.size() != latestInputLength || Strings.isNullOrEmpty(inputTextModel.getValue())) {
					String valueString;
					if (Strings.isNullOrEmpty(inputTextModel.getValue())) {
						valueString = inputTextModel.getValue() + element.toString();
					} else {
						valueString = inputTextModel.getValue() + "," + element.toString();
					}
					inputText.getModel().setValue(valueString);
				}
			}

			@Override
			public void removed(ObservableList<T> list, int index, T element) {
				int latestInputLength = inputTextModel.getValue().split(",").length;
				if (list.size() != latestInputLength) {
					String valueString = list.toString().replaceAll("\\[", "").replaceAll("\\]", "");
					inputText.getModel().setValue(valueString);
				}
			}
		};
		return this;
	}
}
