
package com.doctusoft.dsw.client.comp.model;

import com.doctusoft.ObservableProperty;
import com.doctusoft.bean.binding.observable.ObservableList;

public class RichTextEditorModel extends BaseComponentModel {
	
	@ObservableProperty
	private String content;
	
	@ObservableProperty
	private ObservableList<String> autoCompleteOptions = new ObservableList<String>();
	
}
