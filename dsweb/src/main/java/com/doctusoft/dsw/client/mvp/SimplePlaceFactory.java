package com.doctusoft.dsw.client.mvp;

import java.io.Serializable;

public class SimplePlaceFactory implements PlaceFactory, Serializable {
	
	@Override
	public <P extends Presenter<P>> Place<P> createPlaceForClass(Class<? extends Place<?>> placeClass) {
		try {
			return (Place<P>) placeClass.newInstance();
		} catch (Exception e) {
			throw new RuntimeException("Exception creating place: " + placeClass);
		}
	}

}
