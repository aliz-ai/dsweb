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
import com.doctusoft.dsw.client.comp.model.AbstractSelectModel;
import com.doctusoft.dsw.client.comp.model.AbstractSelectModel_;
import com.doctusoft.dsw.client.comp.model.SelectItemModel;
import com.doctusoft.dsw.client.comp.model.SelectModel;
import com.doctusoft.dsw.client.comp.model.SelectModel_;
import com.google.common.collect.Lists;
import com.xedge.jquery.client.JQEvent;
import com.xedge.jquery.client.JQuery;
import com.xedge.jquery.client.handlers.EventHandler;

public class SelectRenderer extends BaseComponentRenderer {
	
	private List<JQuery> options = Lists.newArrayList();
	private JQuery nullOption = null;
	private AbstractSelectModel select;
	
	public SelectRenderer(final SelectModel select) {
		super(JQuery.select("<select/>"), select);
		this.select = select;
		new ListBindingListener<SelectItemModel>(Bindings.obs(select).get((ObservableProperty) AbstractSelectModel_._selectItemsModel)) {
			@Override
			public void inserted(ObservableList<SelectItemModel> list, int index, SelectItemModel element) {
				JQuery option = JQuery.select("<option/>");
				option.text(element.getCaption());
				option.attr("name", element.getId());
				if (index == 0) {
					options.add(0, option);
					if (nullOption == null) {
						option.prependTo(widget);
					} else {
						option.insertAfter(nullOption);
					}
				} else {
					option.insertAfter(options.get(index - 1));
					options.add(index, option);
				}
				if (element == select.getSelectedItem()) {
					applySelectedIndex();
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
		addChangeListenerAndApply(AbstractSelectModel_._selectedItem, select, new ValueChangeListener<SelectItemModel>() {
			@Override
			public void valueChanged(SelectItemModel newValue) {
				applySelectedIndex();
			}
		});
		addChangeListenerAndApply(SelectModel_._nullOptionCaption, select, new ValueChangeListener<String>() {
			@Override
			public void valueChanged(String newValue) {
				if (newValue == null && nullOption != null) {
					nullOption.remove();
					nullOption = null;
				} else if (newValue != null) {
					if (nullOption == null) {
						nullOption = JQuery.select("<option/>").prependTo(widget);
					}
					nullOption.text(newValue);
				}
			}
		});
		widget.change(new EventHandler() {
			@Override
			public void eventComplete(JQEvent event, JQuery currentJQuery) {
				int index = widget.children().index(widget.find("option:selected").get(0));
				if (nullOption != null) {
					index --;
				}
				if (index < 0) {
					select.setSelectedItem(null);
				} else {
					select.setSelectedItem(select.getSelectItemsModel().get(index));
				}
			}
		});
		new EnabledAttributeRenderer(widget, select);
	}
	
	protected void applySelectedIndex() {
		SelectItemModel item = select.getSelectedItem();
		if (item == null) {
			return;
		}
		widget.find("option[selected]").removeAttr("selected");
		setSelectedNative(options.get(select.getSelectItemsModel().indexOf(item)));
	}
	
	public static native void setSelectedNative(JQuery option) /*-{
		option.prop('selected', true);
	}-*/;
	
	
}
