package com.doctusoft.dsw.client.comp.datatable;

import lombok.Getter;

import com.doctusoft.bean.ParametricClassMethodReferences.ClassMethodReference1;

@Getter
public class ButtonColumnDescriptor<Presenter, Item> extends ColumnDescriptor<Item> {

	private Presenter presenter;
	private ClassMethodReference1<? super Presenter, Void, Item> methodRef;

	public ButtonColumnDescriptor(
			final String title,
			final Presenter presenter,
			final ClassMethodReference1<? super Presenter, Void, Item> methodRef) {
		super(title);

		this.presenter = presenter;
		this.methodRef = methodRef;
	}

}
