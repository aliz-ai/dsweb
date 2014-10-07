package com.doctusoft.dsw.mvp.client;

import java.io.Serializable;

import com.doctusoft.bean.ListenerRegistration;
import com.doctusoft.bean.binding.ParametricEventHandler;
import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;

/**
 * A GWT-JVM portable, ds-bean friendly eventbus for dsweb and dsweb-servermode projects. 
 */
public class EventBus implements Serializable {
	
	private Multimap<Class<?>, ParametricEventHandler<?>> eventHandlers = ArrayListMultimap.create();
	
	/**
	 * Register a listener for the type given by eventClass. 
	 */
	public <T> ListenerRegistration registerEventHandler(final Class<T> eventClass, final ParametricEventHandler<T> handler) {
		eventHandlers.put(eventClass, handler);
		return new ListenerRegistration() {
			@Override
			public void removeHandler() {
				eventHandlers.remove(eventClass, handler);
			}
		};
	}
	
	public void fireEvent(Object event) {
		fireEvent((Class)event.getClass(), (Object) event);
	}
	
	public <T> void fireEvent(Class<T> eventClass, T event) {
		for (ParametricEventHandler handler : eventHandlers.get(eventClass)) {
			handler.handle(event);
		}
	}
	
}
