package com.doctusoft.dsw.sample.client.showcase;

import com.doctusoft.dsw.client.comp.BaseContainer;
import com.doctusoft.dsw.client.comp.Container;
import com.doctusoft.dsw.client.comp.HasComponentModel;
import com.doctusoft.dsw.client.comp.HtmlContent;
import com.doctusoft.dsw.client.comp.Label;
import com.doctusoft.dsw.client.comp.ProgressBar;
import com.doctusoft.dsw.client.comp.ProgressBar.ProgressBarType;
import com.doctusoft.dsw.client.comp.model.BaseComponentModel;

public class ShowcaseProgressBarView implements HasComponentModel {
	
	private Container container = new Container();
	
	public ShowcaseProgressBarView() {
		new BaseContainer().withStyleClass("page-header").appendTo(container)
			.add(new HtmlContent("<h1>Progress Bars</h1>"));
		new Label("Basic", "h3").appendTo(container);
		new ProgressBar().appendTo(container);
		new Label("Striped", "h3").appendTo(container);
		new ProgressBar().setType(ProgressBarType.Striped).appendTo(container);
		new Label("Animated", "h3").appendTo(container);
		new ProgressBar().setType(ProgressBarType.StripedActive).appendTo(container);
	}
	
	@Override
	public BaseComponentModel getComponentModel() {
		return container.getModel();
	}

}
