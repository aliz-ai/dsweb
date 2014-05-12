package com.doctusoft.dsweb.mvp;

import lombok.Getter;

import com.google.gwt.place.shared.Place;
import com.google.gwt.place.shared.PlaceController;

public class GwtPlaceControllerWrapper implements IPlaceController {
	
	@Getter
	private PlaceController placeController;

	public GwtPlaceControllerWrapper(PlaceController placeController) {
		this.placeController = placeController;
	}
	
	@Override
	public void goTo(Place newPlace) {
		placeController.goTo(newPlace);
	}

}
