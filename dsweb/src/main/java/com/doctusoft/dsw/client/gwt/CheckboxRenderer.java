
package com.doctusoft.dsw.client.gwt;

/*
 * #%L
 * dsweb
 * %%
 * Copyright (C) 2014 Doctusoft Ltd.
 * %%
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public
 * License along with this program. If not, see
 * <http://www.gnu.org/licenses/gpl-3.0.html>.
 * #L%
 */

import com.doctusoft.bean.ValueChangeListener;
import com.doctusoft.dsw.client.comp.model.CheckboxModel;
import com.doctusoft.dsw.client.comp.model.CheckboxModel_;
import com.xedge.jquery.client.JQEvent;
import com.xedge.jquery.client.JQuery;
import com.xedge.jquery.client.handlers.EventHandler;

public class CheckboxRenderer extends BaseComponentRenderer {
	
	public static native void setCheckedNative( JQuery checkbox, boolean isChecked ) /*-{
		checkbox.prop('checked', isChecked);
	}-*/;
	
	public CheckboxRenderer( final CheckboxModel model ) {
		super( JQuery.select( "<label class=\"checkbox\">" ), model );
		final JQuery input = JQuery.select( "<input type=\"checkbox\">" );
		
		input.appendTo( widget );
		input.after( "<span>" + model.getLabel() + "</span>" );
		setCheckedNative( input, model.getChecked().booleanValue() );
		
		CheckboxModel_._checked.addChangeListener( model, new ValueChangeListener<Boolean>() {
			
			@Override
			public void valueChanged( Boolean newValue ) {
				setCheckedNative( input, newValue.booleanValue() );
			}
		} );
		
		input.change( new EventHandler() {
			
			@Override
			public void eventComplete( JQEvent event, JQuery currentJQuery ) {
				model.setChecked( input.is( ":checked" ) );
			}
		} );
		
		CheckboxModel_._label.addChangeListener( model, new ValueChangeListener<String>() {
			
			@Override
			public void valueChanged( String newValue ) {
				input.next().text( newValue );
			}
		} );
	}
	
}
