package com.doctusoft.dsw.client.comp;

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


import lombok.Getter;

import com.doctusoft.dsw.client.comp.model.AlertModel;


public class Alert extends AbstractContainer<Alert, AlertModel> {
	
	private Button closeButton;
	
	public Alert(String description) {
		super(new AlertModel());
		addStyleClass("alert");
		closeButton = new Button("\u00d7");
		closeButton.removeStyleClass("btn");
		closeButton.addStyleClass("close");
		model.setDescription(description);
		add(closeButton);
	}
	
	public Alert(String description, String title) {
		this(description);
		model.setTitle(title);
	}
	
	public Alert setDisplayType(AlertDisplayType alertDisplayType) {
		model.setAlertDisplayType(alertDisplayType);
		return this;
	}
	
	public Alert setAlertType(AlertType alertType) {
		model.setAlertType(alertType);
		return this;
	}
	
	public static enum AlertDisplayType {
		
		OneLine("strong"),
		TwoLine("h4");
		
		@Getter
		private String elementName;
		AlertDisplayType(String elementName) {
			this.elementName = elementName;
		}
	}
	
	public static enum AlertType {
		
		Danger("alert-error"),
		Success("alert-success"),
		Information("alert-info");
		
		@Getter
		private String name;
		AlertType(String name) {
			this.name = name;
		}
	}
	
}
