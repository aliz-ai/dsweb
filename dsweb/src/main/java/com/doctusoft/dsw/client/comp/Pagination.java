package com.doctusoft.dsw.client.comp;

/*
 * #%L
 * dsweb
 * %%
 * Copyright (C) 2014 Doctusoft Ltd.
 * %%
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public
 * License along with this program.  If not, see
 * <http://www.gnu.org/licenses/gpl-3.0.html>.
 * #L%
 */


import com.doctusoft.bean.ValueChangeListener;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import java.util.List;
import java.util.Map;

public class Pagination extends BaseContainer {
	
	private BaseContainer mainContainer;
	private List<PaginationItem> paginationItems = Lists.newArrayList();
	private List<BaseContainer> links = Lists.newArrayList();
	private Map<PaginationItem, BaseContainer> linkByItem = Maps.newHashMap();
	
	public Pagination() {
		mainContainer = new BaseContainer("ul");
		addStyleClass("pagination");
		Link previous = new Link("\u226a", "#");
		Link next = new Link("\u226b", "#");
		links.add(addElement(mainContainer, next));
		links.add(addElement(mainContainer, previous));
		addElement(mainContainer, next);
		add(mainContainer);
	}
	
	private BaseContainer addElement(BaseContainer container, HasComponentModel component) {
		BaseContainer componentContainer = new BaseContainer("li");
		componentContainer.add(component);
		container.add(componentContainer);
		return componentContainer;
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
