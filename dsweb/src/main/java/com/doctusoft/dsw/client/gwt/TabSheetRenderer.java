package com.doctusoft.dsw.client.gwt;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.doctusoft.bean.ValueChangeListener;
import com.doctusoft.bean.binding.Bindings;
import com.doctusoft.bean.binding.observable.ListBindingListener;
import com.doctusoft.bean.binding.observable.ObservableList;
import com.doctusoft.dsw.client.RendererFactory;
import com.doctusoft.dsw.client.comp.Tab;
import com.doctusoft.dsw.client.comp.model.BaseComponentModel;
import com.doctusoft.dsw.client.comp.model.TabSheetModel;
import com.doctusoft.dsw.client.comp.model.TabSheetModel_;
import com.google.common.collect.Maps;
import com.google.gwt.core.client.GWT;
import com.xedge.jquery.client.JQEvent;
import com.xedge.jquery.client.JQuery;
import com.xedge.jquery.client.handlers.EventHandler;

public class TabSheetRenderer extends BaseComponentRenderer {
	
	public static RendererFactory<JQuery> rendererFactory = GWT.create(RendererFactory.class);
	
	private Map<BaseComponentModel, JQuery> renderedWidgets = Maps.newHashMap();

	private List<JQuery> tabCaptionList = new ArrayList<JQuery>();
	private List<JQuery> tabContentList = new ArrayList<JQuery>();
	private JQuery tabHolder;

	public TabSheetRenderer(final TabSheetModel model) {
		super(JQuery.select("<div class='tabsheet' />"), model);
		
		tabHolder = JQuery.select("<ul class='nav nav-tabs' />").appendTo(widget); 
		
		Bindings.obs(model)
				.get(TabSheetModel_._activeTab)
				.addValueChangeListener(
						new ValueChangeListener<Tab>() {
							@Override
							public void valueChanged(Tab newValue) {
								JQuery.select("li").removeClass("active");
								int tabIndex = model.getTabList().indexOf(newValue);
								tabCaptionList.get(tabIndex).addClass("active");
								widget.find(".tab-container").children().hide();
								tabContentList.get(tabIndex).show();
							}
						});
		
		new ListBindingListener<Tab>(Bindings.obs(model).get(TabSheetModel_._tabList)) {

			@Override
			public void inserted(ObservableList<Tab> list,
					int index, final Tab element) {
				//tabHolder.find(":nth-child("+5+")").after(tabCaption);
				//:nth-child(n)
				JQuery tabCaption =  JQuery.select( "<li>" );
				int numberOfTabs = tabHolder.find("li").length();
				if (numberOfTabs >= index || index == 0) {
					tabHolder.append(tabCaption);
				} else {
					tabHolder.children("li:nth-child("+index+")").prepend(tabCaption);
				}
				
				//tabHolder.find("li:nth-child("+--index+")").prepend(tabCaption);
				
				JQuery tabLink = JQuery.select( "<a>" ).appendTo(tabCaption);
				tabLink.text(element.getTitle());
				tabLink.click(new EventHandler() {
					
					@Override
					public void eventComplete(JQEvent event, JQuery currentJQuery) {
						model.setActiveTab(element);
					}
				});
				tabCaptionList.add(tabCaption);
				
				//tabCaption.appendTo(tabHolder.find(":nth-child("+index+")"));
				JQuery tabContent;
				if (widget.find(".tab-container").length() == 0) {
					widget.after(JQuery.select("ul")).append(JQuery.select("<div>").addClass("tab-container"));
					tabCaption.addClass("active");
					tabContent = JQuery.select("<div>");
				} else {
					tabContent = JQuery.select("<div>").hide();
				}
				tabContentList.add(tabContent);
				widget.find(".tab-container").append(tabContent);
				tabWidgetAdded(tabContent, element.getContent());
			}

			@Override
			public void removed(ObservableList<Tab> list,
					int index, Tab element) {
				tabCaptionList.get(index).remove();
				tabContentList.get(index).remove();
				tabContentList.remove(index);
				tabCaptionList.remove(index);

			}
		};
	}
	
	protected void tabWidgetAdded(JQuery parentWidget, BaseComponentModel baseWidget) {
		JQuery rendered = rendererFactory.getRenderer(baseWidget).getWidget();
		parentWidget.append(rendered);
		renderedWidgets.put(baseWidget, rendered);
	}
	
}
