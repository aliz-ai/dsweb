package com.doctusoft.dsw.client.comp;

import com.doctusoft.bean.ValueChangeListener;
import com.doctusoft.bean.binding.Bindings;
import com.doctusoft.bean.binding.EmptyEventHandler;
import com.doctusoft.dsw.client.comp.model.TabSheetModel;
import com.doctusoft.dsw.client.comp.model.TabSheetModel_;
import com.google.common.base.Preconditions;

public class TabSheet extends BaseComponent<TabSheet, TabSheetModel>{

	public TabSheet() {
		super(new TabSheetModel());
	}
	
	public TabSheet withTab(Tab tab) {
		insertTab(tab, model.getTabList().size());
		return this;
	}
	
	public TabSheet withDefaultTab(String title, HasComponentModel tabPanel) {
		Tab tab = new Tab()
			.withTitle(title)
			.withContent(tabPanel.getComponentModel());
		insertTab(tab, model.getTabList().size());
		return this;
	}
	
	public TabSheet withTabOnSpecifiedIndex(Tab tab, int index) {
		insertTab(tab, index);
		return this;
	}
	
	public TabSheet withDefaultTabOnSpecifiedIndex(String title, HasComponentModel tabPanel, int index) {
		Tab tab = new Tab()
			.withTitle(title)
			.withContent(tabPanel.getComponentModel());
		insertTab(tab, index);
		return this;
	}
	
	public TabSheet withActiveTabIndex(int activeTabIndex) {
		model.setActiveTab(activeTabIndex);
		return this;
	}
	
	protected void insertTab(Tab tab, int index) {
		Integer activeTabIndex = model.getActiveTab();
		if (activeTabIndex != null && activeTabIndex != -1 && activeTabIndex < model.getTabList().size()) {
			if (index <= activeTabIndex) {
				model.setActiveTab(activeTabIndex + 1);
			}
		}
		model.getTabList().add(index, tab);
	}
	
	public void removeTab(Tab tab) {
		int indexOfRemoved = model.getTabList().indexOf(tab);
		Preconditions.checkState(indexOfRemoved != -1, "trying to remove a tab that is not in this tabsheet");
		model.getTabList().remove(tab);
		Integer activeTabIndex = model.getActiveTab();
		if (activeTabIndex != null && activeTabIndex != -1) {
			// if there was a selected tab, we update its index.
			if (activeTabIndex == indexOfRemoved) {
				model.setActiveTab(-1);
			} else if (activeTabIndex > indexOfRemoved) {
				model.setActiveTab(activeTabIndex - 1);
			}
		}
	}
	
	public TabSheet onTabShown(final EmptyEventHandler handler) {
		Bindings.obs(model).get(TabSheetModel_._activeTab).addValueChangeListener(new ValueChangeListener<Integer>() {
			@Override
			public void valueChanged(Integer newValue) {
				handler.handle();
			}
		});
		return this;
	}
	
}
