package com.doctusoft.dsw.client.util;

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
