package com.doctusoft.dsw.client.gwt;

import com.doctusoft.bean.ValueChangeListener;
import com.doctusoft.dsw.client.comp.model.BaseComponentModel;
import com.doctusoft.dsw.client.comp.model.BaseComponentModel_;
import com.google.common.base.Objects;
import com.xedge.jquery.client.JQuery;

public class DisabledAttributeRenderer {
	
	public DisabledAttributeRenderer(final JQuery input, BaseComponentModel model) {
		BaseComponentRenderer.addChangeListenerAndApply(BaseComponentModel_._disabled, model, new ValueChangeListener<Boolean>() {
			@Override
			public void valueChanged(Boolean newValue) {
				if (Objects.firstNonNull(newValue, false)) {
					input.attr("disabled", "disabled");
				} else {
					input.removeAttr("disabled");
				}
			}
		});
	}

}
