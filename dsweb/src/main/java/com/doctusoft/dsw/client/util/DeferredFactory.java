package com.doctusoft.dsw.client.util;

import com.google.common.base.Preconditions;

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



public class DeferredFactory {

	/* package visible stuff */
	interface Deferrer {
		public DeferredRunnable defer(final Runnable runnable);
	}

	static Deferrer deferrer = null;


	/**
	 * Run the action in a deferred way as above, but runs it at most once. The task is identified by the given id
	 */
	public static DeferredRunnable defer(final DeferredRunnable deferredRunnable, final Runnable runnable) {
		if (deferredRunnable != null) {
			return deferredRunnable;
		}
		Preconditions.checkNotNull(deferrer, "No deferrer implementations is set, please initialize GWTTimerDeferrerImpl or JUnitDeferrerImpl");

		return deferrer.defer(new Runnable() {
			@Override
			public void run() {
				runnable.run();
			}
		});
	}

}
