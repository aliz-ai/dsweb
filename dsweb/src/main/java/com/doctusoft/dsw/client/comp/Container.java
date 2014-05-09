package com.doctusoft.dsw.client.comp;

import lombok.Getter;

import com.doctusoft.bean.ObservableProperty;
import com.doctusoft.bean.binding.observable.ObservableList;

@Getter
public class Container extends BaseComponent<Container> implements IsContainer {
	
	@Override
	public Iterable<ObservableProperty<?, ?>> getObservableProperties() {
		return Container_._observableProperties;
	}
	
	@com.doctusoft.ObservableProperty
	private ObservableList<BaseComponent<?>> children = new ObservableList<BaseComponent<?>>();
	
	@Override
	public void add(BaseComponent<?> component) {
		children.add(component);
	}

	@Override
	public void add(IsComponent component) {
		children.add(component.asComponent());
	}
}
