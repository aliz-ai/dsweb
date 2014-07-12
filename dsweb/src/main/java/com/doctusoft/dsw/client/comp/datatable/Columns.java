package com.doctusoft.dsw.client.comp.datatable;

import com.doctusoft.bean.ParametricClassMethodReferences.ClassMethodReference1;
import com.doctusoft.bean.Property;
import com.doctusoft.dsw.client.comp.Button;
import com.doctusoft.dsw.client.comp.HasComponentModel;
import com.doctusoft.dsw.client.comp.mvp.ContainerWithPresenter;

public class Columns {
	
	public static <Item, Value> PropertyColumn<Item, Value> from(String title, Property<Item, Value> property) {
		return new PropertyColumn<Item, Value>(title, property);
	}

	public static <Presenter, Item> ComponentColumn<Item> actionButton(final ContainerWithPresenter<Presenter> container,
				final ClassMethodReference1<Presenter, Void, Item> methodRef, final String buttonCaption) {
		return new ComponentColumn<Item>() {
			@Override
			public HasComponentModel getComponent(Item item) {
				return new Button(buttonCaption).click(container.presenterMethod(methodRef, item));
			}
		};
	}
}
