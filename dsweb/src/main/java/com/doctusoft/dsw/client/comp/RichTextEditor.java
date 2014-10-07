package com.doctusoft.dsw.client.comp;

import com.doctusoft.bean.binding.Bindings;
import com.doctusoft.bean.binding.ValueBinding;
import com.doctusoft.bean.binding.observable.ObservableList;
import com.doctusoft.dsw.client.comp.model.RichTextEditorModel;
import com.doctusoft.dsw.client.comp.model.RichTextEditorModel_;

public class RichTextEditor extends BaseComponent<RichTextEditor, RichTextEditorModel> {

	public RichTextEditor(RichTextEditorModel model) {
		super(model);
	}

	public RichTextEditor() {
		this(new RichTextEditorModel());
	}

	public RichTextEditor bindContent(ValueBinding<String> binding) {
		Bindings.bind(binding, Bindings.obs(model).get(RichTextEditorModel_._content));
		return this;
	}

	public RichTextEditor bindAutoCompleteOptions(ValueBinding<ObservableList<String>> binding) {
		Bindings.bind(binding, Bindings.obs(model).get(RichTextEditorModel_._autoCompleteOptions));
		return this;
	}

	public RichTextEditor withAutocompleteTriggerCharacter(Character autocompleteTriggerCharacter) {
		model.setAutocompleteTriggerCharacter(autocompleteTriggerCharacter);
		return this;
	}

	public RichTextEditor withTextToInsertBeforeAutoCompleteValue(String textToInsertBeforeAutoCompleteValue) {
		model.setTextToInsertBeforeAutoCompleteValue(textToInsertBeforeAutoCompleteValue);
		return this;
	}

	public RichTextEditor withTextToInsertAfterValue(String textToInsertAfterAutoCompleteValue) {
		model.setTextToInsertAfterAutoCompleteValue(textToInsertAfterAutoCompleteValue);
		return this;
	}
}
