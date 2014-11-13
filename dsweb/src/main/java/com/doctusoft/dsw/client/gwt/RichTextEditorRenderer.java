package com.doctusoft.dsw.client.gwt;

import com.doctusoft.bean.ValueChangeListener;
import com.doctusoft.bean.binding.Bindings;
import com.doctusoft.bean.binding.observable.ListBindingListener;
import com.doctusoft.bean.binding.observable.ObservableList;
import com.doctusoft.dsw.client.comp.model.BaseComponentModel_;
import com.doctusoft.dsw.client.comp.model.RichTextEditorModel;
import com.doctusoft.dsw.client.comp.model.RichTextEditorModel_;
import com.doctusoft.dsw.client.util.JsArrayExtended;
import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.core.client.JsArray;
import com.xedge.jquery.client.JQuery;

public class RichTextEditorRenderer extends BaseComponentRenderer {

	private RichTextEditorModel model;

	private JsArrayExtended<JavaScriptObject> autoCompleteValues = JavaScriptObject.createArray().cast();
	
	private boolean isAttached = true;
	
	protected JavaScriptObject editor;
	
	private static long innerIdCounter = 1;
	private String innerIdClass = "dsweb-tinymce-" + (innerIdCounter ++);

	public RichTextEditorRenderer(final RichTextEditorModel model) {
		super(JQuery.select("<textarea name=\"content\"></textarea>"),
				model);
		this.model = model;

		widget.addClass(innerIdClass);
		reinit();

		RichTextEditorModel_._content.addChangeListener(model, new ValueChangeListener<String>() {

			@Override
			public void valueChanged(String content) {
				if (isAttached) {
					setContent(content);
				}
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
	
	protected void reinit() {
		init(widget, innerIdClass, model.getContent(), autoCompleteValues, model.getAutocompleteTriggerCharacter().toString(),
				model.getTextToInsertBeforeAutoCompleteValue(), model.getTextToInsertAfterAutoCompleteValue());
	}
	
	@Override
	public void reattach() {
		reinit();
		isAttached = true;
	}
	
	@Override
	public void detach() {
		isAttached = false;
		removeEditor();
		editor = null;
	}

	private native JavaScriptObject createOption(String optionName)/*-{
		return {
			name : optionName
		};
	}-*/;

	private void editorContentChanged(String content) {
		model.setContent(content);
	}
	
	public void setEditor(JavaScriptObject editor) {
		this.editor = editor;
	}

	private native void setContent(String content) /*-{
		var editor = this.@com.doctusoft.dsw.client.gwt.RichTextEditorRenderer::editor;
		if (editor.getContent() != content) {
			editor.setContent(content);
		}
	}-*/;
	
	private native void setEnabled(boolean enabled) /*-{
		var editor = this.@com.doctusoft.dsw.client.gwt.RichTextEditorRenderer::editor;
		editor.getBody().setAttribute('contenteditable', enabled);
	}-*/;

	private native void removeEditor() /*-{
		var editor = this.@com.doctusoft.dsw.client.gwt.RichTextEditorRenderer::editor;
		editor.remove();
	}-*/;

	private native void init(JQuery widget, String idClass, String content, JsArray<JavaScriptObject> mentionSources,
			String autoCompleteTriggerCharacter, String textToInsertBeforeAutoCompleteValue,
			String textToInsertAfterAutoCompleteValue) /*-{
		var that = this;
		widget.val(content);
		setTimeout(function() {
			$wnd.tinymce.init({
				selector : "." + idClass,
				theme : "modern",
				plugins : "mention",
				toolbar: 
			        "undo redo | styleselect | bold italic | link image | alignleft aligncenter alignright | sizeselect | fontselect |  fontsizeselect",
				mentions: {
				    source: mentionSources,
				    insert: function(item) {
					    return textToInsertBeforeAutoCompleteValue + item.name + textToInsertAfterAutoCompleteValue;
					}, 
					delimiter: autoCompleteTriggerCharacter
				},
				setup : function(editor) {
					that.@com.doctusoft.dsw.client.gwt.RichTextEditorRenderer::setEditor(Lcom/google/gwt/core/client/JavaScriptObject;)(editor);
					editor.on('change', function(e) {
						that.@com.doctusoft.dsw.client.gwt.RichTextEditorRenderer::editorContentChanged(Ljava/lang/String;)(editor.getContent());
					});
				}
			});
		}, 1);
	}-*/;

}
