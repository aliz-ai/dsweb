package com.doctusoft.dsw.client.exc;

import com.doctusoft.dsw.client.comp.mvp.ContainerWithPresenter;

/**
 * If your presenter implements this interface, if an event bound by {@link ContainerWithPresenter} throws an exception
 * it will be caught and reported through this interface, and it won't distract UI component code execution 
 */
public interface ExceptionReporter {
	
	public void reportException(Throwable t);

}
