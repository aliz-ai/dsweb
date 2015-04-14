package com.doctusoft.dsw.sample.client.sandbox;

import com.doctusoft.dsw.client.comp.FreeInputTags;
import com.doctusoft.dsw.client.comp.mvp.ContainerWithPresenter;

public class SandboxView extends ContainerWithPresenter<SandboxPresenter> {
	
	public SandboxView() {
		
		new FreeInputTags()
			.bind(bindOnPresenter().get(SandboxPresenter_._tags))
			.appendTo(container);
	}

}
