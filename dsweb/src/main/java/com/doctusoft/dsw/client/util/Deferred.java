package com.doctusoft.dsw.client.util;

import java.util.Set;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import com.google.common.collect.Sets;
import com.google.gwt.user.client.Timer;

public class Deferred {
	
	private static Set<Key> deferredRunnables = Sets.newHashSet();

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

	/**
	 * Run the action in a deferred way as above, but runs it at most once. The task is identified by the given id
	 */
	public static void defer(Object caller, String id, final Runnable runnable) {
		final Key key = new Key(caller, id);
		if (deferredRunnables.contains(key))
			return;
		deferredRunnables.add(key);
		defer(new Runnable() {
			@Override
			public void run() {
				deferredRunnables.remove(key);
				runnable.run();
			}
		});
	}
	
	@AllArgsConstructor @EqualsAndHashCode @Getter
	private static class Key {
		private Object caller;
		private String id;
	}
}
