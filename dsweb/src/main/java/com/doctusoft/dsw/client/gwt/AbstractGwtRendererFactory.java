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
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public
 * License along with this program.  If not, see
 * <http://www.gnu.org/licenses/gpl-3.0.html>.
 * #L%
 */


import com.doctusoft.dsw.client.AbstractRendererFactory;
import com.doctusoft.dsw.client.Renderer;
import com.doctusoft.dsw.client.comp.model.BaseComponentModel;
import com.google.common.base.Preconditions;
import com.google.gwt.dom.client.Element;
import com.xedge.jquery.client.JQuery;

public abstract class AbstractGwtRendererFactory extends AbstractRendererFactory<JQuery> {
	
	// this method cannot rely on jquery because this one will actually load it :)
	public static native void loadScript(String path) /*-{
		var fileref=document.createElement('script');
  		fileref.setAttribute("type","text/javascript");
  		fileref.setAttribute("src", path);
  		document.getElementsByTagName("head")[0].appendChild(fileref);
	}-*/;
	
	public static native void loadStylesheet(String path) /*-{
		var fileref=document.createElement("link")
	    fileref.setAttribute("rel", "stylesheet")
	    fileref.setAttribute("type", "text/css")
	    fileref.setAttribute("href", path)
  		document.getElementsByTagName("head")[0].appendChild(fileref);
	}-*/;
	
	@Override
	public void dispose(BaseComponentModel baseComponentModel) {
		super.dispose(baseComponentModel);
		// remove the jquery object from its parent, but it will be kept in the DOM without a parent. A total removal of the DOM objects will only happen when the dsweb gc runs
		Renderer<?> renderer = renderers.get(baseComponentModel);
		if (renderer == null)
			return;
		JQuery jquery = (JQuery) renderer.getWidget();
		if (jquery != null) {
			Preconditions.checkState(jquery.length() == 1, "The component " + baseComponentModel + " should have rendered exactly one dom element");
			Element element = jquery.get(0);
			element.getParentNode().removeChild(element);
		}
	}

}
