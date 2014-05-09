package com.doctusoft.dsw.sample.client.person;

import lombok.Getter;

import com.google.gwt.place.shared.Place;
import com.google.gwt.place.shared.PlaceTokenizer;

public class PersonDetailPlace extends Place {
	
	@Getter
	private long personId;

	public PersonDetailPlace(long personId) {
		this.personId = personId;
	}


	public static class Tokenizer implements PlaceTokenizer<PersonDetailPlace> {
		@Override
		public PersonDetailPlace getPlace(String token) {
			return new PersonDetailPlace(Long.parseLong(token));
		}
		@Override
		public String getToken(PersonDetailPlace place) {
			return Long.toString(place.getPersonId());
		}
	}
}
