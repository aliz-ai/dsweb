package com.doctusoft.dsw.client.gwt;

import com.doctusoft.dsw.client.comp.BaseComponent;
import com.doctusoft.dsw.client.comp.Button;
import com.doctusoft.dsw.client.comp.Container;
import com.doctusoft.dsw.client.comp.HistoryHandler;
import com.doctusoft.dsw.client.comp.InputText;
import com.doctusoft.dsw.client.comp.Label;
import com.doctusoft.dsw.client.comp.Link;
import com.doctusoft.dsw.client.comp.ModalDialog;
import com.doctusoft.dsw.client.comp.Select;

public class RendererFactory {
	
	public static Renderer getRenderer(BaseComponent<?> baseWidget) {
		if (baseWidget instanceof Label) {
			return new LabelRenderer((Label) baseWidget);
		}
		if (baseWidget instanceof Button) {
			return new ButtonRenderer((Button) baseWidget);
		}
		if (baseWidget instanceof InputText) {
			return new InputTextRenderer((InputText) baseWidget); 
		}
		if (baseWidget instanceof Container) {
			return new ContainerRenderer((Container) baseWidget);
		}
		if (baseWidget instanceof Select) {
			return new SelectRenderer((Select) baseWidget);
		}
		if (baseWidget instanceof Link)
			return new LinkRenderer((Link) baseWidget);
		if (baseWidget instanceof HistoryHandler)
			return new HistoryHandlerRenderer((HistoryHandler) baseWidget);
		if (baseWidget instanceof ModalDialog)
			return new ModalDialogRenderer((ModalDialog) baseWidget);
		throw new RuntimeException("No renderer for widget: " + baseWidget);
	}

}
