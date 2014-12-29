package com.doctusoft.dsw.client.gwt;

import com.doctusoft.bean.ValueChangeListener;
import com.doctusoft.bean.binding.Bindings;
import com.doctusoft.dsw.client.comp.model.OnbeforeunloadModel;
import com.doctusoft.dsw.client.comp.model.OnbeforeunloadModel_;
import com.xedge.jquery.client.JQuery;

public class OnbeforeunloadRenderer extends BaseComponentRenderer{

	public OnbeforeunloadRenderer(final OnbeforeunloadModel model) {
		super(JQuery.select("<span/>"), model);

		Bindings.obs(model).get(OnbeforeunloadModel_._onbeforeunloadMessage).addValueChangeListener(new ValueChangeListener<String>() {

			@Override
			public void valueChanged(final String newValue) {
				if (newValue == null || newValue.isEmpty()) {
					unbindOnbeforeunload();
				} else {
					bindOnbeforeunload(newValue);
				}
			}

		});
	}

	/**
	 * Not all of the browsers' version support a custom message
	 * Firefox 4 and later - https://bugzilla.mozilla.org/show_bug.cgi?id=588292
	 */

	private static native void bindOnbeforeunload(final String confirmationMessage)/*-{
		window.onbeforeunload = function() {
			return confirmationMessage;
		};
	}-*/;

	private static native void unbindOnbeforeunload()/*-{
		window.onbeforeunload = function() {
		};
	}-*/;

}
