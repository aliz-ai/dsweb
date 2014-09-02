package com.doctusoft.dsw.client.comp;

import com.doctusoft.bean.ValueChangeListener;
import com.doctusoft.bean.binding.Bindings;
import com.doctusoft.bean.binding.EmptyEventHandler;
import com.doctusoft.dsw.client.comp.model.BaseComponentModel;
import com.doctusoft.dsw.client.comp.model.TabSheetModel;
import com.doctusoft.dsw.client.comp.model.TabSheetModel_;

public class TabSheet extends BaseComponent<TabSheet, TabSheetModel>{

	public TabSheet() {
		super(new TabSheetModel());
	}
	
	public TabSheet withTab(Tab tab) {
		model.getTabList().add(tab);
		return this;
	}
	
	public TabSheet withDefaultTab(String title, BaseComponentModel tabPanel) {
		Tab tab = new Tab(title).withContent(tabPanel);
		model.getTabList().add(tab);
		return this;
	}
	
	public TabSheet withTabOnSpecifiedIndex(Tab tab, int index) {
		model.getTabList().add(index, tab);
		return this;
	}
	
	public TabSheet withDefaultTabOnSpecifiedIndex(String title, BaseComponentModel tabPanel, int index) {
		Tab tab = new Tab(title).withContent(tabPanel);
		model.getTabList().add(index, tab);
		return this;
	}
	
	public TabSheet onBeforeTabShown(EmptyEventHandler handler) {
		bindEvent(TabSheetModel_._eventBeforeTabShown, handler);
		Bindings.obs(model).get(TabSheetModel_._activeTab).addValueChangeListener(new ValueChangeListener<Tab>() {
			@Override
			public void valueChanged(Tab newValue) {
				model.getEventAfterTabHidden().fire();
			}
		});
		return this;
	}
	
	public TabSheet onAfterTabHidden(EmptyEventHandler handler) {
		bindEvent(TabSheetModel_._eventAfterTabHidden, handler);
		Bindings.obs(model).get(TabSheetModel_._activeTab).addValueChangeListener(new ValueChangeListener<Tab>() {
			@Override
			public void valueChanged(Tab newValue) {
				model.getEventBeforeTabShown().fire();
			}
		});
		return this;
	}

}
