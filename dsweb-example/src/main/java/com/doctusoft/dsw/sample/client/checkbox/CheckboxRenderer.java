package com.doctusoft.dsw.sample.client.checkbox;

import com.doctusoft.bean.ValueChangeListener;
import com.doctusoft.dsw.client.gwt.BaseComponentRenderer;
import com.doctusoft.dsw.client.util.Booleans;
import com.xedge.jquery.client.JQuery;

public class CheckboxRenderer extends BaseComponentRenderer {
	
	public CheckboxRenderer(CheckboxModel model) {
		super(JQuery.select("<label class=\"checkbox\">"), model);
		final JQuery input = JQuery.select("<input type=\"checkbox\">");
		input.appendTo(widget);
		input.after(model.getLabel());
		CheckboxModel_._checked.addChangeListener(model, new ValueChangeListener<Boolean>() {
			@Override
			public void valueChanged(Boolean newValue) {
				input.attr("checked", Booleans.toString(newValue));
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
