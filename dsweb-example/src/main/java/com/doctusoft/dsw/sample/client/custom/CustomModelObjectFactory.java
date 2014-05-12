package com.doctusoft.dsw.sample.client.custom;

import com.doctusoft.bean.ModelObject;
import com.doctusoft.dsw.client.comp.UIObjectFactory;
import com.doctusoft.dsw.sample.client.checkbox.CheckboxModel;
import com.doctusoft.dsw.sample.client.passwordfield.PasswordFieldModel;

public class CustomModelObjectFactory extends UIObjectFactory {

	@Override
	public ModelObject createModelObject(String className) {
		if (CustomComponentModel.class.getName().equals(className))
			return new CustomComponentModel();
		if (CheckboxModel.class.getName().equals(className))
			return new CheckboxModel();
		if (PasswordFieldModel.class.getName().equals(className))
			return new PasswordFieldModel();
		return super.createModelObject(className);
	}

}
