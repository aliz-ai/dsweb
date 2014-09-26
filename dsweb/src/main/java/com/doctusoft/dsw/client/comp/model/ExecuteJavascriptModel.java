package com.doctusoft.dsw.client.comp.model;

import com.doctusoft.ObservableProperty;
import com.doctusoft.bean.binding.observable.ObservableList;

public class ExecuteJavascriptModel extends BaseComponentModel {
	
	@ObservableProperty
	private ObservableList<String> snippets = new ObservableList<String>();

}
