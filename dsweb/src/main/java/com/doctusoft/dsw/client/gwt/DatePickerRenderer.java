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
import com.doctusoft.dsw.client.comp.model.DatePickerModel;
import com.doctusoft.dsw.client.comp.model.DatePickerModel_;
import com.google.common.base.Strings;
import com.xedge.jquery.client.JQEvent;
import com.xedge.jquery.client.JQuery;
import com.xedge.jquery.client.handlers.EventHandler;

public class DatePickerRenderer extends BaseComponentRenderer {

	private boolean changedFromModel = false;

	private boolean changedFromWidget = false;

	private DatePickerModel model;

	public DatePickerRenderer(final DatePickerModel model) {
		super(JQuery.select("<input type=\"text\" placeholder=\"" + model.getPlaceHolder() + "\"/>"), model);
		this.model = model;

		initDatepickerNative(widget, model.getFormat());

		addChangeListenerAndApply(DatePickerModel_._value, model, new ValueChangeListener<Date>() {

			@Override
			public void valueChanged(Date newValue) {
				if (changedFromWidget) {
					return;
				}
				applyValue();
			}
		});

		DatePickerModel_._placeHolder.addChangeListener(model, new ValueChangeListener<String>() {

			@Override
			public void valueChanged(String placeHolder) {
				widget.attr("placeholder", placeHolder);
			}
		});

		widget.change(new EventHandler() {

			@Override
			public void eventComplete(JQEvent event, JQuery currentJQuery) {
				if (changedFromModel) {
					return;
				}
				changedFromWidget = true;
				String textValue = widget.val();
				if (Strings.isNullOrEmpty(textValue)) {	// if we click the 'clear' button on the widget, the previous date would still be returned by getDate
					model.setValue(null);
				} else {
					long time = (long) getDatepickerValueNative(widget);
					model.setValue(new Date(time));
				}
				changedFromWidget = false;
			}
		});

		DatePickerModel_._format.addChangeListener(model, new ValueChangeListener<String>() {
			@Override
			public void valueChanged(String newValue) {
				destroyDatepickerNative(widget);
				initDatepickerNative(widget, model.getFormat());
				applyValue();
			}
		});
		new EnabledAttributeRenderer(widget, model);
	}
	
	protected void applyValue() {
		changedFromModel = true;
		if (model.getValue() != null) {
			setDatepickerValueNative(widget, model.getValue().getTime(), model.getFormat());
		} else {
			setDatepickerValueNative(widget, -1, model.getFormat());
		}
		changedFromModel = false;
	}

	/*
	 * Note, passing a native javascript date object as the update method parameter does not work.
	 * The parseDate method in bootstrap-datepicker.js will fail when checking 'date instanceof Date',
	 * see http://stackoverflow.com/questions/643782/how-to-check-whether-an-object-is-a-date.
	 * This formatting hack works if we don't want to modify boostrap-datepicker.js
	 */
	private native void setDatepickerValueNative(JQuery widget, double value, String format) /*-{
		if (value == -1) {
			widget.datepicker("update", "");
		} else {
			var format = $wnd.$.prototype.datepicker.DPGlobal.parseFormat(format);
			var str = $wnd.$.prototype.datepicker.DPGlobal.formatDate(new Date(value), format, "en");
			widget.datepicker("update", str);
		}
	}-*/;

	// only double can be returned from javascript, see gwt jsni
	private native double getDatepickerValueNative(JQuery widget) /*-{
		var date = widget.datepicker("getDate");
		return date.getTime();
	}-*/;


	private native void destroyDatepickerNative(JQuery widget) /*-{
		widget.datepicker("remove");
	}-*/;

	private native void initDatepickerNative(JQuery widget, String datepickerFormat) /*-{
		widget.datepicker({
			clearBtn : true,
			autoclose : true,
			format : datepickerFormat
		});
	}-*/;

}
