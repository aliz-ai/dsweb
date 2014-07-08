package com.doctusoft.dsw.client.comp;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
	
	InputTextModel inputTextModel;
	
	private Set<String> tags = new HashSet<String>();
	
	private boolean settingValueFromWidget = false;
	
	private boolean inserted = false;

	public InputTags() {
		super(new InputTagsModel());
		inputText = new InputText();
		inputText.bind(Bindings.obs(model).get(InputTagsModel_._inputText));
//		Bindings.bind(Bindings.obs(inputText.getModel()).get(InputTextModel_._value), Bindings.obs(model).get(InputTagsModel_._inputText));
		add(inputText);
	}
	
	
	
	public <T> InputTags bind(final ObservableValueBinding<? extends List<T>> listBinding) {
		InputTextModel_._value.addChangeListener(inputText.getModel(), new ValueChangeListener<String>() {
			@Override
			public void valueChanged(String newValue) {
				settingValueFromWidget = true;
				String[] newTags = newValue.split(",");
				
				Set<String> setHelper = new HashSet<String>();
				
				for (String tag : newTags) {
					if (!Strings.isNullOrEmpty(tag)) {
						setHelper.add(tag);
					}
				}
				
				if (setHelper.size() > tags.size()) {
					tags.addAll(setHelper);
					for (String v : tags) {
						if (!listBinding.getValue().contains(v)) {
							listBinding.getValue().add((T) v);
						}
					}
				} else if (setHelper.size() <= tags.size() && !inserted) {
					tags = setHelper;
					List<T> helper = Lists.newArrayList(listBinding.getValue()); 
					for (T v : helper) {
						if (!tags.contains(v.toString())) {
							listBinding.getValue().remove(v);
						}
					}
				}
				settingValueFromWidget = false;
			}
		});
		new ListBindingListener<T>(listBinding) {
			
			@Override
			public void inserted(ObservableList<T> list, int index, T element) {
				if (!settingValueFromWidget) {
					// handling the adding of multiple tags
					String[] addedTags = element.toString().split(",");
					for (String tag : addedTags) {
						tags.add(tag);
					}
					
					String valueString = tagSetToString(tags);
					inserted = true;
					inputText.getModel().setValue(valueString);
					inserted = false;
				}
			}

			@Override
			public void removed(ObservableList<T> list, int index, T element) {
				if (!settingValueFromWidget) {
					tags.remove(element);
					inputText.getModel().setValue(tagSetToString(tags));
				}
			}
		};
		return this;
	}
	
	private String tagSetToString(Set<String> tagSet) {
		String tagString = "";
		for (String tag : tagSet) {
			tagString = tagString + tag + ",";
		}
		if (tagString.length() > 0) {
			tagString = tagString.substring(0, tagString.length()-1);
		}
		return tagString;
	}
}
