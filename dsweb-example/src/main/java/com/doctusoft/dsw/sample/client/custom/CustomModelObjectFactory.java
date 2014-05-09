package com.doctusoft.dsw.sample.client.custom;

import com.doctusoft.bean.ModelObject;
import com.doctusoft.dsw.client.comp.UIObjectFactory;

public class CustomModelObjectFactory extends UIObjectFactory {

	@Override
	public ModelObject createModelObject(String className) {
		if (CustomComponentModel.class.getName().equals(className))
			return new CustomComponentModel();
		return super.createModelObject(className);
	}

}
