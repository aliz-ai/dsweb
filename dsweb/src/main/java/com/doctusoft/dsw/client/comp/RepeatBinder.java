package com.doctusoft.dsw.client.comp;

import lombok.Getter;
import lombok.Setter;

import com.doctusoft.bean.binding.Bindings;
import com.doctusoft.bean.binding.ValueBinding;
import com.doctusoft.bean.binding.observable.ObservableList;
import com.doctusoft.bean.binding.observable.ObservableValueBinding;
import com.doctusoft.dsw.client.util.ListBindingListener;

public abstract class RepeatBinder<T> implements IsComponent {
	
	@com.doctusoft.ObservableProperty @Getter @Setter
	private ObservableList<T> items = new ObservableList<>();
	
	private Container container = new Container();

	public RepeatBinder() {
		new ListBindingListener<T>((ObservableValueBinding) Bindings.obs(this).get(RepeatBinder_._items)) {
			@Override
			public void inserted(ObservableList<T> list, int index, T element) {
				Container newRow = new Container();
				renderItem(element, newRow, index);
				container.getChildren().add(index, newRow);
			}
			@Override
			public void removed(ObservableList<T> list, int index, T element) {
				container.getChildren().remove(index);
			}
		};
	}

	protected abstract void renderItem(T item, Container row, int rowNum);
	
	public RepeatBinder<T> bind(ValueBinding<ObservableList<T>> valueBinding) {
		Bindings.bind(valueBinding, (ValueBinding) Bindings.on(this).get(RepeatBinder_._items));
		return this;
	}
	
	public BaseComponent<?> asComponent() {
		return container;
	}
}
