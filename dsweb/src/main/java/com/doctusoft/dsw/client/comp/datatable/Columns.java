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
import com.doctusoft.bean.binding.EmptyEventHandler;
import com.doctusoft.dsw.client.comp.Button;
import com.doctusoft.dsw.client.comp.HasComponentModel;
import com.doctusoft.dsw.client.comp.mvp.ContainerWithPresenter;
import com.doctusoft.dsw.client.exc.ExceptionReporter;

public class Columns {

	public static <Item, Value> PropertyColumn<Item, Value> from(final String title, final Property<Item, Value> property) {
		return new PropertyColumn<Item, Value>(title, property);
	}

	public static <Item, Value> ObservablePropertyColumn<Item, Value> obs(final String title, final ObservableProperty<Item, Value> property) {
		return new ObservablePropertyColumn<Item, Value>(title, property);
	}

	public static <Presenter, Item> ComponentColumn<Item> actionButton(final ContainerWithPresenter<Presenter> container,
			final ClassMethodReference1<? super Presenter, Void, Item> methodRef, final String buttonCaption) {
		return new ComponentColumn<Item>() {
			@Override
			public HasComponentModel getComponent(final Item item) {
				return new Button(buttonCaption).click(container.presenterMethod(methodRef, item));
			}
		};
	}

	private static <Presenter, Item> ComponentColumn<Item> actionButton(
			final Presenter presenter,
			final ClassMethodReference1<? super Presenter, Void, Item> methodRef,
			final String buttonCaption) {
		return new ComponentColumn<Item>() {
			@Override
			public HasComponentModel getComponent(final Item item) {
				return new Button(buttonCaption).click(new EmptyEventHandler() {

					@Override
					public void handle() {
						try {
							methodRef.apply(presenter, item);
						} catch (RuntimeException e) {
							if (presenter instanceof ExceptionReporter) {
								((ExceptionReporter) presenter).reportException(e);
							} else {
								throw e;
							}
						}
					}
				});
			}
		};
	}

	public static <Item> Column<Item> from(final ColumnDescriptor<Item> columnDescriptor) {
		if (columnDescriptor instanceof ButtonColumnDescriptor) {
			return from((ButtonColumnDescriptor<?, Item>) columnDescriptor);
		} else if (columnDescriptor instanceof PropertyColumnDescriptor) {
			return from((PropertyColumnDescriptor<Item, ?>) columnDescriptor);
		}

		throw new RuntimeException("Columndescriptor type is not supported: " + columnDescriptor.getClass().getSimpleName());
	}

	public static <Presenter, Item> ComponentColumn<Item> from(final ButtonColumnDescriptor<Presenter, Item> columnDescriptor) {
		return actionButton(columnDescriptor.getPresenter(), columnDescriptor.getMethodRef(), columnDescriptor.getTitle());
	}

	public static <Item, Value> PropertyColumn<Item, Value> from(final PropertyColumnDescriptor<Item, Value> columnDescriptor) {
		if (columnDescriptor.getConverter() != null) {
			return from(columnDescriptor.getTitle(), columnDescriptor.getProperty()).format(columnDescriptor.getConverter());
		}

		return from(columnDescriptor.getTitle(), columnDescriptor.getProperty());
	}

}
