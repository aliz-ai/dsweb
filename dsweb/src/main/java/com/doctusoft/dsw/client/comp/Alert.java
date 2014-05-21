package com.doctusoft.dsw.client.comp;

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
		private String name;
		AlertDisplayType(String name) {
			this.name = name;
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
