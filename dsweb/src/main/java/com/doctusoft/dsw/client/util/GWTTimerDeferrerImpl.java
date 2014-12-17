package com.doctusoft.dsw.client.util;

import com.doctusoft.dsw.client.util.DeferredFactory.Deferrer;
import com.google.gwt.user.client.Timer;

public class GWTTimerDeferrerImpl implements Deferrer {
	
	public GWTTimerDeferrerImpl() {
		DeferredFactory.deferrer = this;
	}
	
	/**
	 * Runs the action in a deferred way, with an 1ms timeoout.
	 * It's usually useful when you want to wait for the DOM to be rendered before running an action
	 */
	@Override
	public DeferredRunnable defer(final Runnable runnable) {
		final Timer timer = new Timer() {
			@Override
			public void run() {
				runnable.run();
			}
		};

		timer.schedule(1);

		return new DeferredRunnable() {

			@Override
			public void cancel() {
				timer.cancel();
			}
		};
	}


}
