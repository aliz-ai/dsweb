package com.doctusoft.dsw.sample.client.showcase;

import com.doctusoft.dsw.client.comp.BaseContainer;
import com.doctusoft.dsw.client.comp.Button;
import com.doctusoft.dsw.client.comp.HtmlContent;
import com.doctusoft.dsw.client.comp.mvp.ContainerWithPresenter;

public class ShowcaseExceptionsView extends ContainerWithPresenter<ShowcaseActivity> {
	
	public ShowcaseExceptionsView() {
		new BaseContainer().withStyleClass("page-header").appendTo(container)
			.add(new HtmlContent("<h1>Exceptions</h1>"));
		new Button("Dangerous button")
			.click(presenterMethod(ShowcaseActivity_.__dangerousMethod))
			.appendTo(container);
	}

}
