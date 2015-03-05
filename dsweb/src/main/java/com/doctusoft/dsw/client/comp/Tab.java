package com.doctusoft.dsw.client.comp;

import java.io.Serializable;

import com.doctusoft.ObservableProperty;
import com.doctusoft.bean.ModelObject;
import com.doctusoft.dsw.client.comp.model.BaseComponentModel;

public class Tab implements Serializable, ModelObject {

	@ObservableProperty
	private String title = null;
	
	@ObservableProperty
	private BaseComponentModel content = null;

	@ObservableProperty
	private BaseComponentModel titleComponent = null;

	public Tab() {
		Container panel = new Container();
		content = panel.getComponentModel();
	}
	
	public Tab withTitle(String title) {
		setTitle(title);
		setTitleComponent(null);
		return this;
	}
	
	public Tab withTitleComponent(HasComponentModel titleComponent) {
		setTitleComponent(titleComponent.getComponentModel());
		setTitle(null);
		return this;
	}

	public Tab withContent(HasComponentModel content) {
		setContent(content.getComponentModel());
		return this;
	}
	
}
