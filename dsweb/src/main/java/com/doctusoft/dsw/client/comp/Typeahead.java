package com.doctusoft.dsw.client.comp;

import com.doctusoft.dsw.client.comp.model.TypeaheadModel;


public class Typeahead<T> extends AbstractSelect<Typeahead<T>, TypeaheadModel, T> {
	
	public Typeahead() {
		super(new TypeaheadModel());
	}
	
	public Typeahead<T> showAllOnFocus() {
		model.setAllVisibleOnFocus(true);
		return this;
	}
	
}
