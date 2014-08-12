package com.doctusoft.dsw.sample.client.custom;

/*
 * #%L
 * dsweb-example
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
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public
 * License along with this program.  If not, see
 * <http://www.gnu.org/licenses/gpl-3.0.html>.
 * #L%
 */


import com.doctusoft.dsw.client.Renderer;
import com.doctusoft.dsw.client.comp.model.BaseComponentModel;
import com.doctusoft.dsw.client.gwt.RendererFactoryImpl;
import com.xedge.jquery.client.JQuery;

public class CustomRendererFactoryImpl extends RendererFactoryImpl {
	
	@Override
	public Renderer<JQuery> resolveRenderer(BaseComponentModel baseWidget) {
		if (baseWidget instanceof CustomComponentModel)
			return new CustomComponentRenderer((CustomComponentModel) baseWidget);
		return super.resolveRenderer(baseWidget);
	}

}
