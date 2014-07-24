
package com.doctusoft.dsw.sample.client.person;

import com.doctusoft.dsw.mvp.client.DefaultEmptyTokenizer;
import com.google.gwt.place.shared.Place;

public class ChartPlace extends Place {
	
	public static class Tokenizer extends DefaultEmptyTokenizer<ChartPlace> {
		
		public Tokenizer() {
			super( new ChartPlace() );
		}
		
	}
	
}
