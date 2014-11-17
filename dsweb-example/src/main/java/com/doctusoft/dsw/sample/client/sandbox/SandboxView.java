package com.doctusoft.dsw.sample.client.sandbox;

import com.doctusoft.dsw.client.comp.InputTags;
import com.doctusoft.dsw.client.comp.mvp.ContainerWithPresenter;

public class SandboxView extends ContainerWithPresenter<SandboxPresenter> {
	
	public SandboxView() {
		new InputTags()
			.bind(bindOnPresenter().get(SandboxPresenter_._tags))
			.appendTo(container);
	}

}
