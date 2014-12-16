package com.doctusoft.dsw.client.gwt;

import com.google.gwt.core.client.JavaScriptObject;

/**
 * Helper class to build JavascriptObjects from SelectItemModel
 *
 */
public class SelectItemModelItem extends JavaScriptObject {

	protected SelectItemModelItem(){}

	public final native void setCaption(final String caption)/*-{
		this.caption = caption;
	}-*/;

	public final native void setId(final String id)/*-{
		this.id = id;
	}-*/;

	public final native void setMapId(final String mapId)/*-{
		this.mapId = mapId;
	}-*/;

	public final native String getCaption()/*-{
    	return this.caption;
	}-*/;

	public final native String getId()/*-{
    	return this.id;
	}-*/;

	public final native String getMapId()/*-{
    	return this.mapId;
	}-*/;

}
