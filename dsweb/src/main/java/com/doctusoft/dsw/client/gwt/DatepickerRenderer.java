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


import java.util.Date;

import com.doctusoft.bean.ValueChangeListener;
import com.doctusoft.dsw.client.comp.model.DatepickerModel;
import com.doctusoft.dsw.client.comp.model.DatepickerModel_;
import com.google.gwt.i18n.shared.DateTimeFormat;
import com.google.gwt.i18n.shared.DefaultDateTimeFormatInfo;
import com.xedge.jquery.client.JQEvent;
import com.xedge.jquery.client.JQuery;
import com.xedge.jquery.client.handlers.EventHandler;

public class DatepickerRenderer extends BaseComponentRenderer {
	
	private boolean changedFromModel = false;
	
	private boolean changedFromWidget = false;
	
	DefaultDateTimeFormatInfo info = new DefaultDateTimeFormatInfo();
	
	DateTimeFormat dateTimeFormat;
	
	public DatepickerRenderer(final DatepickerModel model) {
		super(JQuery.select("<input type=\"text\" />"), model);
		dateTimeFormat = new DateTimeFormat(model.getFormat(), info){};
		
		initDatepickerNative(widget, model.getFormat().toLowerCase());
		
		if(model.getValue() != null) {
			setDatepickerValueNative(widget, dateTimeFormat.format(model.getValue()));
		} else {
			setDatepickerValueNative(widget, "");
		}
			
		DatepickerModel_._value.addChangeListener(model, new ValueChangeListener<Date>() {
			
			@Override
			public void valueChanged(Date newValue) {
				if (changedFromWidget) {
					return;
				}
				changedFromModel = true;
				if (newValue != null) {
					setDatepickerValueNative(widget, dateTimeFormat.format(newValue));
				} else {
					setDatepickerValueNative(widget, "");
				} 
				changedFromModel = false;
			}
		});
		
		widget.change(new EventHandler() {

			@Override
			public void eventComplete(JQEvent event, JQuery currentJQuery) {
				if (changedFromModel) {
					return;
				}
				changedFromWidget = true;
				String dateValue = widget.val();
				if (!dateValue.equals("")) {
					model.setValue(dateTimeFormat.parse(dateValue));
				} else {
					model.setValue(null);
				}
				changedFromWidget = false;
			}
		});
		
		DatepickerModel_._format.addChangeListener(model, new ValueChangeListener<String>() {
			@Override
			public void valueChanged(String newValue) {
				destroyDatepickerNative(widget);
				initDatepickerNative(widget, model.getFormat().toLowerCase());
			}
		});
	}

	private native void setDatepickerValueNative(JQuery widget, String value) /*-{
		widget.datepicker("setDate", value);
	}-*/;
	

	private native void destroyDatepickerNative(JQuery widget) /*-{
		widget.datepicker("remove");
	}-*/;
	
	private native void initDatepickerNative(JQuery widget, String datepickerFormat) /*-{
		widget.datepicker({
			clearBtn: true,
			autoclose: true,
			format: datepickerFormat
		});
	}-*/;

}
