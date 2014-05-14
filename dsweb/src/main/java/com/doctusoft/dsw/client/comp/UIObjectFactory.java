package com.doctusoft.dsw.client.comp;

import com.doctusoft.bean.ModelObject;
import com.doctusoft.dsw.client.comp.model.ButtonModel;
import com.doctusoft.dsw.client.comp.model.CheckboxModel;
import com.doctusoft.dsw.client.comp.model.ContainerModel;
import com.doctusoft.dsw.client.comp.model.HistoryHandlerModel;
import com.doctusoft.dsw.client.comp.model.InputTextModel;
import com.doctusoft.dsw.client.comp.model.LabelModel;
import com.doctusoft.dsw.client.comp.model.LinkModel;
import com.doctusoft.dsw.client.comp.model.ModalDialogModel;
import com.doctusoft.dsw.client.comp.model.SelectItemModel;
import com.doctusoft.dsw.client.comp.model.SelectModel;
import com.doctusoft.dsw.client.comp.model.TextareaModel;
import com.doctusoft.synchronic.serialization.ObjectModelFactory;

public class UIObjectFactory implements ObjectModelFactory {

	@Override
	public ModelObject createModelObject(String className) {
		if (ButtonModel.class.getName().equals(className))
			return new ButtonModel();
		if (LabelModel.class.getName().equals(className))
			return new LabelModel();
		if (ContainerModel.class.getName().equals(className))
			return new ContainerModel();
		if (InputTextModel.class.getName().equals(className))
			return new InputTextModel();
		if (SelectModel.class.getName().equals(className))
			return new SelectModel();
		if (SelectItemModel.class.getName().equals(className))
			return new SelectItemModel();
		if (LinkModel.class.getName().equals(className))
			return new LinkModel();
		if (HistoryHandlerModel.class.getName().equals(className))
			return new HistoryHandlerModel();
		if (ModalDialogModel.class.getName().equals(className))
			return new ModalDialogModel();
		if (CheckboxModel.class.getName().equals(className))
			return new CheckboxModel();
		if (TextareaModel.class.getName().equals(className))
			return new TextareaModel();
		return null;
	}

}
