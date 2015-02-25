package com.doctusoft.dsw.sample.client.sandbox;

import com.doctusoft.bean.binding.EmptyEventHandler;
import com.doctusoft.dsw.client.comp.BaseContainer;
import com.doctusoft.dsw.client.comp.Button;
import com.doctusoft.dsw.client.comp.Label;
import com.doctusoft.dsw.client.comp.Link;
import com.doctusoft.dsw.client.comp.Tab;
import com.doctusoft.dsw.client.comp.TabSheet;
import com.doctusoft.dsw.client.comp.mvp.ContainerWithPresenter;

public class SandboxView extends ContainerWithPresenter<SandboxPresenter> {
	
	private BaseContainer titleComp;
	private Tab compTab;
	private Tab stringTab;

	public SandboxView() {
		compTab = new Tab();
		stringTab = new Tab().withTitle("Valami").withContent(new Label("Valami").getComponentModel());
		titleComp = new BaseContainer("li").add(new Link("Component").withStyleClass("disabled"));
		new TabSheet()
			.withTab(stringTab)
			.withTab(compTab.withTitleComponent(titleComp).withContent(new Label("Component content")))
			.appendTo(container);
		new Button("Vált").click(new EmptyEventHandler() {
			
			@Override
			public void handle() {
				stringTab.setTitle("Másis");
				//titleComp = new BaseContainer("li").add(new Link("Másik").withStyleClass("ez is más hehe"));
				compTab.setTitleComponent(new BaseContainer("li").add(new Link("Másik").withStyleClass("ez is más hehe")).getComponentModel());
			}
		}).appendTo(container);
	}

}
