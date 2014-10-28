package com.doctusoft.dsw.client.gwt;

import com.doctusoft.bean.ValueChangeListener;
import com.doctusoft.bean.binding.Bindings;
import com.doctusoft.bean.binding.observable.ListBindingListener;
import com.doctusoft.bean.binding.observable.ObservableList;
import com.doctusoft.dsw.client.comp.model.BaseComponentModel_;
import com.doctusoft.dsw.client.comp.model.RichTextEditorModel;
import com.doctusoft.dsw.client.comp.model.RichTextEditorModel_;
import com.doctusoft.dsw.client.util.JsArrayExtended;
import com.google.common.base.Objects;
import com.google.common.base.Strings;
import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.core.client.JsArray;
import com.xedge.jquery.client.JQuery;

public class RichTextEditorRenderer extends BaseComponentRenderer {

	private static Integer editorIdSequence = 0;

	private static final String RICH_TEXT_ID_PREFIX = "richtext";

	private RichTextEditorModel model;

	private JsArrayExtended<JavaScriptObject> autoCompleteValues = JavaScriptObject.createArray().cast();

	public RichTextEditorRenderer(final RichTextEditorModel model) {
		super(JQuery.select("<textarea name=\"content\" id=\""
				+ Objects.firstNonNull(model.getId(), RICH_TEXT_ID_PREFIX + editorIdSequence)
				+ "\" style=\"width:100%\"></textarea>"),
				model);
		this.model = model;
		if (Strings.isNullOrEmpty(model.getId())) {
			model.setId(RICH_TEXT_ID_PREFIX + (editorIdSequence).toString());
			editorIdSequence++;
		}
		String content = model.getContent();
		widget.html(content);

		init(widget, model.getId(), content, autoCompleteValues, model.getAutocompleteTriggerCharacter().toString(),
				model.getTextToInsertBeforeAutoCompleteValue(), model.getTextToInsertAfterAutoCompleteValue());

		RichTextEditorModel_._content.addChangeListener(model, new ValueChangeListener<String>() {

			@Override
			public void valueChanged(String content) {
				setContent(content, model.getId());
			}
		});

		new ListBindingListener<String>(Bindings.obs(model).get(RichTextEditorModel_._autoCompleteOptions)) {

			@Override
			public void inserted(ObservableList<String> list, int index, String element) {
				autoCompleteValues.add(index, createOption(element));
			}

			@Override
			public void removed(ObservableList<String> list, int index, String element) {
				autoCompleteValues.remove(index);
			}
		};
		
		addChangeListenerAndApply(BaseComponentModel_._enabled, model, new ValueChangeListener<Boolean>() {

			@Override
			public void valueChanged(Boolean newValue) {
				// FIXME
				//setEnabled(!Objects.firstNonNull(newValue, false), model.getId());
			}
		});

	}

	private native JavaScriptObject createOption(String optionName)/*-{
		return {
			name : optionName
		};
	}-*/;

	private void editorContentChanged(String content) {
		model.setContent(content);
	}

	private native void setContent(String content, String id)/*-{
		if ($wnd.tinymce.get(id).getContent() != content) {
			$wnd.tinymce.get(id).setContent(content);
		}
	}-*/;
	
	private native void setEnabled(boolean enabled, String id) /*-{
		$wnd.tinymce.get(id).getBody().setAttribute('contenteditable', enabled);
	}-*/;

	private native void init(JQuery widget, String id, String content, JsArray<JavaScriptObject> mentionSources,
			String autoCompleteTriggerCharacter, String textToInsertBeforeAutoCompleteValue,
			String textToInsertAfterAutoCompleteValue) /*-{
		var that = this;
		setTimeout(function() {
			$wnd.tinymce.init({
				selector : "textarea#" + id,
				theme : "modern",
				plugins : "mention",
				toolbar: 
			        "undo redo | styleselect | bold italic | link image | alignleft aligncenter alignright | sizeselect | fontselect |  fontsizeselect",
				content: content,
				mentions: {
				    source: mentionSources,
				    insert: function(item) {
					    return textToInsertBeforeAutoCompleteValue + item.name + textToInsertAfterAutoCompleteValue;
					}, 
					delimiter: autoCompleteTriggerCharacter
				},
				setup : function(editor) {
					editor.on('change', function(e) {
						that.@com.doctusoft.dsw.client.gwt.RichTextEditorRenderer::editorContentChanged(Ljava/lang/String;)(editor.getContent());
					});
				}
			});
			$wnd.proba = mentionSources;
		}, 0);
	}-*/;

}
