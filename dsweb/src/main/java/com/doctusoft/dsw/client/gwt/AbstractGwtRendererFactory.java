package com.doctusoft.dsw.client.gwt;

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
