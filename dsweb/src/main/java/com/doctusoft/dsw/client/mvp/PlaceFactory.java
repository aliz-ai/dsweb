package com.doctusoft.dsw.client.mvp;

public interface PlaceFactory {
	
	<P extends Presenter<P>> Place<P> createPlaceForClass(Class<? extends Place<?>> placeClass);

}
