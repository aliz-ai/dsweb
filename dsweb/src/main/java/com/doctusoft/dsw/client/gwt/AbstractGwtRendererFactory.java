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

}
