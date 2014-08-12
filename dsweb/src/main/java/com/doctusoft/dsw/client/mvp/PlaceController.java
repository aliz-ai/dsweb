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

import lombok.Getter;

import com.doctusoft.bean.GenericListeners;
import com.doctusoft.bean.ListenerRegistration;
import com.google.common.base.Objects;

public class PlaceController implements Serializable {

	@Getter
	protected Place<?> currentPlace = null;
	@Getter
	protected Presenter<?> currentPresenter = null;
	private PlacePresenterMapper mapper;

	private PresenterStartedListeners presenterStartedListeners = new PresenterStartedListeners();

	public PlaceController(PlacePresenterMapper mapper) {
		this.mapper = mapper;
	}

	public <Presenter extends com.doctusoft.dsw.client.mvp.Presenter<Presenter>> void goTo(Place<Presenter> place) {
		if (currentPresenter != null) {
			currentPresenter.onUnload();
		}
		if (Objects.equal(currentPlace, place))
			return;	// don't navigate to the same place multiple times
		currentPlace = place;
		Presenter presenter = mapper.getPresenter(place);
		currentPresenter = presenter;
		presenter.getView().setPresenter(presenter);
		presenter.start(place);
		presenter.getView().viewPresented();
		presenterStartedListeners.presenterStarted(presenter, place);
	}

	public ListenerRegistration addPresenterStartedListener(PresenterStartedListener listener) {
		return presenterStartedListeners.addListener(listener);
	}

	public interface PresenterStartedListener extends Serializable {
		public void presenterStarted(Presenter<?> presenter, Place<?> place);
	}

	protected class PresenterStartedListeners extends GenericListeners<PresenterStartedListener> {
		public void presenterStarted(final Presenter<?> presenter, final Place<?> place) {
			forEachListener(new ListenerCallback<PlaceController.PresenterStartedListener>() {
				@Override
				public void apply(PresenterStartedListener listener) {
					listener.presenterStarted(presenter, place);
				}
			});
		}
	}
}
