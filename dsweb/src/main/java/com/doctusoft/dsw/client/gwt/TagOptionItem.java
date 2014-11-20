package com.doctusoft.dsw.client.gwt;

import com.google.gwt.core.client.JavaScriptObject;

/**
 * Helper class to build JavascriptObjects from TagOption
 */
public class TagOptionItem extends JavaScriptObject
{
    protected TagOptionItem(){}

    public final native String setValue(String value)/*-{ 
        this.value = value; 
    }-*/;

    public final native String setStyle(String style)/*-{ 
        this.style = style; 
    }-*/;
    
    public final native String setText(String text)/*-{ 
    	this.text = text; 
	}-*/;

	public final native String getValue()/*-{ 
        return this.value; 
    }-*/;

    public final native String getStyle()/*-{ 
        return this.style; 
    }-*/;
    
    public final native String getText()/*-{ 
    return this.text; 
	}-*/;

}

