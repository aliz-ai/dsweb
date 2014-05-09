package com.doctusoft.dsw.client.comp;

import com.doctusoft.bean.ModelObject;
import com.doctusoft.synchronic.serialization.ObjectModelFactory;

public class UIObjectFactory implements ObjectModelFactory {

	@Override
	public ModelObject createModelObject(String className) {
		if (Button.class.getName().equals(className))
			return new Button();
		if (Label.class.getName().equals(className))
			return new Label();
		if (Container.class.getName().equals(className))
			return new Container();
		if (InputText.class.getName().equals(className))
			return new InputText();
		if (Select.class.getName().equals(className))
			return new Select();
		if (SelectItemModel.class.getName().equals(className))
			return new SelectItemModel();
		if (Link.class.getName().equals(className))
			return new Link();
		if (HistoryHandler.class.getName().equals(className))
			return new HistoryHandler();
		if (ModalDialog.class.getName().equals(className))
			return new ModalDialog();
		return null;
	}

}
