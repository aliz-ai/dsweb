package com.doctusoft.dsw.client.gwt;

import com.doctusoft.bean.ValueChangeListener;
import com.doctusoft.dsw.client.comp.model.BaseComponentModel;
import com.doctusoft.dsw.client.comp.model.BaseComponentModel_;
import com.doctusoft.html4j.jquery.JQuery;
import com.google.common.base.Objects;

public class EnabledAttributeRenderer {
	
	public EnabledAttributeRenderer(final JQuery input, BaseComponentModel model) {
		BaseComponentRenderer.addChangeListenerAndApply(BaseComponentModel_._enabled, model, new ValueChangeListener<Boolean>() {
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