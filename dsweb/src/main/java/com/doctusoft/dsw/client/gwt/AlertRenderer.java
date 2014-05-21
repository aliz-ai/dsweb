package com.doctusoft.dsw.client.gwt;

import com.doctusoft.bean.ValueChangeListener;
import com.doctusoft.bean.binding.Bindings;
import com.doctusoft.dsw.client.comp.model.AlertModel;
import com.doctusoft.dsw.client.comp.model.AlertModel_;
import com.xedge.jquery.client.JQuery;

public class AlertRenderer extends ContainerRenderer {
	
	public AlertRenderer(AlertModel alert) {
		super(alert);
		final JQuery title = JQuery.select("<" + alert.getAlertDisplayType() + ">" + alert.getTitle() + "</" + alert.getAlertDisplayType() +  ">");
		widget.append(title);
		widget.append(" " + alert.getDescription());
		
		if (alert.getAlertType() != null) {
			widget.addClass(alert.getAlertType());
		}
		
		Bindings.obs(alert).get(AlertModel_._title).addValueChangeListener(new ValueChangeListener<String>() {
			@Override
			public void valueChanged(String newValue) {
				title.text(newValue);
			}
		});
		
		Bindings.obs(alert).get(AlertModel_._description).addValueChangeListener(new ValueChangeListener<String>() {
			@Override
			public void valueChanged(String newValue) {
				widget.clone(false).children().remove().end().text(newValue);
			}
		});
		JQuery.select(widget.children().get(0)).attr("data-dismiss", "alert");
	}
	
}
