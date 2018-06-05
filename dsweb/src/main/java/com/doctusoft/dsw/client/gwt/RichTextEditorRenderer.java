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

	private final RichTextEditorModel model;

	private final JsArrayExtended<JavaScriptObject> autoCompleteValues = JavaScriptObject.createArray().cast();

	private boolean isAttached = false;

	protected JavaScriptObject editor;

	private static long innerIdCounter = 1;
	private final String innerIdClass = "dsweb-tinymce-" + (innerIdCounter ++);

	public RichTextEditorRenderer(final RichTextEditorModel model) {
		super(JQuery.select("<textarea name=\"content\"></textarea>"),
				model);
		this.model = model;

		widget.addClass(innerIdClass);
		reinit();

		addChangeListener(RichTextEditorModel_._content, model, new ValueChangeListener<String>() {

			@Override
			public void valueChanged(final String content) {
				if (isAttached) {
					setContent(content);
				}
			}
		});

		new ListBindingListener<String>(Bindings.obs(model).get(RichTextEditorModel_._autoCompleteOptions)) {

			@Override
			public void inserted(final ObservableList<String> list, final int index, final String element) {
				autoCompleteValues.add(index, createOption(element));
			}

			@Override
			public void removed(final ObservableList<String> list, final int index, final String element) {
				autoCompleteValues.remove(index);
			}
		};

		addChangeListenerAndApply(BaseComponentModel_._enabled, model, new ValueChangeListener<Boolean>() {

			@Override
			public void valueChanged(final Boolean newValue) {
				if(isAttached) {
					setEnabled(newValue == null || !newValue ? Boolean.FALSE.toString() : Boolean.TRUE.toString() );
				}
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

	private native JavaScriptObject createOption(final String optionName)/*-{
		return {
			name : optionName
		};
	}-*/;

	private void editorContentChanged(final String content) {
		model.setContent(content);
	}

	public void editorLoaded(final JavaScriptObject editor) {
		this.editor = editor;
		isAttached = true;
		setContent(model.getContent());

		// tricky model refresh
		setEnabled(model.getEnabled() == null || !model.getEnabled() ? Boolean.FALSE.toString() : Boolean.TRUE.toString() );
	}

	private native void setContent(final String content) /*-{
		var editor = this.@com.doctusoft.dsw.client.gwt.RichTextEditorRenderer::editor;
		if (editor === null || (typeof editor === 'undefined')) {
			return;
		}
		setTimeout(function() {
			if (editor.getContent() != content) {
				editor.setContent(content);
			}
		}, 100);
	}-*/;

	/**
	 * Workaround: http://stackoverflow.com/questions/5456363/how-to-disable-tinymce-editor
	 */
	private native void setEnabled(final String enabled) /*-{
		var editor = this.@com.doctusoft.dsw.client.gwt.RichTextEditorRenderer::editor;

		if (editor === null || (typeof editor === 'undefined')) {
			return;
		}

		editor.getBody().setAttribute('contenteditable', enabled);
	}-*/;

	private native void removeEditor() /*-{
		var editor = this.@com.doctusoft.dsw.client.gwt.RichTextEditorRenderer::editor;
		editor.remove();
	}-*/;

	private native void init(final JQuery widget, final String idClass, final String content, final JsArray<JavaScriptObject> mentionSources,
			final String autoCompleteTriggerCharacter, final String textToInsertBeforeAutoCompleteValue,
			final String textToInsertAfterAutoCompleteValue) /*-{
		var that = this;
		widget.val(content);
		setTimeout(function() {
			$wnd.tinymce.init({
				selector : "." + idClass,
				theme : "modern",
				plugins : "mention",
				menubar : false,
				toolbar:
			        "undo redo | styleselect | bold italic | link image | alignleft aligncenter alignright | sizeselect | fontselect |  fontsizeselect",
				mentions: {
				    source: mentionSources,
				    insert: function(item) {
					    return textToInsertBeforeAutoCompleteValue + item.name + textToInsertAfterAutoCompleteValue;
					},
					delimiter: autoCompleteTriggerCharacter
				},
				init_instance_callback: function(editor) {
					that.@com.doctusoft.dsw.client.gwt.RichTextEditorRenderer::editorLoaded(Lcom/google/gwt/core/client/JavaScriptObject;)(editor);
				},
				setup : function(editor) {
					editor.on('change', function(e) {
						that.@com.doctusoft.dsw.client.gwt.RichTextEditorRenderer::editorContentChanged(Ljava/lang/String;)(editor.getContent());
					});
				}
			});
		}, 1);
	}-*/;

}
