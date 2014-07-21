package com.doctusoft.dsw.client.gwt;

import com.doctusoft.bean.ValueChangeListener;
import com.doctusoft.dsw.client.comp.model.DatepickerModel;
import com.doctusoft.dsw.client.comp.model.DatepickerModel_;
import com.xedge.jquery.client.JQEvent;
import com.xedge.jquery.client.JQuery;
import com.xedge.jquery.client.handlers.EventHandler;

public class DatepickerRenderer extends BaseComponentRenderer {

	public DatepickerRenderer(final DatepickerModel model) {
		super(JQuery.select("<input type=\"text\" />"), model);
		initDatepickerNative(widget, model.getFormat());
		setDatepickerValueNative(widget, model.getValue());
		
		DatepickerModel_._value.addChangeListener(model, new ValueChangeListener<String>() {
			@Override
			public void valueChanged(String newValue) {
				if (!widget.val().equals(newValue)) {
					setDatepickerValueNative(widget, newValue);				
				}
			}
		});
		
		widget.change(new EventHandler() {
			@Override
			public void eventComplete(JQEvent event, JQuery currentJQuery) {
				model.setValue(widget.val());				
			}
		});
		
		DatepickerModel_._format.addChangeListener(model, new ValueChangeListener<String>() {
			@Override
			public void valueChanged(String newValue) {
				initDatepickerNative(widget, model.getFormat());
			}
		});
	}
	
	private native void setDatepickerValueNative(JQuery widget, String value) /*-{
		widget.datepicker("setDate", value);
	}-*/;
	

	private native void initDatepickerNative(JQuery widget, String format) /*-{
		widget.datepicker("remove");
		widget.datepicker({
			autoclose: true,
			format: format
		});
	}-*/;

}
