package com.doctusoft.dsw.client;

import lombok.Getter;

import com.doctusoft.dsw.client.exc.ApplicaitonExceptionEvent;
import com.doctusoft.dsw.client.exc.ExceptionReporter;
import com.doctusoft.dsw.mvp.client.ViewOf;
import com.google.gwt.activity.shared.AbstractActivity;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.place.shared.Place;
import com.google.gwt.user.client.ui.AcceptsOneWidget;

public abstract class BaseActivity<Presenter, ActualPlace extends Place, ClientFactory> extends AbstractActivity implements ExceptionReporter {

	protected ActualPlace place;
	protected ClientFactory clientFactory;
	protected EventBus eventBus;
	@Getter
	private ViewOf<Presenter> view;
	
	public BaseActivity(ClientFactory clientFactory, ActualPlace place) {
		this.clientFactory = clientFactory;
		this.place = place;
	}

	protected abstract ViewOf<Presenter> createView();
	
	@Override
	public void start(AcceptsOneWidget panel, EventBus eventBus) {
		this.eventBus = eventBus;
		view = createView();
		view.setPresenter((Presenter) this);
		panel.setWidget(view);
	}
	
	@Override
	public void reportException(Throwable t) {
		eventBus.fireEvent(new ApplicaitonExceptionEvent(this, t));
	}
}
