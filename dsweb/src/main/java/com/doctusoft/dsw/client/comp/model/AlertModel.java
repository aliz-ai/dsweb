package com.doctusoft.dsw.client.comp.model;

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


import lombok.Setter;

import com.doctusoft.ObservableProperty;
import com.doctusoft.bean.ModelObject;
import com.doctusoft.dsw.client.comp.Alert.AlertDisplayType;
import com.doctusoft.dsw.client.comp.Alert.AlertType;


public class AlertModel extends ContainerModel implements ModelObject {
	
	@ObservableProperty
	private String title = "";
	
	@ObservableProperty
	private String description;
	
	@Setter
	private AlertDisplayType alertDisplayType = AlertDisplayType.OneLine;
	
	@Setter
	private AlertType alertType;
	
	public String getAlertDisplayType() {
		return alertDisplayType.getName();
	}
	
	public String getAlertType() {
		if (alertType != null) {
			return alertType.getName();
		}
		return null;
	}

}
