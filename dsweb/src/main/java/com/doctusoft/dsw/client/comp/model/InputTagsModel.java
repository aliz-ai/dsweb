package com.doctusoft.dsw.client.comp.model;

import com.doctusoft.ObservableProperty;
import com.doctusoft.bean.ModelObject;
import com.doctusoft.bean.binding.observable.ObservableList;
import com.doctusoft.dsw.client.comp.TagOption;

public class InputTagsModel extends BaseComponentModel implements ModelObject {
	
	@ObservableProperty
	private ObservableList<TagOption> tagOptionList = new ObservableList<TagOption>();
	
	@ObservableProperty
	private ObservableList<TagOption> tagOptionSuggestions = new ObservableList<TagOption>();
	
	@ObservableProperty
	private ObservableList<String> tagList = new ObservableList<String>();
	
	@ObservableProperty
	private ObservableList<String> tagSuggestions = new ObservableList<String>();
	

}
