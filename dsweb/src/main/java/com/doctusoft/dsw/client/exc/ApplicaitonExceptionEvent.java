package com.doctusoft.dsw.client.exc;

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
