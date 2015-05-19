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


import com.doctusoft.bean.ValueChangeListener;
import com.doctusoft.dsw.client.comp.model.InputTextModel;
import com.doctusoft.dsw.client.comp.model.InputTextModel_;
import com.google.common.base.Objects;
import com.xedge.jquery.client.JQEvent;
import com.xedge.jquery.client.JQuery;
import com.xedge.jquery.client.handlers.EventHandler;

public class InputTextRenderer extends BaseComponentRenderer {

	public InputTextRenderer(final InputTextModel model) {
		super(JQuery.select("<input/>"), model);
		
		// value
		addChangeListenerAndApply(InputTextModel_._value, model, new ValueChangeListener<String>() {
				@Override
				public void valueChanged(final String newValue) {
					String allowedValue = getAllowedValue(newValue, model); 
					if (newValue.compareToIgnoreCase(allowedValue)!=0){
						model.setValue(allowedValue);
					} else {
						widget.val(allowedValue);
					}
				}
			});

		// type
		addChangeListenerAndApply(InputTextModel_._inputType, model, new ValueChangeListener<String>() {
				@Override
				public void valueChanged(final String newValue) {
					widget.attr("type", Objects.firstNonNull(newValue, "text"));
				}
			});
		
		// max length
		new MaxLengthRenderer(widget, model, InputTextModel_._maxLength);
		
		widget.change(new EventHandler() {
			@Override
			public void eventComplete(final JQEvent event, final JQuery currentJQuery) {
				model.setValue(getAllowedValue(widget.val(), model));
			}
		});

		widget.keyup(new EventHandler() {
			@Override
			public void eventComplete(JQEvent event, JQuery currentJQuery) {
				if (model.getImmediate()) {
					model.setValue(widget.val());
				}
			}
		});
		
		new EnabledAttributeRenderer(widget, model);
		new PlaceHolderAttributeRenderer(widget, model, InputTextModel_._placeHolder);
	}
	
	private static String getAllowedValue(final String newValue, InputTextModel model){
		String retValue = newValue;
		if (model.getMaxLength() > 0) {
			int min = Math.min(model.getMaxLength(), newValue.length());
			retValue = newValue.substring(0, min);
		} 
		return retValue;
	}
	
}
