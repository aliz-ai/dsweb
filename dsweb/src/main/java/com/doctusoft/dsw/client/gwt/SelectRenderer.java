package com.doctusoft.dsw.client.gwt;

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


import java.util.List;

import com.doctusoft.bean.ObservableProperty;
import com.doctusoft.bean.ValueChangeListener;
import com.doctusoft.bean.binding.Bindings;
import com.doctusoft.bean.binding.observable.ListBindingListener;
import com.doctusoft.bean.binding.observable.ObservableList;
import com.doctusoft.dsw.client.comp.model.SelectItemModel;
import com.doctusoft.dsw.client.comp.model.SelectModel;
import com.doctusoft.dsw.client.comp.model.SelectModel_;
import com.google.common.collect.Lists;
import com.xedge.jquery.client.JQEvent;
import com.xedge.jquery.client.JQuery;
import com.xedge.jquery.client.handlers.EventHandler;

public class SelectRenderer extends BaseComponentRenderer {
	
	private List<JQuery> options = Lists.newArrayList();
	private SelectModel select;
	
	public SelectRenderer(final SelectModel select) {
		super(JQuery.select("<select/>"), select);
		this.select = select;
		new ListBindingListener<SelectItemModel>(Bindings.obs(select).get((ObservableProperty) SelectModel_._selectItemsModel)) {
			@Override
			public void inserted(ObservableList<SelectItemModel> list, int index, SelectItemModel element) {
				JQuery option = JQuery.select("<option/>");
				option.text(element.getCaption());
				option.attr("name", element.getId());
				if (index == 0) {
					options.add(0, option);
					option.prependTo(widget);
				} else {
					option.insertAfter(options.get(index - 1));
					options.add(index, option);
				}
			}
			@Override
			public void removed(ObservableList<SelectItemModel> list, int index, SelectItemModel element) {
				// remove it from the DOM
				options.get(index).remove();
				// remove it from our registry
				options.remove(index);
			}
		};
		applySelectedIndex();
		SelectModel_._selectedIndex.addChangeListener(select, new ValueChangeListener<Integer>() {
			@Override
			public void valueChanged(Integer newValue) {
				applySelectedIndex();
			}
		});
		widget.change(new EventHandler() {
			@Override
			public void eventComplete(JQEvent event, JQuery currentJQuery) {
				int index = widget.children().index(widget.find("option:selected").get(0));
				select.setSelectedIndex(index);
			}
		});
	}
	
	protected void applySelectedIndex() {
		int index = select.getSelectedIndex();
		if (index == -1) {
			// do nothing
			return;
		}
		widget.find("option[selected]").removeAttr("selected");
		setSelectedNative(options.get(index));
	}
	
	public static native void setSelectedNative(JQuery option) /*-{
		option.prop('selected', true);
	}-*/;
	
	
}
