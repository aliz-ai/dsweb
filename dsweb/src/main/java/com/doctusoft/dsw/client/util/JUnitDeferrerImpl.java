package com.doctusoft.dsw.client.util;

import java.util.List;

import com.doctusoft.dsw.client.util.DeferredFactory.Deferrer;
import com.google.common.collect.Lists;

public class JUnitDeferrerImpl implements Deferrer {
	
	private static List<Runnable> scheduledRunnables = Lists.newArrayList();
	
	public JUnitDeferrerImpl() {
		DeferredFactory.deferrer = this;
	}
	
	public static void fireScheduledRunnables() {
		List<Runnable> currentRunnables = Lists.newArrayList(scheduledRunnables);
		scheduledRunnables.clear();
		for (Runnable runnable : currentRunnables) {
			runnable.run();
		}
	}
	
	@Override
	public DeferredRunnable defer(final Runnable runnable) {
		scheduledRunnables.add(runnable);
		return new DeferredRunnable() {
			
			@Override
			public void cancel() {
				scheduledRunnables.remove(runnable);
			}
		};
	}

}
