package com.doctusoft.dsw.sample.client.showcase;

import lombok.Getter;

import com.google.gwt.place.shared.Place;
import com.google.gwt.place.shared.PlaceTokenizer;

public class ShowcasePlace extends Place {
	
	@Getter
	private ShowcaseItem item;
	
	public ShowcasePlace(ShowcaseItem item) {
		this.item = item;
	}
	
	public static class Tokenizer implements PlaceTokenizer<ShowcasePlace> {
		@Override
		public ShowcasePlace getPlace(String token) {
			return new ShowcasePlace(ShowcaseItem.valueOf(token));
		}
		@Override
		public String getToken(ShowcasePlace place) {
			return place.getItem().name();
		}
	}

}
