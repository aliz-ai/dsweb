package com.doctusoft.dsw.client.gwt;

import com.doctusoft.dsw.client.AbstractRendererFactory;
import com.doctusoft.dsw.client.Renderer;
import com.doctusoft.dsw.client.comp.model.AlertModel;
import com.doctusoft.dsw.client.comp.model.BaseComponentModel;
import com.doctusoft.dsw.client.comp.model.ButtonModel;
import com.doctusoft.dsw.client.comp.model.CellModel;
import com.doctusoft.dsw.client.comp.model.CheckboxModel;
import com.doctusoft.dsw.client.comp.model.ContainerModel;
import com.doctusoft.dsw.client.comp.model.DropdownButtonModel;
import com.doctusoft.dsw.client.comp.model.HistoryHandlerModel;
import com.doctusoft.dsw.client.comp.model.HtmlContentModel;
import com.doctusoft.dsw.client.comp.model.IconModel;
import com.doctusoft.dsw.client.comp.model.InputTagsModel;
import com.doctusoft.dsw.client.comp.model.InputTextModel;
import com.doctusoft.dsw.client.comp.model.LabelModel;
import com.doctusoft.dsw.client.comp.model.LinkModel;
import com.doctusoft.dsw.client.comp.model.ModalDialogModel;
import com.doctusoft.dsw.client.comp.model.SelectModel;
import com.doctusoft.dsw.client.comp.model.TextareaModel;
import com.doctusoft.dsw.client.comp.model.TypeaheadModel;
import com.xedge.jquery.client.JQuery;

public class RendererFactoryImpl extends AbstractRendererFactory<JQuery> {
	
	@Override
	public Renderer<JQuery> resolveRenderer(BaseComponentModel baseWidget) {
		if (baseWidget instanceof TypeaheadModel) {
			return new TypeaheadRenderer((TypeaheadModel) baseWidget);
		}
		if (baseWidget instanceof InputTagsModel) {
			return new InputTagsRenderer((InputTagsModel) baseWidget);
		}
		if (baseWidget instanceof LabelModel) {
			return new LabelRenderer((LabelModel) baseWidget);
		}
		if (baseWidget instanceof ButtonModel) {
			return new ButtonRenderer((ButtonModel) baseWidget);
		}
		if (baseWidget instanceof InputTextModel) {
			return new InputTextRenderer((InputTextModel) baseWidget);
		}
		if (baseWidget instanceof DropdownButtonModel) {
			return new DropdownButtonRenderer((DropdownButtonModel) baseWidget);
		}
		if (baseWidget instanceof AlertModel) {
			return new AlertRenderer((AlertModel) baseWidget);
		}
		if (baseWidget instanceof ContainerModel) {
			return new ContainerRenderer((ContainerModel) baseWidget);
		}
		if (baseWidget instanceof SelectModel) {
			return new SelectRenderer((SelectModel) baseWidget);
		}
		if (baseWidget instanceof LinkModel) {
			return new LinkRenderer((LinkModel) baseWidget);
		}
		if (baseWidget instanceof HistoryHandlerModel) {
			return new HistoryHandlerRenderer((HistoryHandlerModel) baseWidget);
		}
		if (baseWidget instanceof ModalDialogModel) {
			return new ModalDialogRenderer((ModalDialogModel) baseWidget);
		}
		if (baseWidget instanceof CheckboxModel) {
			return new CheckboxRenderer((CheckboxModel) baseWidget);
		}
		if (baseWidget instanceof TextareaModel) {
			return new TextareaRenderer((TextareaModel) baseWidget);
		}
		if (baseWidget instanceof CellModel) {
			return new CellRenderer((CellModel) baseWidget);
		}
		if (baseWidget instanceof IconModel) {
			return new IconRenderer((IconModel) baseWidget);
		}
		if (baseWidget instanceof HtmlContentModel) {
			return new HtmlContentRenderer((HtmlContentModel) baseWidget);
		}

		return null;
	}
	
}
