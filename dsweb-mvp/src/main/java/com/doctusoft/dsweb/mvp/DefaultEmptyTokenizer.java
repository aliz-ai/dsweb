package com.doctusoft.dsweb.mvp;

import com.google.gwt.place.shared.Place;
import com.google.gwt.place.shared.PlaceTokenizer;

public class DefaultEmptyTokenizer<P extends Place> implements PlaceTokenizer<P> {
	
	private final P singletonPlace;

	public DefaultEmptyTokenizer(P singletonPlace) {
		this.singletonPlace = singletonPlace;
	}
	
	@Override
	public P getPlace(String token) {
		return singletonPlace;
	}

	@Override
	public String getToken(P place) {
		return null;
	}

}
