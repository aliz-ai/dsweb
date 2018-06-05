package com.doctusoft.dsw.client.gwt;

import java.util.ArrayList;
import java.util.List;

import com.doctusoft.bean.ObservableProperty;
import com.doctusoft.bean.ValueChangeListener;
import com.doctusoft.bean.binding.Bindings;
import com.doctusoft.bean.binding.observable.ListBindingListener;
import com.doctusoft.bean.binding.observable.ObservableList;
import com.doctusoft.dsw.client.RendererFactory;
import com.doctusoft.dsw.client.comp.Tab;
import com.doctusoft.dsw.client.comp.Tab_;
import com.doctusoft.dsw.client.comp.model.BaseComponentModel;
import com.doctusoft.dsw.client.comp.model.TabSheetModel;
import com.doctusoft.dsw.client.comp.model.TabSheetModel_;
import com.google.gwt.core.client.GWT;
import com.xedge.jquery.client.JQEvent;
import com.xedge.jquery.client.JQuery;
import com.xedge.jquery.client.handlers.EventHandler;

public class TabSheetRenderer extends BaseComponentRenderer {
	
	public static RendererFactory<JQuery> rendererFactory = GWT.create(RendererFactory.class);
	
	private List<JQuery> tabCaptionList = new ArrayList<JQuery>();
	private List<JQuery> tabContentList = new ArrayList<JQuery>();
	private JQuery tabButtonsHolder;
	private JQuery tabSheetContainer;

	private TabSheetModel tabSheet;

	public TabSheetRenderer(final TabSheetModel model) {
		super(JQuery.select("<div class='tabsheet' />"), model);
		this.tabSheet = model;
		
		tabButtonsHolder = JQuery.select("<ul class='nav nav-tabs' />").appendTo(widget);
		tabSheetContainer = JQuery.select("<div>").addClass("tab-content").appendTo(widget);
		
		Bindings.obs(model)
				.get(TabSheetModel_._activeTab)
				.addValueChangeListener(
						new ValueChangeListener<Integer>() {
							@Override
							public void valueChanged(Integer newValue) {
								if (newValue == null)
									return;
								refreshActiveClass(newValue);
							}
						});
		
		new ListBindingListener<Tab>(Bindings.obs(model).get((ObservableProperty) TabSheetModel_._tabList)) {

			@Override
			public void inserted(ObservableList<Tab> list, int index, final Tab element) {
				JQuery tabCaption =  JQuery.select( "<li>" );
				int numberOfTabs = tabButtonsHolder.children().length();
				if (numberOfTabs == index || numberOfTabs == 0) {
					tabButtonsHolder.append(tabCaption);
				} else {
					tabCaption.insertBefore(tabButtonsHolder.children().get(index));
				}
				
				final JQuery tabLink = JQuery.select( "<a>" ).appendTo(tabCaption);
				tabLink.text(element.getTitle());
				Tab_._title.addChangeListener(element, new ValueChangeListener<String>() {
					@Override
					public void valueChanged(String newValue) {
						tabLink.text(newValue);
					}
				});
				tabLink.click(new EventHandler() {
					
					@Override
					public void eventComplete(JQEvent event, JQuery currentJQuery) {
						// we have to recalculate the index because it might have changed since the insertion of the tab
						int indexOfClickedTab = model.getTabList().indexOf(element);
						
						if (model.getAutomaticTabSwitch().equals(Boolean.FALSE)) {
							model.setClickedTabIndex(indexOfClickedTab);
						} else {
							model.setActiveTab(indexOfClickedTab);
						}
						
					}
				});
				
				JQuery tabPane = JQuery.select("<div>").addClass("tab-pane").hide();
				int childrenCount = tabCaptionList.size();
				// well, the actual order doesn't matter that much, but it'll be easier to debug on the client side if it's correct
				if (childrenCount == 0 || index == childrenCount) {
					tabSheetContainer.append(tabPane);
				} else {
					tabPane.insertBefore(tabSheetContainer.children().get(index));
				}
				
				tabContentList.add(index, tabPane);
				tabCaptionList.add(index, tabCaption);
				tabWidgetAdded(tabPane, element.getContent());
				refreshActiveClass(model.getActiveTab());
			}

			@Override
			public void removed(ObservableList<Tab> list, int index, Tab element) {
				tabCaptionList.get(index).remove();
                tabContentList.get(index).get(0).removeFromParent();
				rendererFactory.dispose(element.getContent());
				tabContentList.remove(index);
				tabCaptionList.remove(index);

			}
		};
	}
	
	protected void refreshActiveClass(int tabIndex) {
		tabButtonsHolder.find("li").removeClass("active");
		tabSheetContainer.children().hide();
		if (tabIndex != -1 && tabIndex < tabCaptionList.size()) {
			tabCaptionList.get(tabIndex).addClass("active");
			tabContentList.get(tabIndex).show();
		}
		
	}
	
	protected void tabWidgetAdded(JQuery parentWidget, BaseComponentModel baseWidget) {
		JQuery rendered = rendererFactory.getRenderer(baseWidget).getWidget();
		parentWidget.append(rendered);
	}
	
	@Override
	public void detach() {
		super.detach();
		for (Tab tab : tabSheet.getTabList()) {
			rendererFactory.dispose(tab.getContent());
		}
	}
	
	@Override
	public void reattach() {
		super.reattach();
		for (Tab tab : tabSheet.getTabList()) {
			rendererFactory.reattach(tab.getContent());
		}
	}
}
