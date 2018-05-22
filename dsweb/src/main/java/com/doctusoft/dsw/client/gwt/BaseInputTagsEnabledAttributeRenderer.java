package com.doctusoft.dsw.client.gwt;

import com.doctusoft.bean.ValueChangeListener;
import com.doctusoft.dsw.client.comp.model.BaseComponentModel;
import com.doctusoft.dsw.client.comp.model.BaseComponentModel_;
import com.xedge.jquery.client.JQuery;

/**
 * Tricky solution with css and DOM:
 * 1. TagsInput API does not support defining/changing disabled property -> find in DOM
 * 2. Add defined styleclass to the tagsinput container
 * 
 */
public class BaseInputTagsEnabledAttributeRenderer {

	public BaseInputTagsEnabledAttributeRenderer(final JQuery widget, final BaseComponentModel model, final BaseComponentRenderer renderer) {
		renderer.addChangeListenerAndApply(BaseComponentModel_._enabled, model, new ValueChangeListener<Boolean>() {
			@Override
			public void valueChanged(final Boolean newValue) {
				if (newValue == null || !newValue) {
					widget.attr("disabled", "disabled");
					widget.next().find("input").attr("disabled", "disabled");

					widget.next().addClass("bootstrap-tagsinput-disabled");
				} else {
					widget.removeAttr("disabled");
					widget.next().find("input").removeAttr("disabled");

					widget.next().removeClass("bootstrap-tagsinput-disabled");
				}
			}
		});
	}

}
