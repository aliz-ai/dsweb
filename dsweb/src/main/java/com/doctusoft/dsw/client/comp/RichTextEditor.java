
package com.doctusoft.dsw.client.comp;

import com.doctusoft.bean.binding.Bindings;
import com.doctusoft.bean.binding.ValueBinding;
import com.doctusoft.dsw.client.comp.model.RichTextEditorModel;
import com.doctusoft.dsw.client.comp.model.RichTextEditorModel_;

public class RichTextEditor extends BaseComponent<RichTextEditor, RichTextEditorModel> {
	
	public RichTextEditor( RichTextEditorModel model ) {
		super( model );
	}
	
	public RichTextEditor() {
		this( new RichTextEditorModel() );
	}
	
	public RichTextEditor bindContent( ValueBinding<String> binding ) {
		Bindings.bind( binding, Bindings.obs( model ).get( RichTextEditorModel_._content ) );
		return this;
	}
}
