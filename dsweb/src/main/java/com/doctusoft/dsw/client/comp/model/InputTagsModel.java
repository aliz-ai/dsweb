package com.doctusoft.dsw.client.comp.model;

import com.doctusoft.bean.ObservableProperty;
import com.doctusoft.bean.binding.observable.ObservableList;
import com.doctusoft.dsw.client.comp.TagOption;
import com.xedge.jquery.ui.client.model.TabsOptions.TabsStringOption;

public class InputTagsModel extends BaseComponentModel {
	
	@com.doctusoft.ObservableProperty
	private ObservableList<TagOption> tagOptionList = new ObservableList<TagOption>();
	
	@com.doctusoft.ObservableProperty
	private ObservableList<String> tagList = new ObservableList<String>();
	
	@com.doctusoft.ObservableProperty
	private ObservableList<String> defaultTagList = new ObservableList<String>();
	
	@Override
	public Iterable<ObservableProperty<?, ?>> getObservableProperties() {
		return InputTagsModel_._observableProperties;
	}

}
