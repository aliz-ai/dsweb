package com.doctusoft.dsw.client.comp;

import com.doctusoft.dsw.client.comp.model.ExecuteJavascriptModel;

/**
 * Not a real component. Registered javascript snippets will get executed on the client side. 
 */
public class ExecuteJavascript extends BaseComponent<ExecuteJavascript, ExecuteJavascriptModel> {

	public ExecuteJavascript() {
		super(new ExecuteJavascriptModel());
	}
	
	public ExecuteJavascript execute(String js) {
		model.getSnippets().add(js);
		return this;
	}
}
