
package com.doctusoft.dsw.client.gwt;

import com.doctusoft.bean.ValueChangeListener;
import com.doctusoft.dsw.client.comp.model.RichTextEditorModel;
import com.doctusoft.dsw.client.comp.model.RichTextEditorModel_;
import com.google.common.base.Objects;
import com.xedge.jquery.client.JQEvent;
import com.xedge.jquery.client.JQuery;
import com.xedge.jquery.client.handlers.EventHandler;

public class RichTextEditorRenderer extends BaseComponentRenderer {
	
	public RichTextEditorRenderer( final RichTextEditorModel model ) {
		super( JQuery.select( "<form method=\"post\"><textarea name=\"content\" id=\"" + model.getId()
			+ "\" style=\"width:100%\"></textarea></form>" ),
			model );
		init( widget );
		String modelContent = model.getContent();
		widget.html( Objects.firstNonNull( modelContent, "" ) );
		
		RichTextEditorModel_._content.addChangeListener( model, new ValueChangeListener<String>() {
			
			@Override
			public void valueChanged( String content ) {
				widget.html( content );
			}
		} );
		
		widget.keypress( new EventHandler() {
			
			@Override
			public void eventComplete( JQEvent event, JQuery currentJQuery ) {
				model.setContent( widget.html() );
				
				//TODO ez igy nem jo
				
			}
		} );
	}
	
	private native void init( JQuery widget ) /*-{
		widget.tinymce({
			script_url : 'tinymce/js/tinymce/tinymce.min.js',
			theme : "modern"
		});
	}-*/;
	
}
