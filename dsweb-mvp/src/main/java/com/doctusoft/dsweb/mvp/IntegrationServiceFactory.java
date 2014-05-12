package com.doctusoft.dsweb.mvp;

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
