package com.doctusoft.dsw.client.gwt;

import com.doctusoft.bean.ValueChangeListener;
import com.doctusoft.dsw.client.comp.model.BaseComponentModel;
import com.doctusoft.dsw.client.comp.model.BaseComponentModel_;
import com.google.common.base.Objects;
import com.xedge.jquery.client.JQuery;

public class EnabledAttributeRenderer {
	
	public EnabledAttributeRenderer(final JQuery input, final BaseComponentModel model, final BaseComponentRenderer renderer) {
		renderer.addChangeListenerAndApply(BaseComponentModel_._enabled, model, new ValueChangeListener<Boolean>() {
			@Override
			public void valueChanged(Boolean newValue) {
				// null value defaults to true here
				if (!Objects.firstNonNull(newValue, true)) {
					input.attr("disabled", "disabled");
				} else {
					input.removeAttr("disabled");
				}
			}
		});
	}

}
