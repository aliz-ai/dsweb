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
		widget.find(".close").attr("data-dismiss", "alert");
	}
	
}
