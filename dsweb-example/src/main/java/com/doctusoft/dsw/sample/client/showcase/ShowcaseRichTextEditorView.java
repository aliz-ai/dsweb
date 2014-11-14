package com.doctusoft.dsw.sample.client.showcase;

import com.doctusoft.dsw.client.comp.Alert;
import com.doctusoft.dsw.client.comp.Alert.AlertType;
import com.doctusoft.dsw.client.comp.Button;
import com.doctusoft.dsw.client.comp.InputText;
import com.doctusoft.dsw.client.comp.RichTextEditor;
import com.doctusoft.dsw.sample.client.AbstractViewWithNavBar;

public class ShowcaseRichTextEditorView extends AbstractViewWithNavBar<ShowcaseRichTextEditorPresenter> {

	public ShowcaseRichTextEditorView() {
		new Alert(
				"Ha egy új szót a @ karakterrel kezdesz, feljön az autocomplete menü. Az Add option gombbal bővül a választható option-ök listája további dummy opciókkal.")
				.setAlertType(AlertType.Information).appendTo(subContainer);
		new RichTextEditor().bind(bindOnPresenter().get(ShowcaseRichTextEditorPresenter_._content2))
		.bindAutoCompleteOptions(bindOnPresenter().get(ShowcaseRichTextEditorPresenter_._options))
		.withAutocompleteTriggerCharacter('@').withTextToInsertBeforeAutoCompleteValue("@{")
		.appendTo(subContainer);
		new InputText().bind(bindOnPresenter().get(ShowcaseRichTextEditorPresenter_._content2)).appendTo(subContainer);
		new Button("Add option").click(presenterMethod(ShowcaseRichTextEditorPresenter_.__addOption))
				.appendTo(subContainer);
	}
}
