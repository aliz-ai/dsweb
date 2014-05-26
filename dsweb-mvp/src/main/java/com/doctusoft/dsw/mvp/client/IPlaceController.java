package com.doctusoft.dsw.mvp.client;

import com.google.gwt.place.shared.Place;
import com.google.gwt.place.shared.PlaceController;

/**
 * An interface to wrap gwt standard {@link PlaceController}, so that we can wrap it for integraion tests 
 */
public interface IPlaceController {
	
	  public void goTo(Place newPlace);

}
