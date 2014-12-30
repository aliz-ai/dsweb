package com.doctusoft.dsw.client.comp;

import com.doctusoft.bean.binding.Bindings;
import com.doctusoft.bean.binding.ValueBinding;
import com.doctusoft.bean.binding.observable.ObservableList;
import com.doctusoft.dsw.client.comp.model.RichTextEditorModel;
import com.doctusoft.dsw.client.comp.model.RichTextEditorModel_;

public class RichTextEditor extends BaseComponent<RichTextEditor, RichTextEditorModel> {

	public RichTextEditor(final RichTextEditorModel model) {
		super(model);
	}

	public RichTextEditor() {
		this(new RichTextEditorModel());
	}

	public RichTextEditor bind(final ValueBinding<String> binding) {
		Bindings.bind(binding, Bindings.obs(model).get(RichTextEditorModel_._content));
		return this;
	}

	public RichTextEditor bindAutoCompleteOptions(final ValueBinding<ObservableList<String>> binding) {
		Bindings.bind(binding, Bindings.obs(model).get(RichTextEditorModel_._autoCompleteOptions));
		return this;
	}

	public RichTextEditor withAutocompleteTriggerCharacter(final Character autocompleteTriggerCharacter) {
		model.setAutocompleteTriggerCharacter(autocompleteTriggerCharacter);
		return this;
	}

	public RichTextEditor withTextToInsertBeforeAutoCompleteValue(final String textToInsertBeforeAutoCompleteValue) {
		model.setTextToInsertBeforeAutoCompleteValue(textToInsertBeforeAutoCompleteValue);
		return this;
	}

	public RichTextEditor withTextToInsertAfterValue(final String textToInsertAfterAutoCompleteValue) {
		model.setTextToInsertAfterAutoCompleteValue(textToInsertAfterAutoCompleteValue);
		return this;
	}

}
