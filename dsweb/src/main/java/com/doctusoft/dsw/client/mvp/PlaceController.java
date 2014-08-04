package com.doctusoft.dsw.client.mvp;

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
