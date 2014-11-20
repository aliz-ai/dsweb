package com.doctusoft.dsw.sample.client.showcase;

import com.doctusoft.dsw.client.comp.BaseContainer;
import com.doctusoft.dsw.client.comp.HtmlContent;
import com.doctusoft.dsw.client.comp.Label;
import com.doctusoft.dsw.client.comp.TabSheet;
import com.doctusoft.dsw.sample.client.BaseShowcaseView;

public class ShowcaseTabsheetView extends BaseShowcaseView<ShowcaseTabsheetPresenter> {

	public ShowcaseTabsheetView() {
		new BaseContainer().withStyleClass("page-header").appendTo(subContainer)
		.add(new HtmlContent("<h1>Tabsheet</h1>"));
		new TabSheet()
		.withDefaultTab("Tab1", new Label().withLabel("Valami").getComponentModel())
			.withDefaultTab("Tab2", new Label().withLabel("Valami2").getComponentModel())
			.appendTo(subContainer);
	}

}
