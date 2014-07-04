package com.doctusoft.dsw.client.comp;

import com.doctusoft.dsw.client.comp.model.TypeaheadModel;


public class Typeahead<T> extends AbstractSelect<Typeahead, TypeaheadModel, T> {
	
	public Typeahead() {
		super(new TypeaheadModel());
	}
	
	public Typeahead(boolean showAllOnFocus) {
		this();
		model.setAllVisibleOnFocus(true);
	}
	
}