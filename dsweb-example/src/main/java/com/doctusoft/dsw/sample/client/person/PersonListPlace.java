package com.doctusoft.dsw.sample.client.person;

import com.doctusoft.gwt.light.mvp.DefaultEmptyTokenizer;
import com.google.gwt.place.shared.Place;

public class PersonListPlace extends Place {
	
	public static class Tokenizer extends DefaultEmptyTokenizer<PersonListPlace> {
		public Tokenizer() {
			super(new PersonListPlace());
		}
		
	}

}
