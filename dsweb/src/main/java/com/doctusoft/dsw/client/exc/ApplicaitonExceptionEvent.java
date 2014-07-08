package com.doctusoft.dsw.client.exc;

import lombok.Getter;

import com.doctusoft.dsw.client.BaseActivity;
import com.doctusoft.dsw.client.comp.mvp.ContainerWithPresenter;
import com.google.gwt.event.shared.EventHandler;
import com.google.web.bindery.event.shared.Event;

/**
 * If an event handler throws an exception and it's properyl caught by {@link ContainerWithPresenter}.presenterMethod
 * wrappers and forwarder to {@link BaseActivity} through {@link ExceptionReporter}, this event will be fired on the event bus.  
 */
public class ApplicaitonExceptionEvent extends Event<ApplicaitonExceptionEvent.Handler> {
	
	@Getter
	private Throwable throwable;
	@Getter
	private BaseActivity presenter;

	public ApplicaitonExceptionEvent(BaseActivity presenter, Throwable throwable) {
		this.presenter = presenter;
		this.throwable = throwable;
	}
	
	public interface Handler extends EventHandler {
		public void onApplicationException(ApplicaitonExceptionEvent event);
	}
	
	static Type<ApplicaitonExceptionEvent.Handler> TYPE = new Type<ApplicaitonExceptionEvent.Handler>();
	
	@Override
	public com.google.web.bindery.event.shared.Event.Type<Handler> getAssociatedType() {
		return TYPE;
	}

	@Override
	protected void dispatch(Handler handler) {
		handler.onApplicationException(this);
	}
}
