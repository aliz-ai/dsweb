package com.doctusoft.dsw.client.comp;

/*
 * #%L
 * dsweb
 * %%
 * Copyright (C) 2014 Doctusoft Ltd.
 * %%
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public
 * License along with this program.  If not, see
 * <http://www.gnu.org/licenses/gpl-3.0.html>.
 * #L%
 */


import java.util.List;

import lombok.Getter;
import lombok.Setter;

import com.doctusoft.ObservableProperty;
import com.doctusoft.bean.binding.Bindings;
import com.doctusoft.bean.binding.ValueBinding;
import com.doctusoft.bean.binding.observable.ListBindingListener;
import com.doctusoft.bean.binding.observable.ObservableList;
import com.doctusoft.bean.binding.observable.ObservableValueBinding;
import com.doctusoft.dsw.client.comp.model.ContainerModel;

public abstract class Repeat<T> extends BaseComponent<Repeat<T>, ContainerModel> {
	
	@ObservableProperty
	@Getter
	@Setter
	private ObservableList<T> items = new ObservableList<T>();
	
	public Repeat() {
		super(new ContainerModel());
		new ListBindingListener<T>((ObservableValueBinding) Bindings.obs(this).get(Repeat_._items)) {
			@Override
			public void inserted(ObservableList<T> list, int index, T element) {
				BaseComponent<?, ?> newRow = renderItem(element, index);
				model.getChildren().add(index, newRow.getComponentModel());
			}
			
			@Override
			public void removed(ObservableList<T> list, int index, T element) {
				model.getChildren().remove(index);
			}
		};
	}
	
	protected abstract BaseComponent<?, ?> renderItem(T item, int rowNum);
	
	/**
	 * If an observablelist is bound, it's insert and remove events will also propagate
	 */
	public Repeat<T> bind(ValueBinding<? extends List<T>> valueBinding) {
		Bindings.bind(valueBinding, (ValueBinding) Bindings.on(this).get(Repeat_._items));
		return this;
	}
	
}
