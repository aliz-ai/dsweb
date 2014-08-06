package com.doctusoft.dsw.client.comp.model;

import com.doctusoft.bean.binding.observable.ObservableList;


public class ContextMenuModel extends BaseComponentModel{
	
	@com.doctusoft.ObservableProperty
	private String connectedObjectId = "";

	@com.doctusoft.ObservableProperty
	private String selector = "";
	
	@com.doctusoft.ObservableProperty
	private ObservableList<String> menuItems = new ObservableList<String>();
	
}
