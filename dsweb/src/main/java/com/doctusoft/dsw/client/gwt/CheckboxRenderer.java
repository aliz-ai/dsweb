package com.doctusoft.dsw.client.gwt;

import com.doctusoft.bean.ValueChangeListener;
import com.doctusoft.dsw.client.comp.model.CheckboxModel;
import com.doctusoft.dsw.client.comp.model.CheckboxModel_;
import com.xedge.jquery.client.JQEvent;
import com.xedge.jquery.client.JQuery;
import com.xedge.jquery.client.handlers.EventHandler;

public class CheckboxRenderer extends BaseComponentRenderer {
	
	public static native void setCheckedNative(JQuery checkbox, boolean isChecked) /*-{
		checkbox.prop('checked', isChecked);
	}-*/;
	
	public CheckboxRenderer(final CheckboxModel model) {
		super(JQuery.select("<label class=\"checkbox\">"), model);
		final JQuery input = JQuery.select("<input type=\"checkbox\">");
		
		input.appendTo(widget);
		input.after(model.getLabel());
		setCheckedNative(input, model.getChecked().booleanValue());
		
		CheckboxModel_._checked.addChangeListener(model, new ValueChangeListener<Boolean>() {
			@Override
			public void valueChanged(Boolean newValue) {
				setCheckedNative(input, newValue.booleanValue());
			}
		});
		
		input.change(new EventHandler() {
			@Override
			public void eventComplete(JQEvent event, JQuery currentJQuery) {
				model.setChecked(input.is(":checked"));
			}
		});
		
		CheckboxModel_._label.addChangeListener(model, new ValueChangeListener<String>() {
			@Override
			public void valueChanged(String newValue) {
				input.after(newValue);
			}
		});
	}

}
