package com.doctusoft.dsw.client.gwt;

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
	
	private Date dta;

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
				setDatepickerValueNative(widget, dateTimeFormat.format(newValue));
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
				model.setValue(dateTimeFormat.parse(widget.val()));
				changedFromWidget = false;
			}
		});
		
		DatepickerModel_._format.addChangeListener(model, new ValueChangeListener<String>() {
			@Override
			public void valueChanged(String newValue) {
				initDatepickerNative(widget, model.getFormat().toLowerCase());
			}
		});
	}

	private native void setDatepickerValueNative(JQuery widget, String value) /*-{
		widget.datepicker("setDate", value);
	}-*/;
	

	private native void initDatepickerNative(JQuery widget, String datepickerFormat) /*-{
		widget.datepicker("remove");
		widget.datepicker({
			autoclose: true,
			format: datepickerFormat
		});
	}-*/;

}
