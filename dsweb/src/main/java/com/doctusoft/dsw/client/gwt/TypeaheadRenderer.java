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
import com.doctusoft.dsw.client.comp.model.SelectModel_;
import com.doctusoft.dsw.client.comp.model.TypeaheadModel;
import com.doctusoft.dsw.client.comp.model.TypeaheadModel_;
import com.google.common.collect.Lists;
import com.xedge.jquery.client.JQEvent;
import com.xedge.jquery.client.JQuery;
import com.xedge.jquery.client.handlers.EventHandler;

public class TypeaheadRenderer extends BaseComponentRenderer {
	
	private List<String> optionCaptions = Lists.newArrayList();
	
	private TypeaheadModel typeaheadModel;
	
	public TypeaheadRenderer(final TypeaheadModel select) {
		super(JQuery.select("<input type=\"text\" data-provide=\"typeahead\"/>"), select);
		init(widget);
		typeaheadModel = select;
		if (select.isAllVisibleOnFocus()) {
			setShowAllOnFocus(widget);
		}
		
		if (select.getSelectedIndex() != -1) {
			int selectedIndex = select.getSelectedIndex();
			if (selectedIndex >= 0 && selectedIndex < select.getSelectItemsModel().size()) {
				widget.val(select.getSelectItemsModel().get(select.getSelectedIndex()).getCaption());
			}
		}
		
		TypeaheadModel_._allVisibleOnFocus.addChangeListener(select, new ValueChangeListener<Boolean>() {
			@Override
			public void valueChanged(Boolean newValue) {
				if (newValue) {
					setShowAllOnFocus(widget);
				}
			}
		});
		
		SelectModel_._selectedIndex.addChangeListener(select, new ValueChangeListener<Integer>() {
			@Override
			public void valueChanged(Integer newValue) {
				if (select.getSelectedIndex() != -1) {
					widget.val(select.getSelectItemsModel().get(newValue).getCaption());
				}
			}
		});
		
		new ListBindingListener<SelectItemModel>(Bindings.obs(select).get((ObservableProperty) SelectModel_._selectItemsModel)) {
			@Override
			public void inserted(ObservableList<SelectItemModel> list, int index, SelectItemModel element) {
				updateOptions(widget, itemsToString(list));
				if (index == select.getSelectedIndex()) {
					// the selected value just got inserted
					widget.val(element.getCaption());
				}
			}
			@Override
			public void removed(ObservableList<SelectItemModel> list, int index, SelectItemModel element) {
				updateOptions(widget, itemsToString(list));
			} 
		};
		
		widget.change(new EventHandler() {
			@Override
			public void eventComplete(JQEvent event, JQuery currentJQuery) {
				widget.val(typeaheadModel.getSelectItemsModel().get(typeaheadModel.getSelectedIndex()).getCaption());				
			}
		});
	}
	
	private void itemSelected(String item) {
		int index = optionCaptions.indexOf(item);
		typeaheadModel.setSelectedIndex(index);
	}
	
	private native void init(JQuery widget) /*-{
		var that = this;
		widget.typeahead({
			updater: function(item) {
				$entry(that.@com.doctusoft.dsw.client.gwt.TypeaheadRenderer::itemSelected(Ljava/lang/String;)(item));
			}
		});
	}-*/;
	
	private native void updateOptions(JQuery widget, String options) /*-{
		var typeAhead = widget.data("typeahead");
		if (typeAhead) {
		    typeAhead.source = options.split(",");
		}
	}-*/;
	
	/**
	 * Workaround for functionality of showing all items on focus until it is supported in bootstrap, as seen here:
	 * http://stackoverflow.com/questions/11745422/twitter-bootstrap-typeahead-to-work-like-dropdown-list-select-tag-with-autocom
	 */
	private native void setShowAllOnFocus(JQuery widget) /*-{
		widget.data("typeahead").matcher = function(item) {
        		if (this.query == '*') {
            		return true;
        		} else {
            		return item.toLowerCase().indexOf(this.query.trim().toLowerCase()) >= 0;
        		}
    		};
    	widget.data("typeahead").highlighter = function(item) {
		        return "<div>" + item + "</div>"
		    };
		widget.click(function(event) {
			widget.val('*');
			widget.typeahead('lookup');
			widget.val('');
	 	});
	}-*/;
	
	private String itemsToString(List<SelectItemModel> items) {
		String options = "";
		for (int i = 0; i < items.size(); i++) {
			options = options + items.get(i).getCaption();
			if (i != items.size()-1) {
				options +=  ",";
			}
		}
		optionCaptions.clear();
		for (SelectItemModel item : items) {
			optionCaptions.add(item.getCaption());
		}
		return options;
	}
}
