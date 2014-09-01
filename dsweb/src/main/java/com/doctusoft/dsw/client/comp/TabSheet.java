package com.doctusoft.dsw.client.comp;

import com.doctusoft.bean.ObservableProperty;
import com.doctusoft.bean.ValueChangeListener;
import com.doctusoft.bean.binding.Bindings;
import com.doctusoft.bean.binding.EmptyEventHandler;
import com.doctusoft.dsw.client.comp.model.BaseComponentModel;
import com.doctusoft.dsw.client.comp.model.ComponentEvent;
import com.doctusoft.dsw.client.comp.model.ComponentEvent_;
import com.doctusoft.dsw.client.comp.model.TabSheetModel;
import com.doctusoft.dsw.client.comp.model.TabSheetModel_;
import com.google.common.base.Objects;

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
		bindBeforeTabShownEvent(TabSheetModel_._eventBeforeTabShown, handler);
		Bindings.obs(model).get(TabSheetModel_._activeTab).addValueChangeListener(new ValueChangeListener<Tab>() {
			@Override
			public void valueChanged(Tab newValue) {
				model.getEventAfterTabHidden().fire();
			}
		});
		return this;
	}
	
	public TabSheet onAfterTabHidden(EmptyEventHandler handler) {
		bindAfterTabHiddenEvent(TabSheetModel_._eventAfterTabHidden, handler);
		Bindings.obs(model).get(TabSheetModel_._activeTab).addValueChangeListener(new ValueChangeListener<Tab>() {
			@Override
			public void valueChanged(Tab newValue) {
				model.getEventBeforeTabShown().fire();
			}
		});
		return this;
	}

	private void bindAfterTabHiddenEvent(ObservableProperty<? super TabSheetModel, ComponentEvent> eventProperty, final EmptyEventHandler handler) {
		ComponentEvent event = eventProperty.getValue(model);
		if (event == null) {
			event = new ComponentEvent();
			eventProperty.setValue(model, event);
		}
		event.setHasListeners(true);
		Bindings.obs(model).get(eventProperty).get(ComponentEvent_._fired).addValueChangeListener(new ValueChangeListener<Boolean>() {
			@Override
			public void valueChanged(Boolean newValue) {
				if (Objects.firstNonNull(newValue, false)) {
					handler.handle();
				}
			}
		});
	}
	
	private void bindBeforeTabShownEvent(ObservableProperty<? super TabSheetModel, ComponentEvent> eventProperty, final EmptyEventHandler handler) {
		ComponentEvent event = eventProperty.getValue(model);
		if (event == null) {
			event = new ComponentEvent();
			eventProperty.setValue(model, event);
		}
		event.setHasListeners(true);
		Bindings.obs(model).get(eventProperty).get(ComponentEvent_._fired).addValueChangeListener(new ValueChangeListener<Boolean>() {
			@Override
			public void valueChanged(Boolean newValue) {
				if (Objects.firstNonNull(newValue, false)) {
					handler.handle();
				}
			}
		});
	}

}
