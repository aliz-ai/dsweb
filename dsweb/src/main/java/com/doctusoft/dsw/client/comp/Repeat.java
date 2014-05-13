package com.doctusoft.dsw.client.comp;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

import com.doctusoft.bean.binding.Bindings;
import com.doctusoft.bean.binding.ValueBinding;
import com.doctusoft.bean.binding.observable.ObservableList;
import com.doctusoft.bean.binding.observable.ObservableValueBinding;
import com.doctusoft.dsw.client.comp.model.ContainerModel;
import com.doctusoft.dsw.client.util.ListBindingListener;

public abstract class Repeat<T> extends BaseComponent<Repeat<T>, ContainerModel> {
	
	@com.doctusoft.ObservableProperty @Getter @Setter
	private ObservableList<T> items = new ObservableList<>();
	
	public Repeat() {
		super(new ContainerModel());
		new ListBindingListener<T>((ObservableValueBinding) Bindings.obs(this).get(Repeat_._items)) {
			@Override
			public void inserted(ObservableList<T> list, int index, T element) {
				Container newRow = new Container();
				renderItem(element, newRow, index);
				model.getChildren().add(index, newRow.getComponentModel());
			}
			@Override
			public void removed(ObservableList<T> list, int index, T element) {
				model.getChildren().remove(index);
			}
		};
	}

	protected abstract void renderItem(T item, Container row, int rowNum);
	
	/**
	 * If an observablelist is bound, it's insert and remove events will also propagate 
	 */
	public Repeat<T> bind(ValueBinding<? extends List<T>> valueBinding) {
		Bindings.bind(valueBinding, (ValueBinding) Bindings.on(this).get(Repeat_._items));
		return this;
	}

}
