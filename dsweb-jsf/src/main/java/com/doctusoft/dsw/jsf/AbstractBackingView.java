package com.doctusoft.dsw.jsf;

import javax.faces.component.UIComponent;

import com.doctusoft.bean.binding.ValueBindingBuilder;
import com.doctusoft.dsw.client.comp.Container;
import com.doctusoft.dsw.jsf.binding.JsfBindings;


public class AbstractBackingView<Backing> {
	
	private Class<Backing> backingClass;
	private String backingName;
	
	protected Container container = new Container();

	public AbstractBackingView(Class<Backing> backingClass, String backingName) {
		this.backingClass = backingClass;
		this.backingName = backingName;
	}
	
	public UIComponent getComponent() {
		// TODO: resolve customized rendererfactory for the case the developer wants to customize container rendering
		return new JsfContainerRenderer(container.getModel()).getWidget();
	}
	
	public ValueBindingBuilder<Backing> bindOnPresenter() {
		return JsfBindings.on(backingClass, backingName);
	}

}
