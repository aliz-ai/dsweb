package com.doctusoft.dsw.sample.client.custom;

import com.doctusoft.dsw.client.comp.model.BaseComponentModel;
import com.doctusoft.dsw.client.gwt.Renderer;
import com.doctusoft.dsw.client.gwt.RendererFactoryImpl;
import com.doctusoft.dsw.sample.client.checkbox.CheckboxModel;
import com.doctusoft.dsw.sample.client.checkbox.CheckboxRenderer;
import com.doctusoft.dsw.sample.client.passwordfield.PasswordFieldModel;
import com.doctusoft.dsw.sample.client.passwordfield.PasswordFieldRenderer;

public class CustomRendererFactoryImpl extends RendererFactoryImpl {
	
	@Override
	public Renderer resolveRenderer(BaseComponentModel baseWidget) {
		if (baseWidget instanceof CustomComponentModel)
			return new CustomComponentRenderer((CustomComponentModel) baseWidget);
		if (baseWidget instanceof CheckboxModel)
			return new CheckboxRenderer((CheckboxModel) baseWidget);
		if (baseWidget instanceof PasswordFieldModel)
			return new PasswordFieldRenderer((PasswordFieldModel) baseWidget);
		return super.resolveRenderer(baseWidget);
	}

}
