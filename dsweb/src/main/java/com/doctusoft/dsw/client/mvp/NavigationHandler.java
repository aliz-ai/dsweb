package com.doctusoft.dsw.client.mvp;

import java.io.Serializable;
import java.util.Map;

import com.doctusoft.bean.ValueChangeListener;
import com.doctusoft.dsw.client.comp.model.HistoryHandlerModel;
import com.doctusoft.dsw.client.comp.model.HistoryHandlerModel_;
import com.doctusoft.dsw.client.mvp.PlaceController.PresenterStartedListener;
import com.google.common.base.Objects;
import com.google.common.collect.Maps;

public class NavigationHandler implements Serializable {
	protected Map<String, Class<? extends Place<?>>> placeClassesByFragmentRoot = Maps.newHashMap();
	protected PlaceController placeController;
	private Place<?> defaultPlace;
	private HistoryHandlerModel historyHandlerModel;
	private PlaceFactory placeFactory;

	public NavigationHandler(final HistoryHandlerModel historyHandlerModel, final PlaceController placeController, Place<?> defaultPlace,
					PlaceFactory placeFactory) {
		this.historyHandlerModel = historyHandlerModel;
		this.placeController = placeController;
		this.defaultPlace = defaultPlace;
		this.placeFactory = placeFactory;
		HistoryHandlerModel_._historyToken.addChangeListener(historyHandlerModel, new ValueChangeListener<String>() {
			@Override
			public void valueChanged(String newValue) {
				Place<?> place = resolvePlace(newValue);
				placeController.goTo(place);
			}
		});
		placeController.addPresenterStartedListener(new PresenterStartedListener() {
			@Override
			public void presenterStarted(Presenter<?> presenter, Place<?> place) {
				String currentFragment = historyHandlerModel.getHistoryToken();
				String placeFragment = place.generateFragment();
				if (!Objects.equal(currentFragment, placeFragment)) {
					historyHandlerModel.setHistoryToken(placeFragment);
				}
			}
		});
	}

	public void handleCurrentHistory() {
		Place<?> place = resolvePlace(historyHandlerModel.getHistoryToken());
		placeController.goTo(place);
	}

	protected Place<?> resolvePlace(String fragment) {
		if (fragment == null)
			return defaultPlace;
		String[] fragmentParts = fragment.split(":");
		String placeName = fragmentParts[0];
		Class<? extends Place<?>> placeClass = placeClassesByFragmentRoot.get(placeName);
		if (placeClass == null)
			return defaultPlace;
		Place<?> place;
		place = placeFactory.createPlaceForClass(placeClass);
		place.parseFragment(fragment);
		return place;
	}

	public void registerPlaces(Class<? extends Place<?>>... placeClasses) {
		for (Class<? extends Place<?>> placeClass : placeClasses) {
			placeClassesByFragmentRoot.put(Place.getFragmentRoot(placeClass), placeClass);
		}
	}

}
