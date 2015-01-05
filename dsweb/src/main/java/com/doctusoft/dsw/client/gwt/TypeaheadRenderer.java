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
import com.doctusoft.dsw.client.comp.model.AbstractSelectModel_;
import com.doctusoft.dsw.client.comp.model.SelectItemModel;
import com.doctusoft.dsw.client.comp.model.TypeaheadModel;
import com.doctusoft.dsw.client.comp.model.TypeaheadModel_;
import com.google.common.collect.Lists;
import com.xedge.jquery.client.JQEvent;
import com.xedge.jquery.client.JQuery;
import com.xedge.jquery.client.handlers.EventHandler;

public class TypeaheadRenderer extends BaseComponentRenderer {

	private final List<String> optionCaptions = Lists.newArrayList();

	private final TypeaheadModel typeaheadModel;

	public TypeaheadRenderer(final TypeaheadModel select) {
		super(JQuery.select("<input type=\"text\" data-provide=\"typeahead\"/>"), select);
		init(widget);
		if (select.getPlaceHolder() != null) {
			widget.attr("placeholder", select.getPlaceHolder());
		}
		typeaheadModel = select;
		if (typeaheadModel.isAllVisibleOnFocus()) {
			setShowAllOnFocus(widget);
		}

		if (typeaheadModel.getSelectedItem() != null) {
			widget.val(typeaheadModel.getSelectedItem().getCaption());
		}

		TypeaheadModel_._allVisibleOnFocus.addChangeListener(typeaheadModel, new ValueChangeListener<Boolean>() {
			@Override
			public void valueChanged(final Boolean newValue) {
				if (newValue) {
					setShowAllOnFocus(widget);
				}
			}
		});

		AbstractSelectModel_._selectedItem.addChangeListener(typeaheadModel, new ValueChangeListener<SelectItemModel>() {
			@Override
			public void valueChanged(final SelectItemModel newValue) {
				if (typeaheadModel.getSelectedItem() != null) {
					String selectedValue = typeaheadModel.getSelectedItem().getCaption();
					if (widget.val() != selectedValue) {
						widget.val(selectedValue);
					}
				}
			}
		});

		addChangeListenerAndApply(TypeaheadModel_._customText, typeaheadModel, new ValueChangeListener<String>() {
			@Override
			public void valueChanged(final String newValue) {
				if (newValue != null && newValue != widget.val() && !typeaheadModel.getSelectItemsModel().contains(newValue)) {
					widget.val(newValue);
				}
			}
		});

		new ListBindingListener<SelectItemModel>(Bindings.obs(typeaheadModel).get((ObservableProperty) AbstractSelectModel_._selectItemsModel)) {
			@Override
			public void inserted(final ObservableList<SelectItemModel> list, final int index, final SelectItemModel element) {
				updateOptions(widget, itemsToString(list));
				if (element == typeaheadModel.getSelectedItem()) {
					// the selected value just got inserted
					widget.val(element.getCaption());
				}
			}
			@Override
			public void removed(final ObservableList<SelectItemModel> list, final int index, final SelectItemModel element) {
				updateOptions(widget, itemsToString(list));
			}
		};

		widget.change(new EventHandler() {
			@Override
			public void eventComplete(final JQEvent event, final JQuery currentJQuery) {
				String widgetVal = widget.val();
				int newIndex = optionCaptions.indexOf(widgetVal);

				/*
				 * we need to set up index in every case
				 */
				if (newIndex == -1) {
					typeaheadModel.setSelectedItem(null);
				} else {
					typeaheadModel.setSelectedItem(typeaheadModel.getSelectItemsModel().get(newIndex));
				}

				if (newIndex > -1) {
					//reset to previous selection
					widget.val(typeaheadModel.getSelectItemsModel().get(newIndex).getCaption());
					typeaheadModel.setCustomText(null);
				} else if (typeaheadModel.isAllowCustomText() && newIndex == -1) {
					typeaheadModel.setCustomText(widgetVal);
				}
			}
		});

		new PlaceHolderAttributeRenderer(widget, select, TypeaheadModel_._placeHolder);
		new EnabledAttributeRenderer(widget, select);
	}

	private native void init(final JQuery widget) /*-{
		var that = this;
		widget.typeahead({
		});
	}-*/;

	private native void updateOptions(final JQuery widget, final String options) /*-{
		var typeAhead = widget.data("typeahead");
		if (typeAhead) {
		    typeAhead.source = options.split(",");
		}
	}-*/;

	/**
	 * Workaround for functionality of showing all items on focus until it is supported in bootstrap, as seen here:
	 * http://stackoverflow.com/questions/11745422/twitter-bootstrap-typeahead-to-work-like-dropdown-list-select-tag-with-autocom
	 */
	private native void setShowAllOnFocus(final JQuery widget) /*-{
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
			var temp = widget.val();
			widget.val('*');
			widget.typeahead('lookup');
			widget.val(temp);
	 	});
	}-*/;

	private String itemsToString(final List<SelectItemModel> items) {
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
