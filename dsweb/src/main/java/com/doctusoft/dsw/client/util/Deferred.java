package com.doctusoft.dsw.client.util;

import com.google.gwt.user.client.Timer;

public class Deferred {

	/**
	 * Runs the action in a deferred way, with an 1ms timeoout.
	 * It's usually useful when you want to wait for the DOM to be rendered before running an action 
	 */
	public static void defer(final Runnable runnable) {
		new Timer() {
			@Override
			public void run() {
				runnable.run();
			}
		}.schedule(1);
	}

}
