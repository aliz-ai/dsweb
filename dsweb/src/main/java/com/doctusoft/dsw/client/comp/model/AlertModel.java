package com.doctusoft.dsw.client.comp.model;

import lombok.Setter;

import com.doctusoft.ObservableProperty;
import com.doctusoft.dsw.client.comp.Alert.AlertDisplayType;
import com.doctusoft.dsw.client.comp.Alert.AlertType;


public class AlertModel extends ContainerModel {
	
	@ObservableProperty
	private String title = "Warning!";
	
	@ObservableProperty
	private String description;
	
	@Setter
	private AlertDisplayType alertDisplayType = AlertDisplayType.OneLine;
	
	@Setter
	private AlertType alertType;
	
	@Override
	public Iterable<com.doctusoft.bean.ObservableProperty<?, ?>> getObservableProperties() {
		return AlertModel_._observableProperties;
	}
	
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
