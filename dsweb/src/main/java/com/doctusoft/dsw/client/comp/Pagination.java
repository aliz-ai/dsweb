package com.doctusoft.dsw.client.comp;

import java.util.List;
import java.util.Map;

import com.doctusoft.bean.ValueChangeListener;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

public class Pagination extends BaseContainer {
	
	private BaseContainer mainContainer;
	private List<PaginationItem> paginationItems = Lists.newArrayList();
	private List<BaseContainer> links = Lists.newArrayList();
	private Map<PaginationItem, BaseContainer> linkByItem = Maps.newHashMap();
	
	public Pagination() {
		mainContainer = new BaseContainer("ul");
		addStyleClass("pagination");
		addElement(mainContainer, new Link("\u226a", "#"));
		addElement(mainContainer, new Link("\u226b", "#"));
		add(mainContainer);
	}
	
	private void addElement(BaseContainer container, HasComponentModel component) {
		BaseContainer componentContainer = new BaseContainer("li");
		componentContainer.add(component);
		container.add(componentContainer);
	}
	
	public Pagination setPaginationItems(List<PaginationItem> paginationItems) {
		for (final PaginationItem paginationItem : paginationItems) {
						
			Link paginationLink = new Link(String.valueOf(paginationItem.getSeq()), paginationItem.getLink());
			
			BaseContainer linkContainer = new BaseContainer("li");
			linkContainer.add(paginationLink);
			
			linkByItem.put(paginationItem, linkContainer);
			
			PaginationItem_._active.addChangeListener(paginationItem, new ValueChangeListener<Boolean>() {
				@Override
				public void valueChanged(Boolean newValue) {
					if (newValue) {
						for (BaseContainer container : links) {
							container.removeStyleClass("active");
						}
						linkByItem.get(paginationItem).addStyleClass("active");
					}
				}
			});
			
			PaginationItem_._disabled.addChangeListener(paginationItem, new ValueChangeListener<Boolean>() {
				@Override
				public void valueChanged(Boolean newValue) {
					BaseContainer link = linkByItem.get(paginationItem);
					if (newValue) {
						link.addStyleClass("disabled");
					} else {
						link.removeStyleClass("disabled");
					}
				}
			});
			
			addElement(mainContainer, paginationLink);
			paginationItems.add(paginationItem);
		}
		return this;
	}

}
