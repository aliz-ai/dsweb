package com.doctusoft.dsw.client.comp;

import lombok.Getter;

import com.doctusoft.ObservableProperty;
import com.doctusoft.bean.binding.EmptyEventHandler;
import com.doctusoft.dsw.client.comp.model.BaseComponentModel;

@Getter
public class Tab {

	private String title;
	
	@ObservableProperty
	private BaseComponentModel content;

	private EmptyEventHandler eventBeforeTabShown;

	private EmptyEventHandler eventAfterTabHidden;

	public Tab(String title) {
		this.title = title;
		Container panel = new Container();
		content = panel.getComponentModel();
	}

	public Tab withContent(BaseComponentModel content) {
		this.content = content;
		return this;
	}
	public Tab onBeforeTabShown(EmptyEventHandler handler) {
		this.eventBeforeTabShown = handler;
		return this;
	}
	
	public Tab onAfterTabHidden(EmptyEventHandler handler) {
		this.eventAfterTabHidden = handler;
		return this;
	}

}
