package com.doctusoft.dsw.sample.client.sandbox;

import com.doctusoft.dsw.client.comp.BaseContainer;
import com.doctusoft.dsw.client.comp.Label;
import com.doctusoft.dsw.client.comp.Link;
import com.doctusoft.dsw.client.comp.Tab;
import com.doctusoft.dsw.client.comp.TabSheet;
import com.doctusoft.dsw.client.comp.mvp.ContainerWithPresenter;

public class SandboxView extends ContainerWithPresenter<SandboxPresenter> {
	
	public SandboxView() {
		new TabSheet()
			.withDefaultTab("Valami", new Label("Valami").getComponentModel())
			.withTab(new Tab().withTitleComponent(new BaseContainer("li").add(new Link("Component")).withStyleClass("disabled")).withContent(new Label("Component content").getComponentModel()))
			.appendTo(container);
	}

}
