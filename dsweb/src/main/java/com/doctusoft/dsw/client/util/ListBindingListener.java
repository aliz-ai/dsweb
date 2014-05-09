package com.doctusoft.dsw.client.util;

import com.doctusoft.bean.ListenerRegistration;
import com.doctusoft.bean.ValueChangeListener;
import com.doctusoft.bean.binding.observable.ObservableList;
import com.doctusoft.bean.binding.observable.ObservableList.ListElementInsertedListener;
import com.doctusoft.bean.binding.observable.ObservableList.ListElementRemovedListener;
import com.doctusoft.bean.binding.observable.ObservableValueBinding;

/**
 * Normally delegates inserted and removed events to the methods you implement, but if the entire list instance changes
 * it translates the change to series of inserted and removed events 
 */
public abstract class ListBindingListener<T> {
	
	private ListenerRegistration insertListener;
	private ListenerRegistration deleteListener;
	
	private ObservableList<T> shadowList = new ObservableList<T>();
	
	public ListBindingListener(ObservableValueBinding<ObservableList<T>> listBinding) {
		ObservableList<T> items = listBinding.getValue();
		if (items != null) {
			valueChanged(items);
		}
		listBinding.addValueChangeListener(new ValueChangeListener<ObservableList<T>>() {
			@Override
			public void valueChanged(ObservableList<T> newValue) {
				ListBindingListener.this.valueChanged(newValue);
			}
		});
	}
	
	protected void valueChanged(ObservableList<T> items) {
		while (shadowList.size() > 0) {
			removed(shadowList, 0, shadowList.remove(0));
		}
		if (items != null) {
			for (int i = 0; i < items.size(); i ++) {
				T element = items.get(i);
				shadowList.add(element);
				inserted(shadowList, i, element);
			}
		}
		bindListListeners(items);
	}

	protected void bindListListeners(ObservableList<T> items) {
		if (insertListener != null) {
			insertListener.removeHandler();
		}
		if (deleteListener != null) {
			deleteListener.removeHandler();
		}
		if (items != null) {
			// TODO make this reusable in ds-bean-binding
			insertListener = items.addInsertListener(new ListElementInsertedListener<T>() {
				@Override
				public void inserted(ObservableList<T> list, int index, T element) {
					shadowList.add(index, element);
					ListBindingListener.this.inserted(shadowList, index, element);
				}
			});
			deleteListener = items.addDeleteListener(new ListElementRemovedListener<T>() {
				@Override
				public void removed(ObservableList<T> list, int index, T element) {
					shadowList.remove(index);
					ListBindingListener.this.removed(shadowList, index, element);
				}
			});
		}
	}

	public abstract void inserted(ObservableList<T> list, int index, T element);
	
	public abstract void removed(ObservableList<T> list, int index, T element);


}
