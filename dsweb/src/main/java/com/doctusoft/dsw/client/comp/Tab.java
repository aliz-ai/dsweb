package com.doctusoft.dsw.client.comp;

import java.io.Serializable;

import lombok.Getter;

import com.doctusoft.ObservableProperty;
import com.doctusoft.bean.ModelObject;
import com.doctusoft.dsw.client.comp.model.BaseComponentModel;

@Getter
public class Tab implements Serializable, ModelObject{

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
