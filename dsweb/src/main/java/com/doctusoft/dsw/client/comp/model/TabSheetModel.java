package com.doctusoft.dsw.client.comp.model;

import com.doctusoft.ObservableProperty;
import com.doctusoft.bean.ModelObject;
import com.doctusoft.bean.binding.observable.ObservableList;
import com.doctusoft.dsw.client.comp.Tab;

public class TabSheetModel extends BaseComponentModel implements ModelObject{
	
	@ObservableProperty
	private ObservableList<Tab> tabList = new ObservableList<Tab>();
	
	@ObservableProperty
	private Tab activeTab;

}
