package com.doctusoft.dsw.client.comp;

import com.doctusoft.bean.binding.observable.ObservableList;
import com.doctusoft.dsw.client.comp.model.AbstractContainerModel;
import com.doctusoft.dsw.client.comp.model.BaseComponentModel;

public abstract class AbstractContainer<Actual, Model extends AbstractContainerModel<? extends BaseComponentModel>>
		extends BaseComponent<Actual, Model> implements IsContainer {
	
	public AbstractContainer(Model model) {
		super(model);
	}
	
	@Override
	public Actual add(HasComponentModel component) {
		addWithWildCardCapture(model.getChildren(), component.getComponentModel());
		return (Actual) this;
	}
	
	private <T> void addWithWildCardCapture(ObservableList<T> list, BaseComponentModel model) {
		list.add((T) model);
	}
	
}
