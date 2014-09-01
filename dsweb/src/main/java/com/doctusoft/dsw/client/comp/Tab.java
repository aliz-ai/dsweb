package com.doctusoft.dsw.client.comp;

import lombok.Getter;

import com.doctusoft.ObservableProperty;
import com.doctusoft.dsw.client.comp.model.BaseComponentModel;

@Getter
public class Tab {

	private String title;
	
	@ObservableProperty
	private BaseComponentModel content;

	public Tab(String title) {
		this.title = title;
		Container panel = new Container();
		content = panel.getComponentModel();
	}

	public Tab withContent(BaseComponentModel content) {
		this.content = content;
		return this;
	}

}
