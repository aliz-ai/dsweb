package com.doctusoft.dsw.client.comp.datatable;

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


import com.doctusoft.bean.ObservableProperty;
import com.doctusoft.bean.ParametricClassMethodReferences.ClassMethodReference1;
import com.doctusoft.bean.Property;
import com.doctusoft.dsw.client.comp.Button;
import com.doctusoft.dsw.client.comp.HasComponentModel;
import com.doctusoft.dsw.client.comp.mvp.ContainerWithPresenter;

public class Columns {
	
	public static <Item, Value> PropertyColumn<Item, Value> from(String title, Property<Item, Value> property) {
		return new PropertyColumn<Item, Value>(title, property);
	}

	public static <Item, Value> ObservablePropertyColumn<Item, Value> obs(String title, ObservableProperty<Item, Value> property) {
		return new ObservablePropertyColumn<Item, Value>(title, property);
	}

	public static <Presenter, Item> ComponentColumn<Item> actionButton(final ContainerWithPresenter<Presenter> container,
				final ClassMethodReference1<? super Presenter, Void, Item> methodRef, final String buttonCaption) {
		return new ComponentColumn<Item>() {
			@Override
			public HasComponentModel getComponent(Item item) {
				return new Button(buttonCaption).click(container.presenterMethod(methodRef, item));
			}
		};
	}
}
