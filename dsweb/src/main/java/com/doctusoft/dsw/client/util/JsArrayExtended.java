package com.doctusoft.dsw.client.util;

import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.core.client.JsArray;

public class JsArrayExtended<T extends JavaScriptObject> extends JsArray {

	protected JsArrayExtended() {
	}

	public final native void remove(int index) /*-{
		this.splice(index, 1);
	}-*/;

	public final native void add(int index, T element) /*-{
		this.splice(index, 0, element);
	}-*/;

}
