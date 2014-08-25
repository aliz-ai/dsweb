package com.doctusoft.dsw.client.mvp;

/*
 * #%L
 * dsweb
 * %%
 * Copyright (C) 2014 Doctusoft Ltd.
 * %%
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public
 * License along with this program.  If not, see
 * <http://www.gnu.org/licenses/gpl-3.0.html>.
 * #L%
 */


import java.io.Serializable;
import java.util.Map;

import com.doctusoft.bean.ValueChangeListener;
import com.doctusoft.dsw.client.comp.model.HistoryHandlerModel;
import com.doctusoft.dsw.client.comp.model.HistoryHandlerModel_;
import com.doctusoft.dsw.client.mvp.PlaceController.PresenterStartedListener;
import com.google.common.base.Objects;
import com.google.common.collect.Maps;

public class NavigationHandler implements Serializable {
	protected Map<String, Class<? extends AbstractPlace<?>>> placeClassesByFragmentRoot = Maps.newHashMap();
	protected PlaceController placeController;
	private AbstractPlace<?> defaultPlace;
	private HistoryHandlerModel historyHandlerModel;
	private PlaceFactory placeFactory;

	public NavigationHandler(final HistoryHandlerModel historyHandlerModel, final PlaceController placeController, AbstractPlace<?> defaultPlace,
					PlaceFactory placeFactory) {
		this.historyHandlerModel = historyHandlerModel;
		this.placeController = placeController;
		this.defaultPlace = defaultPlace;
		this.placeFactory = placeFactory;
		HistoryHandlerModel_._historyToken.addChangeListener(historyHandlerModel, new ValueChangeListener<String>() {
			@Override
			public void valueChanged(String newValue) {
				AbstractPlace<?> place = resolvePlace(newValue);
				placeController.goTo(place);
			}
		});
		placeController.addPresenterStartedListener(new PresenterStartedListener() {
			@Override
			public void presenterStarted(Presenter<?> presenter, AbstractPlace<?> place) {
				String currentFragment = historyHandlerModel.getHistoryToken();
				String placeFragment = place.generateFragment();
				if (!Objects.equal(currentFragment, placeFragment)) {
					historyHandlerModel.setHistoryToken(placeFragment);
				}
			}
		});
	}

	public void handleCurrentHistory() {
		AbstractPlace<?> place = resolvePlace(historyHandlerModel.getHistoryToken());
		placeController.goTo(place);
	}

	protected AbstractPlace<?> resolvePlace(String fragment) {
		if (fragment == null)
			return defaultPlace;
		String[] fragmentParts = fragment.split(":");
		String placeName = fragmentParts[0];
		Class<? extends AbstractPlace<?>> placeClass = placeClassesByFragmentRoot.get(placeName);
		if (placeClass == null)
			return defaultPlace;
		AbstractPlace<?> place;
		place = (AbstractPlace) placeFactory.createPlaceForClass(placeClass);
		place.parseFragment(fragment);
		return place;
	}

	/**
	 * Pass instances of the supported places. Just pass an arbitrary instance for parametric places 
	 */
	public void registerPlaces(AbstractPlace<?> ... places) {
		for (AbstractPlace<?> place : places) {
			placeClassesByFragmentRoot.put(place.getFragmentRoot(), (Class) place.getClass());
		}
	}

}
