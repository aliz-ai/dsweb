package com.doctusoft.dsw.servermode.example.client.custom;

import com.doctusoft.bean.ModelObject;
import com.doctusoft.dsw.sample.client.custom.CustomComponentModel;
import com.doctusoft.dsw.sample.client.custom.CustomComponentModel;
import com.doctusoft.dsw.sample.client.custom.CustomComponentModel;
import com.doctusoft.dsw.servermode.client.comp.UIObjectFactory;

public class CustomModelObjectFactory extends UIObjectFactory {

	@Override
	public ModelObject createModelObject(String className) {
		if (CustomComponentModel.class.getName().equals(className))
			return new CustomComponentModel();
		return super.createModelObject(className);
	}

}
