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

import com.doctusoft.bean.ValueChangeListener;
import com.doctusoft.dsw.client.comp.model.TypeaheadRemoteModel;
import com.doctusoft.dsw.client.comp.model.TypeaheadRemoteModel_;
import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.core.client.JsArrayString;
import com.xedge.jquery.client.JQEvent;
import com.xedge.jquery.client.JQuery;
import com.xedge.jquery.client.handlers.EventHandler;

public class TypeaheadRemoteRenderer extends BaseComponentRenderer {

	private TypeaheadRemoteModel typeaheadRemoteModel;
	private JavaScriptObject callbackMethod;


	public TypeaheadRemoteRenderer(final TypeaheadRemoteModel typeaheadRemoteModel) {
		super(JQuery.select("<input type=\"text\" data-provide=\"typeahead\"/>"), typeaheadRemoteModel);

		this.typeaheadRemoteModel = typeaheadRemoteModel;

		init(widget);

		if (typeaheadRemoteModel.getPlaceHolder() != null) {
			widget.attr("placeholder", typeaheadRemoteModel.getPlaceHolder());
		}

		if (typeaheadRemoteModel.getValue() != null) {
			widget.val(typeaheadRemoteModel.getValue());
		}

		addChangeListenerAndApply(TypeaheadRemoteModel_._placeHolder, typeaheadRemoteModel, new ValueChangeListener<String>() {

			@Override
			public void valueChanged(final String newValue) {
				if (newValue == null) {
					widget.attr("placeholder", "");
				} else {
					widget.attr("placeholder", newValue);
				}
			}
		});

		addChangeListenerAndApply(TypeaheadRemoteModel_._options, typeaheadRemoteModel, new ValueChangeListener<List<String>>() {

			private List<String> oldValue;

			@Override
			public void valueChanged(final List<String> newValue) {
				if(newValue == null || callbackMethod == null) {
					return;
				}

				if(!newValue.equals(oldValue)) {
					oldValue = newValue;

					invokeCallback(getOptions(), callbackMethod);
					callbackMethod = null;
				}

			}
		});

		widget.change(new EventHandler() {
			@Override
			public void eventComplete(final JQEvent event, final JQuery currentJQuery) {
				String widgetVal = widget.val();

				typeaheadRemoteModel.setValue(widgetVal);
			}
		});
	}

	private void setCallback(final JavaScriptObject callbackMethod) {
		this.callbackMethod = callbackMethod;
	}

	private void updateQuery(final String query) {
		typeaheadRemoteModel.setQuery(query);
	}

	private JsArrayString getOptions() {
		List<String> options = typeaheadRemoteModel.getOptions();

		JsArrayString optionList = JavaScriptObject.createArray().cast();

		if(options != null) {

			for(String option : options) {
				optionList.push(option);
			}

			return optionList;
		}

		return optionList;
	}

	private native void init(final JQuery widget) /*-{
		var that = this;
		widget.typeahead({
			source : function(query, callback) {
		    	that.@com.doctusoft.dsw.client.gwt.TypeaheadRemoteRenderer::setCallback(Lcom/google/gwt/core/client/JavaScriptObject;)(callback);
		    	that.@com.doctusoft.dsw.client.gwt.TypeaheadRemoteRenderer::updateQuery(Ljava/lang/String;)(query);

				return null;
		    }

		});
	}-*/;

	private native void invokeCallback(final JsArrayString items, final JavaScriptObject callbackMethod) /*-{
		callbackMethod(items);
	}-*/;

}
