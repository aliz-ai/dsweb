
package com.doctusoft.dsw.client.gwt;

/*
 * #%L
 * dsweb
 * %%
 * Copyright (C) 2014 Doctusoft Ltd.
 * %%
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public
 * License along with this program. If not, see
 * <http://www.gnu.org/licenses/gpl-3.0.html>.
 * #L%
 */

import com.doctusoft.dsw.client.Renderer;
import com.doctusoft.dsw.client.comp.model.AlertModel;
import com.doctusoft.dsw.client.comp.model.BarChartModel;
import com.doctusoft.dsw.client.comp.model.BaseComponentModel;
import com.doctusoft.dsw.client.comp.model.ButtonModel;
import com.doctusoft.dsw.client.comp.model.CellModel;
import com.doctusoft.dsw.client.comp.model.CheckboxModel;
import com.doctusoft.dsw.client.comp.model.ContainerModel;
import com.doctusoft.dsw.client.comp.model.ContextMenuModel;
import com.doctusoft.dsw.client.comp.model.DataTableModel;
import com.doctusoft.dsw.client.comp.model.DatepickerModel;
import com.doctusoft.dsw.client.comp.model.DropdownButtonModel;
import com.doctusoft.dsw.client.comp.model.ExecuteJavascriptModel;
import com.doctusoft.dsw.client.comp.model.HistoryHandlerModel;
import com.doctusoft.dsw.client.comp.model.HtmlContentModel;
import com.doctusoft.dsw.client.comp.model.IconModel;
import com.doctusoft.dsw.client.comp.model.ImageModel;
import com.doctusoft.dsw.client.comp.model.InputNumberModel;
import com.doctusoft.dsw.client.comp.model.InputTagsModel;
import com.doctusoft.dsw.client.comp.model.InputTextModel;
import com.doctusoft.dsw.client.comp.model.InputTimeModel;
import com.doctusoft.dsw.client.comp.model.LabelModel;
import com.doctusoft.dsw.client.comp.model.LinkModel;
import com.doctusoft.dsw.client.comp.model.ModalDialogModel;
import com.doctusoft.dsw.client.comp.model.PieChartModel;
import com.doctusoft.dsw.client.comp.model.ResourceLoaderModel;
import com.doctusoft.dsw.client.comp.model.RichTextEditorModel;
import com.doctusoft.dsw.client.comp.model.SelectModel;
import com.doctusoft.dsw.client.comp.model.TabSheetModel;
import com.doctusoft.dsw.client.comp.model.TextareaModel;
import com.doctusoft.dsw.client.comp.model.TypeaheadModel;
import com.xedge.jquery.client.JQuery;

public class RendererFactoryImpl extends AbstractGwtRendererFactory {
	
	public RendererFactoryImpl() {
		/*
		 * not currently used, needs some more clarification
		 * loadScript("js/jqery-1.10.2.js");
		 * loadScript("js/bootstrap.js");
		 * loadStylesheet("css/bootstrap.css");
		 * loadScript("bootstrap-tagsinput.js");
		 * loadStylesheet("bootstrap-tagsinput.css");
		 * loadScript("datatables/js/jquery.dataTables.js");
		 * loadStylesheet("jquery.dataTables.css");
		 */
	}
	
	@Override
	public Renderer<JQuery> resolveRenderer( BaseComponentModel model ) {
		if (model instanceof InputTimeModel) {
			return new InputTimeRenderer( (InputTimeModel) model );
		}
		if (model instanceof TypeaheadModel) {
			return new TypeaheadRenderer( (TypeaheadModel) model );
		}
		if (model instanceof TabSheetModel) {
			return new TabSheetRenderer( (TabSheetModel) model );
		}
		if (model instanceof InputTagsModel) {
			return new InputTagsRenderer( (InputTagsModel) model );
		}
		if (model instanceof LabelModel) {
			return new LabelRenderer( (LabelModel) model );
		}
		if (model instanceof ButtonModel) {
			return new ButtonRenderer( (ButtonModel) model );
		}
		if (model instanceof InputTextModel) {
			return new InputTextRenderer( (InputTextModel) model );
		}
		if (model instanceof DropdownButtonModel) {
			return new DropdownButtonRenderer( (DropdownButtonModel) model );
		}
		if (model instanceof AlertModel) {
			return new AlertRenderer( (AlertModel) model );
		}
		if (model instanceof ContextMenuModel) {
			return new ContextMenuRenderer( (ContextMenuModel) model );
		}
		if (model instanceof ContainerModel) {
			return new ContainerRenderer( (ContainerModel) model );
		}
		if (model instanceof SelectModel) {
			return new SelectRenderer( (SelectModel) model );
		}
		if (model instanceof LinkModel) {
			return new LinkRenderer( (LinkModel) model );
		}
		if (model instanceof HistoryHandlerModel) {
			return new HistoryHandlerRenderer( (HistoryHandlerModel) model );
		}
		if (model instanceof ModalDialogModel) {
			return new ModalDialogRenderer( (ModalDialogModel) model );
		}
		if (model instanceof CheckboxModel) {
			return new CheckboxRenderer( (CheckboxModel) model );
		}
		if (model instanceof TextareaModel) {
			return new TextareaRenderer( (TextareaModel) model );
		}
		if (model instanceof CellModel) {
			return new CellRenderer( (CellModel) model );
		}
		if (model instanceof IconModel) {
			return new IconRenderer( (IconModel) model );
		}
		if (model instanceof HtmlContentModel) {
			return new HtmlContentRenderer( (HtmlContentModel) model );
		}
		if (model instanceof DataTableModel) {
			return new DataTableRenderer( (DataTableModel) model );
		}
		if (model instanceof ResourceLoaderModel) {
			return new ResourceLoaderRenderer( (ResourceLoaderModel) model );
		}
		if (model instanceof DatepickerModel) {
			return new DatepickerRenderer( (DatepickerModel) model );
		}
		if (model instanceof PieChartModel) {
			return new PieChartRenderer( (PieChartModel) model );
		}
		if (model instanceof BarChartModel) {
			return new BarChartRenderer( (BarChartModel) model );
		}
		if (model instanceof ImageModel) {
			return new ImageRenderer( (ImageModel) model );
		}
		if (model instanceof InputNumberModel) {
			return new InputNumberRenderer( (InputNumberModel) model );
		}
		if (model instanceof ExecuteJavascriptModel) {
			return new ExecuteJavascriptRenderer( (ExecuteJavascriptModel) model );
		}
		if (model instanceof RichTextEditorModel) {
			return new RichTextEditorRenderer( (RichTextEditorModel) model );
		}
		return null;
	}
}
