package com.doctusoft.dsw.client.gwt;

import java.util.ArrayList;
import java.util.List;

import com.doctusoft.bean.ObservableProperty;
import com.doctusoft.bean.ValueChangeListener;
import com.doctusoft.bean.binding.Bindings;
import com.doctusoft.bean.binding.observable.ListBindingListener;
import com.doctusoft.bean.binding.observable.ObservableList;
import com.doctusoft.dsw.client.Renderer;
import com.doctusoft.dsw.client.RendererFactory;
import com.doctusoft.dsw.client.comp.BaseContainer;
import com.doctusoft.dsw.client.comp.Tab;
import com.doctusoft.dsw.client.comp.Tab_;
import com.doctusoft.dsw.client.comp.model.AbstractContainerModel;
import com.doctusoft.dsw.client.comp.model.BaseComponentModel;
import com.doctusoft.dsw.client.comp.model.ContainerModel;
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

	public TabSheetRenderer(final TabSheetModel model) {
		super(JQuery.select("<div class='tabsheet' />"), model);
		
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

			private JQuery tabCaption;

			@Override
			public void inserted(ObservableList<Tab> list, int index, final Tab element) {
				int numberOfTabs = tabButtonsHolder.children().length();
				
				final JQuery tabLink;
				
				if (element.getTitle() != null) {
					tabCaption = JQuery.select( "<li>" );
					tabLink = JQuery.select( "<a>" ).appendTo(tabCaption);
					tabLink.text(element.getTitle());
					Tab_._title.addChangeListener(element, new ValueChangeListener<String>() {
						@Override
						public void valueChanged(String newValue) {
							tabLink.text(newValue);
						}
					});
					
				} else if (element.getTitleComponent() != null) {
					ContainerModel component = (ContainerModel) element.getTitleComponent();
					if (component.getElementType().equalsIgnoreCase("LI")) {					
						Renderer<JQuery> titleComponentRenderer = rendererFactory.getRenderer( component );
						tabCaption = titleComponentRenderer.getWidget();
					} else {
						throw new RuntimeException("TabTitleComponent's elementType must be 'li'. The given elementType '" + component.getElementType() + "' isn't valid!");
					}
					Tab_._titleComponent.addChangeListener(element, new ValueChangeListener<BaseComponentModel>() {
						@Override
						public void valueChanged(BaseComponentModel newValue) {
							if (((AbstractContainerModel<BaseComponentModel>) newValue).getElementType().equalsIgnoreCase("LI")) {					
								Renderer<JQuery> titleComponentRenderer = rendererFactory.getRenderer( newValue );
								tabCaption.replaceWith(titleComponentRenderer.getWidget());
							} else {
								throw new RuntimeException("TabTitleComponent's elementType must be 'li'. The given elementType '" + ((AbstractContainerModel<BaseComponentModel>) newValue).getElementType() + "' isn't valid!");
							}
						}
					});
					
				} else {
					return;
				}
				if (numberOfTabs == index || numberOfTabs == 0) {
					tabButtonsHolder.append(tabCaption);
				} else {
					tabCaption.insertBefore(tabButtonsHolder.children().get(index));
				}
				tabCaption.click(new EventHandler() {
					
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
	
}
