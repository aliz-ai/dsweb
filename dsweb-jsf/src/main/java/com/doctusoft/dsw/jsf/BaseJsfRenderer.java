package com.doctusoft.dsw.jsf;

import javax.faces.component.UIComponent;

import com.doctusoft.bean.binding.ValueBinding;
import com.doctusoft.dsw.client.Renderer;
import com.doctusoft.dsw.client.comp.model.BaseComponentModel;
import com.doctusoft.dsw.jsf.binding.BindingWrapper;

public class BaseJsfRenderer<Component extends UIComponent> implements Renderer<UIComponent> {
	
	private BaseComponentModel model;
	protected Component component;

	public BaseJsfRenderer(Component component, BaseComponentModel model) {
		this.component = component;
		this.model = model;
	}

	@Override
	public UIComponent getWidget() {
		return component;
	}
	
	protected <T> void bind(String property, ValueBinding<T> binding, Class<T> type) {
		component.setValueExpression(property, new BindingWrapper<T>(binding, type));
	}

	/**
	 * Binds String values
	 */
	protected <T> void bind(String property, ValueBinding<String> binding) {
		component.setValueExpression(property, new BindingWrapper<String>(binding, String.class));
	}
}
