package com.doctusoft.dsw.client.comp;

public class ButtonGroup extends BaseContainer {
	
	public ButtonGroup() {
		addStyleClass("btn-group");
	}
	
	public ButtonGroup addButton(Button button) {
		add(button);
		return this;
	}

}
