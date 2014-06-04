package com.doctusoft.dsw.mvp.client;

import lombok.Getter;
import lombok.Setter;

import com.google.gwt.user.client.ui.Widget;

public class TotallyDummyEmptyView<T> implements ViewOf<T> {
	
	@Getter
	@Setter
	private T presenter;
	
	public static <T> TotallyDummyEmptyView<T> get() {
		return new TotallyDummyEmptyView<T>();
	}
	
	@Override
	public Widget asWidget() {
		return null;
	}
	
	@Override
	public void viewPresented() {
		// do nothing
	}
	
}