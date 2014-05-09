package com.doctusoft.dsw.client.comp;

import lombok.Getter;
import lombok.Setter;

import com.doctusoft.bean.binding.Bindings;
import com.doctusoft.bean.binding.ValueBinding;
import com.doctusoft.bean.binding.observable.ObservableList;
import com.doctusoft.bean.binding.observable.ObservableValueBinding;
import com.doctusoft.dsw.client.comp.model.BaseComponentModel;
import com.doctusoft.dsw.client.util.ListBindingListener;

public abstract class Repeat<T> implements HasComponentModel {
	
	@com.doctusoft.ObservableProperty @Getter @Setter
	private ObservableList<T> items = new ObservableList<>();
	
	private Container container = new Container();

	public Repeat() {
		new ListBindingListener<T>((ObservableValueBinding) Bindings.obs(this).get(Repeat_._items)) {
			@Override
			public void inserted(ObservableList<T> list, int index, T element) {
				Container newRow = new Container();
				renderItem(element, newRow, index);
				container.getModel().getChildren().add(index, newRow.getComponentModel());
			}
			@Override
			public void removed(ObservableList<T> list, int index, T element) {
				container.getModel().getChildren().remove(index);
			}
		};
	}

	protected abstract void renderItem(T item, Container row, int rowNum);
	
	public Repeat<T> bind(ValueBinding<ObservableList<T>> valueBinding) {
		Bindings.bind(valueBinding, (ValueBinding) Bindings.on(this).get(Repeat_._items));
		return this;
	}
	
	@Override
	public BaseComponentModel getComponentModel() {
		return container.getComponentModel();
	}
}
