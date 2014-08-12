package com.doctusoft.dsw.mvp;

/*
 * #%L
 * dsweb-mvp
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


import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Arrays;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.google.common.collect.Maps;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.rpc.RemoteService;

public class IntegrationServiceFactory {
	
	private final static Logger log = Logger.getLogger(IntegrationServiceFactory.class.getName());
	
	@SuppressWarnings("unchecked")
	public static <Async> Async getService(final Class<Async> asyncClass, final RemoteService implementation) {
		// map the methods
		final Map<String, Method> methods = Maps.newHashMap();
		for (Method method : implementation.getClass().getMethods()) {
			methods.put(method.getName(), method);
		}
		return (Async) Proxy.newProxyInstance(IntegrationServiceFactory.class.getClassLoader(), new Class<?>[] { asyncClass }, new InvocationHandler() {
			@Override
			public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
				if (args.length > 0 && args[args.length - 1] instanceof AsyncCallback<?>) {
					// if the last argument is AsyncCallback, we rewrite the call
					AsyncCallback<Object> callback = (AsyncCallback<Object>) args[args.length - 1];
					// invoke the method without the last argument
					try {
						Object result = methods.get(method.getName()).invoke(implementation, Arrays.copyOf(args, args.length - 1));
						callback.onSuccess(result);
					} catch (Throwable caught) {
						log.log(Level.SEVERE, "exception on the server side", caught);
						callback.onFailure(caught);
					}
					return null;	// the return type is void
				}
				// if it's a normal method, we expect the original implementation to also have it
				return method.invoke(implementation, args);
			}
		});
	}

}
