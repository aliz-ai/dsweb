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
import com.doctusoft.dsw.client.comp.Alert.AlertType;
import com.doctusoft.dsw.client.comp.model.AlertModel;
import com.doctusoft.dsw.client.comp.model.AlertModel_;
import com.xedge.jquery.client.JQuery;

public class AlertRenderer extends ContainerRenderer {
	
	private AlertType lastAlertType;

	public AlertRenderer(AlertModel alert) {
		super(alert);
		final JQuery title = JQuery.select("<" + alert.getAlertDisplayType().getElementName() + "/>").text(alert.getTitle());
		lastAlertType = alert.getAlertType();
		widget.append(title);
		widget.append(" <span>" + alert.getDescription() + "</span>");
		
		if (alert.getAlertType() != null) {
			widget.addClass(alert.getAlertType().getName());
		}

		Bindings.obs(alert).get(AlertModel_._alertType).addValueChangeListener(new ValueChangeListener<AlertType>() {
			@Override
			public void valueChanged(AlertType newValue) {
				if (lastAlertType != null) {
					widget.removeClass(lastAlertType.getName());
				}
				widget.addClass(newValue.getName());
			}
		});
		
		Bindings.obs(alert).get(AlertModel_._title).addValueChangeListener(new ValueChangeListener<String>() {
			@Override
			public void valueChanged(String newValue) {
				title.text(newValue);
			}
		});
		
		Bindings.obs(alert).get(AlertModel_._description).addValueChangeListener(new ValueChangeListener<String>() {
			@Override
			public void valueChanged(String newValue) {
				widget.find( "span" ).text( newValue );
			}
		});
		widget.find(".close").attr("data-dismiss", "alert");
	}
	
}
