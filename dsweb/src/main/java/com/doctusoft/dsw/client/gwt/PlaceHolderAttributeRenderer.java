package com.doctusoft.dsw.client.gwt;

import com.doctusoft.bean.ObservableProperty;
import com.doctusoft.bean.ValueChangeListener;
import com.doctusoft.dsw.client.comp.model.BaseComponentModel;
import com.google.common.base.Objects;
import com.xedge.jquery.client.JQuery;

/**
 * Base placeholder renderer
 */
public class PlaceHolderAttributeRenderer {

	public <Model extends BaseComponentModel> PlaceHolderAttributeRenderer(final JQuery input, final Model model, final ObservableProperty<Model, String> property, final BaseComponentRenderer renderer) {
		renderer.addChangeListenerAndApply(property, model, new ValueChangeListener<String>() {

			@Override
			public void valueChanged(final String newValue) {
				input.attr("placeholder", Objects.firstNonNull(newValue, ""));
			}
		});

	}

}
