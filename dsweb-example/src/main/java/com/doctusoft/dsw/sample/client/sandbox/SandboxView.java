package com.doctusoft.dsw.sample.client.sandbox;

import com.doctusoft.dsw.client.comp.FixedInputTagsRemote;
import com.doctusoft.dsw.client.comp.Label;
import com.doctusoft.dsw.client.comp.mvp.ContainerWithPresenter;

public class SandboxView extends ContainerWithPresenter<SandboxPresenter> {
	
	public SandboxView() {
		FixedInputTagsRemote<String> tags = new FixedInputTagsRemote<String>()
			.bind(bindOnPresenter().get(SandboxPresenter_._tags))
			.bindQueryString(bindOnPresenter().get(SandboxPresenter_._queryString))
			.bindTagSuggestions(bindOnPresenter().get(SandboxPresenter_._suggestions))
			.appendTo(container);
		new Label().bind(bindOnPresenter().get(SandboxPresenter_._valuesLabel)).appendTo(container);
	}

}
