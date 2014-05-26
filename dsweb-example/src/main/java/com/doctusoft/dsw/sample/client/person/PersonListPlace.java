package com.doctusoft.dsw.sample.client.person;

import com.doctusoft.dsw.mvp.client.DefaultEmptyTokenizer;
import com.google.gwt.place.shared.Place;

public class PersonListPlace extends Place {
	
	public static class Tokenizer extends DefaultEmptyTokenizer<PersonListPlace> {
		public Tokenizer() {
			super(new PersonListPlace());
		}
		
	}

}
