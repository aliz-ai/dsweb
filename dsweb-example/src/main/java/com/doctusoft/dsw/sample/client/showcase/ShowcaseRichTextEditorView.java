package com.doctusoft.dsw.sample.client.showcase;

import com.doctusoft.dsw.client.comp.Alert;
import com.doctusoft.dsw.client.comp.Alert.AlertType;
import com.doctusoft.dsw.client.comp.Button;
import com.doctusoft.dsw.client.comp.InputText;
import com.doctusoft.dsw.client.comp.RichTextEditor;
import com.doctusoft.dsw.client.comp.mvp.ContainerWithPresenter;

public class ShowcaseRichTextEditorView extends ContainerWithPresenter<ShowcaseActivity> {

	public ShowcaseRichTextEditorView() {
		new Alert(
				"Ha egy új szót a @ karakterrel kezdesz, feljön az autocomplete menü. Az Add option gombbal bővül a választható option-ök listája további dummy opciókkal.")
				.setAlertType(AlertType.Information).appendTo(container);
		new RichTextEditor().bindContent(bindOnPresenter().get(ShowcaseActivity_._content2))
		.bindAutoCompleteOptions(bindOnPresenter().get(ShowcaseActivity_._options))
		.withAutocompleteTriggerCharacter('@').withTextToInsertBeforeAutoCompleteValue("@{")
		.appendTo(container);
		new InputText().bind(bindOnPresenter().get(ShowcaseActivity_._content2)).appendTo(container);
		new Button("Add option").click(presenterMethod(ShowcaseActivity_.__addOption))
				.appendTo(
						container);
	}
}
