package com.doctusoft.dsw.jsf;

import javax.faces.component.UIComponent;

import com.doctusoft.dsw.client.Renderer;
import com.doctusoft.dsw.client.comp.model.BaseComponentModel;

public class BaseJsfRenderer implements Renderer<UIComponent> {
	
	private BaseComponentModel model;

	public BaseJsfRenderer(Class<? extends UIComponent> cls, BaseComponentModel model) {
		this.model = model;
	}

	@Override
	public UIComponent getWidget() {
		return null;
	}
}
