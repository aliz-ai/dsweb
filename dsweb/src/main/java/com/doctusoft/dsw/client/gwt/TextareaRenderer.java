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
import com.doctusoft.dsw.client.comp.model.TextareaModel;
import com.doctusoft.dsw.client.comp.model.TextareaModel_;
import com.xedge.jquery.client.JQEvent;
import com.xedge.jquery.client.JQuery;
import com.xedge.jquery.client.handlers.EventHandler;

public class TextareaRenderer extends BaseComponentRenderer {

	public TextareaRenderer(final TextareaModel model) {
		super(JQuery.select("<textarea/>"), model);
		
		// rows
		addChangeListenerAndApply(TextareaModel_._rows, model, new ValueChangeListener<Integer>() {
			@Override
			public void valueChanged(Integer newValue) {
				if (newValue != null) {
					widget.attr("rows", String.valueOf(newValue));
				} else {
					widget.removeAttr("rows");
				}
			}
		});
		
		// value
		addChangeListenerAndApply(TextareaModel_._value, model, new ValueChangeListener<String>() {
			@Override
			public void valueChanged(final String newValue) {
				widget.val(newValue);
			}
		});
		
		// max length
		new MaxLengthRenderer(widget, model, TextareaModel_._maxLength);
		
		widget.change(new EventHandler() {
			@Override
			public void eventComplete(final JQEvent event, final JQuery currentJQuery) {
				model.setValue(widget.val());
			}
		});

		widget.keyup(new EventHandler() {
			@Override
			public void eventComplete(JQEvent event, JQuery currentJQuery) {
				if (model.getImmediate()){
					model.setValue(widget.val());
				}
			}
		});
		
		new EnabledAttributeRenderer(widget, model);
		new PlaceHolderAttributeRenderer(widget, model, TextareaModel_._placeHolder);
	}

}
