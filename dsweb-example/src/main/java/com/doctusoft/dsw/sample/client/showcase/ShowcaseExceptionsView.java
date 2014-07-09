package com.doctusoft.dsw.sample.client.showcase;

import com.doctusoft.bean.binding.EmptyEventHandler;
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
		new HtmlContent("<hr/>").appendTo(container);
		new Button("Cause sync error")
			.click(new EmptyEventHandler() {
				@Override
				public void handle() {
					throw new RuntimeException("sync error caused");
				}
			})
			.appendTo(container);
	}

}
