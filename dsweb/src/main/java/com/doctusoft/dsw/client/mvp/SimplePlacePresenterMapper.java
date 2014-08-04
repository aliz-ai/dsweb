package com.doctusoft.dsw.client.mvp;

import java.lang.reflect.Constructor;

public class SimplePlacePresenterMapper implements PlacePresenterMapper {
	
	@Override
	public <Presenter extends com.doctusoft.dsw.client.mvp.Presenter<Presenter>> Presenter getPresenter(Place<Presenter> place) {
		try {
			// TODO some more error handling :)
			Constructor<?>[] constructors = place.getPresenterClass().getConstructors();
			return (Presenter) constructors[0].newInstance(place);
		} catch (Exception e) {
			throw new RuntimeException("Could not instantiate presenter for place: " + place, e);
		}
	}

}
