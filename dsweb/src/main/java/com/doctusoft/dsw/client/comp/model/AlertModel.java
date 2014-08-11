package com.doctusoft.dsw.client.comp.model;

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
