package com.doctusoft.dsw.sample.client.showcase;

import com.doctusoft.dsw.client.comp.BaseContainer;
import com.doctusoft.dsw.client.comp.Checkbox;
import com.doctusoft.dsw.client.comp.HtmlContent;
import com.doctusoft.dsw.client.comp.InputText;
import com.doctusoft.dsw.client.comp.Label;
import com.doctusoft.dsw.client.comp.Onbeforeunload;
import com.doctusoft.dsw.sample.client.BaseShowcaseView;

public class ShowcaseOnbeforeunloadView extends BaseShowcaseView<ShowcaseOnbeforeunloadPresenter> {

	public ShowcaseOnbeforeunloadView() {
		super();

		new BaseContainer().withStyleClass("page-header").appendTo(subContainer)
		.add(new HtmlContent("<h1>Onbeforeunload</h1>"));

		new Label("Onbeforeunload message value", "h3").appendTo(subContainer);
		new Label()
		.bind(bindOnPresenter().get(ShowcaseOnbeforeunloadPresenter_._onbeforeunloadMessage))
		.appendTo(subContainer);

		new Label("Change Onbeforeunload message value", "h3").appendTo(subContainer);
		new InputText()
		.withPlaceHolder("Onbeforeunload message")
		.bind(bindOnPresenter().get(ShowcaseOnbeforeunloadPresenter_._onbeforeunloadMessage))
		.appendTo(subContainer);

		new Label("Set Onbeforeunload message to null", "h3").appendTo(subContainer);
		new Checkbox()
		.bindChecked(bindOnPresenter().get(ShowcaseOnbeforeunloadPresenter_._onbeforeMessageNull))
		.appendTo(subContainer);

		new Onbeforeunload()
		.bindOnbeforeunloadMessage(bindOnPresenter().get(ShowcaseOnbeforeunloadPresenter_._onbeforeunloadMessage))
		.appendTo(subContainer);

	}

}
