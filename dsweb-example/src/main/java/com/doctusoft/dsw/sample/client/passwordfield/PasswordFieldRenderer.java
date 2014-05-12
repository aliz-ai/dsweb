package com.doctusoft.dsw.sample.client.passwordfield;

import com.doctusoft.dsw.client.gwt.BaseComponentRenderer;
import com.xedge.jquery.client.JQuery;

public class PasswordFieldRenderer extends BaseComponentRenderer {

	public PasswordFieldRenderer(PasswordFieldModel model) {
		super(JQuery.select("<input tpye=\"password\" class=\"input-small\">"), model);
		widget.append(model.getLabel());
	}

}
