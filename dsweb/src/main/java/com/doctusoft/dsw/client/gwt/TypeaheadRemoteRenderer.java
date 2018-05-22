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
import java.util.Map;

import com.doctusoft.bean.ValueChangeListener;
import com.doctusoft.dsw.client.comp.model.SelectItemModel;
import com.doctusoft.dsw.client.comp.model.TypeaheadRemoteModel;
import com.doctusoft.dsw.client.comp.model.TypeaheadRemoteModel_;
import com.google.common.collect.Maps;
import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.core.client.JsArray;
import com.xedge.jquery.client.JQuery;

public class TypeaheadRemoteRenderer extends BaseComponentRenderer {

	private TypeaheadRemoteModel typeaheadRemoteModel;
	private JavaScriptObject callbackMethod;

	private Map<SelectItemModelItem, SelectItemModel> itemsToModel = Maps.newHashMap();

	public TypeaheadRemoteRenderer(final TypeaheadRemoteModel model) {
		super(JQuery.select("<input type=\"text\" data-provide=\"typeahead\"/>"), model);

		this.typeaheadRemoteModel = model;

		init(widget);

		addChangeListener(TypeaheadRemoteModel_._value, typeaheadRemoteModel, new ValueChangeListener<SelectItemModel>() {
			@Override
			public void valueChanged(final SelectItemModel newValue) {
				widget.val(typeaheadRemoteModel.getValue().getCaption());
			}
		});

		if (typeaheadRemoteModel.getValue() != null) {
			widget.val(typeaheadRemoteModel.getValue().getCaption());
		}

		addChangeListenerAndApply(TypeaheadRemoteModel_._options, typeaheadRemoteModel, new ValueChangeListener<List<SelectItemModel>>() {

			private List<SelectItemModel> oldValue;

			@Override
			public void valueChanged(final List<SelectItemModel> newValue) {
				if (newValue == null || callbackMethod == null) {
					return;
				}

				if (!newValue.equals(oldValue)) {
					oldValue = newValue;

					invokeCallback(getOptions(), callbackMethod);
					callbackMethod = null;
				}

			}
		});

		new PlaceHolderAttributeRenderer(widget, model, TypeaheadRemoteModel_._placeHolder, this);
		new EnabledAttributeRenderer(widget, model, this);
	}

	private void setCallback(final JavaScriptObject callbackMethod) {
		this.callbackMethod = callbackMethod;
	}

	private void updateQuery(final String query) {
		typeaheadRemoteModel.setQuery(query);
	}

	private JsArray<SelectItemModelItem> getOptions() {
		List<SelectItemModel> options = typeaheadRemoteModel.getOptions();
		itemsToModel.clear();

		JsArray<SelectItemModelItem> optionList = JavaScriptObject.createArray().cast();

		int mapIdCounter = 0;
		if (options != null) {
			for (SelectItemModel option : options) {
				SelectItemModelItem optionObject = JavaScriptObject.createObject().cast();

				optionObject.setCaption(option.getCaption());
				optionObject.setId(option.getId());
				optionObject.setMapId(Integer.toString(mapIdCounter++));

				itemsToModel.put(optionObject, option);

				optionList.push(optionObject);
			}
		}

		return optionList;
	}

	private void updater(final SelectItemModelItem item) {
		typeaheadRemoteModel.setValue(itemsToModel.get(item));
	}

	/**
	 * Workaround: http://stackoverflow.com/questions/14901535/bootstrap-typeahead-ajax-result-format-example
	 */

	private native void init(final JQuery widget) /*-{
		var that = this;

		widget.typeahead({
			source : function(query, callback) {
		    	that.@com.doctusoft.dsw.client.gwt.TypeaheadRemoteRenderer::setCallback(Lcom/google/gwt/core/client/JavaScriptObject;)(callback);
		    	that.@com.doctusoft.dsw.client.gwt.TypeaheadRemoteRenderer::updateQuery(Ljava/lang/String;)(query);

				return null;
		    },
		    highlighter : function(item) {
		    	var currentItem = map[item];
		    	return '<div id="' + currentItem.id + '">' + currentItem.caption + '</div>';
		    },
		    updater : function(item) {
		    	var currentItem = map[item];
		    	that.@com.doctusoft.dsw.client.gwt.TypeaheadRemoteRenderer::updater(Lcom/doctusoft/dsw/client/gwt/SelectItemModelItem;)(currentItem);
		    	return currentItem.caption;
		    },
		    matcher : function(item) {
		    	var currentItem = map[item];
           		return ~currentItem.caption.toLowerCase().indexOf(this.query.toLowerCase());
		    },
		    sorter : function (items) {
		      var beginswith = []
		        , caseSensitive = []
		        , caseInsensitive = []
		        , item;

		      while (item = items.shift()) {
		    	var currentItem = map[item];
		        if (!currentItem.caption.toLowerCase().indexOf(this.query.toLowerCase())) beginswith.push(item)
		        else if (~currentItem.caption.indexOf(this.query)) caseSensitive.push(item)
		        else caseInsensitive.push(item)
		      }

		      return beginswith.concat(caseSensitive, caseInsensitive)
		    }

		});
	}-*/;

	private native void invokeCallback(final JsArray<SelectItemModelItem> items, final JavaScriptObject callbackMethod) /*-{
		objects = [];
		map = {};

		items.map(function(item) {
			map[item.mapId] = item;
            objects.push(item.mapId);
		});

		callbackMethod(objects);
	}-*/;

}
