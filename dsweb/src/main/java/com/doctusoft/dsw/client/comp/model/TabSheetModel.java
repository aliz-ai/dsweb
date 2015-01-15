package com.doctusoft.dsw.client.comp.model;

import com.doctusoft.ObservableProperty;
import com.doctusoft.bean.ModelObject;
import com.doctusoft.bean.binding.observable.ObservableList;
import com.doctusoft.dsw.client.comp.Tab;

public class TabSheetModel extends BaseComponentModel implements ModelObject{

	@ObservableProperty
	private ObservableList<Tab> tabList = new ObservableList<Tab>();

	/**
	 * -1 if no tab is selected
	 */
	@ObservableProperty
	private Integer activeTab = 0;

	@ObservableProperty
	private Boolean automaticTabSwitch = Boolean.TRUE;

	@ObservableProperty
	private Integer clickedTabIndex;

}
