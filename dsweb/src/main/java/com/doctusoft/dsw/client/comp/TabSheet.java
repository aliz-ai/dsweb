package com.doctusoft.dsw.client.comp;

import com.doctusoft.bean.ValueChangeListener;
import com.doctusoft.bean.binding.Bindings;
import com.doctusoft.bean.binding.EmptyEventHandler;
import com.doctusoft.bean.binding.ParametricEventHandler;
import com.doctusoft.bean.binding.ValueBinding;
import com.doctusoft.dsw.client.comp.model.TabSheetModel;
import com.doctusoft.dsw.client.comp.model.TabSheetModel_;
import com.google.common.base.Preconditions;

public class TabSheet extends BaseComponent<TabSheet, TabSheetModel>{

	private EmptyEventHandler defaultEventHandler;

	public TabSheet() {
		super(new TabSheetModel());

		Bindings.obs(model).get(TabSheetModel_._activeTab).addValueChangeListener(new ValueChangeListener<Integer>() {
			@Override
			public void valueChanged(final Integer newValue) {
				if (model.getAutomaticTabSwitch().equals(Boolean.TRUE) && defaultEventHandler != null) {
					defaultEventHandler.handle();
				}
			}
		});
	}

	public TabSheet withTab(final Tab tab) {
		insertTab(tab, model.getTabList().size());
		return this;
	}

	public TabSheet withDefaultTab(final String title, final HasComponentModel tabPanel) {
		Tab tab = new Tab()
		.withTitle(title)
		.withContent(tabPanel.getComponentModel());
		insertTab(tab, model.getTabList().size());
		return this;
	}

	public TabSheet withTabOnSpecifiedIndex(final Tab tab, final int index) {
		insertTab(tab, index);
		return this;
	}

	public TabSheet withDefaultTabOnSpecifiedIndex(final String title, final HasComponentModel tabPanel, final int index) {
		Tab tab = new Tab()
		.withTitle(title)
		.withContent(tabPanel.getComponentModel());
		insertTab(tab, index);
		return this;
	}

	public TabSheet withActiveTabIndex(final int activeTabIndex) {
		model.setActiveTab(activeTabIndex);
		return this;
	}

	protected void insertTab(final Tab tab, final int index) {
		Integer activeTabIndex = model.getActiveTab();
		if (activeTabIndex != null && activeTabIndex != -1 && activeTabIndex < model.getTabList().size()) {
			if (index <= activeTabIndex) {
				model.setActiveTab(activeTabIndex + 1);
			}
		}
		model.getTabList().add(index, tab);
	}

	public void removeTab(final Tab tab) {
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
		defaultEventHandler = handler;
		return this;
	}

	public TabSheet bindActiveTabIndex(final ValueBinding<Integer> activeIndexBinding) {
		Bindings.bind(activeIndexBinding, Bindings.obs(model).get(TabSheetModel_._activeTab));
		return this;
	}

	public TabSheet tabClicked(final ParametricEventHandler<Integer> tabClickedEventHandler) {
		model.setAutomaticTabSwitch(Boolean.FALSE);

		Bindings.obs(model).get(TabSheetModel_._clickedTabIndex).addValueChangeListener(new ValueChangeListener<Integer>() {

			@Override
			public void valueChanged(final Integer newValue) {
				if (model.getAutomaticTabSwitch().equals(Boolean.FALSE) && newValue != null) {
					tabClickedEventHandler.handle(newValue);
				}
			}

		});

		return this;
	}

}
