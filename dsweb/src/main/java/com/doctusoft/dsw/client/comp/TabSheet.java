package com.doctusoft.dsw.client.comp;

import com.doctusoft.dsw.client.comp.model.BaseComponentModel;
import com.doctusoft.dsw.client.comp.model.TabSheetModel;

public class TabSheet extends BaseComponent<TabSheet, TabSheetModel>{

	public TabSheet() {
		super(new TabSheetModel());
	}
	
	public TabSheet withTab(Tab tab) {
		model.getTabList().add(tab);
		return this;
	}
	
	public TabSheet withDefaultTab(String title, BaseComponentModel tabPanel) {
		Tab tab = new Tab(title);
		model.getTabList().add(tab);
		return this;
	}

}
