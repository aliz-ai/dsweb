
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

import com.doctusoft.dsw.client.AbstractRendererFactory;
import com.doctusoft.dsw.client.Renderer;
import com.doctusoft.dsw.client.comp.model.BaseComponentModel;
import com.doctusoft.dsw.client.comp.model.ButtonModel;
import com.doctusoft.dsw.client.comp.model.InputTextModel;
import com.doctusoft.dsw.client.comp.model.LabelModel;
import com.doctusoft.dsw.client.comp.model.SelectModel;
import com.doctusoft.html4j.jquery.JQuery;

public class RendererFactoryImpl extends AbstractRendererFactory<JQuery> {
	
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
		if (model instanceof ButtonModel)
			return new ButtonRenderer((ButtonModel) model);
		if (model instanceof LabelModel)
			return new LabelRenderer((LabelModel) model);
		if (model instanceof InputTextModel)
			return new InputTextRenderer((InputTextModel) model);
		if (model instanceof SelectModel)
			return new SelectRenderer((SelectModel) model);
		return null;
	}
}
