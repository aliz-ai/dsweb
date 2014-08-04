package com.doctusoft.dsw.client.mvp;

import java.io.Serializable;

import com.doctusoft.dsw.mvp.client.ViewOf;

/**
 * Usign the ActualPresenter type parameter might be an overkill for type-safety. It introduces a lot of typing with
 * subtypes (see {@link AbstractPresenter} or {@link Place})
 */
public interface Presenter<ActualPresenter extends Presenter<ActualPresenter>> extends Serializable {

	public void start(Place<ActualPresenter> place);

	public void onUnload();

	public ViewOf<ActualPresenter> getView();
}
