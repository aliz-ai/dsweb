package com.doctusoft.dsw.client.mvp;

public abstract class AbstractPresenter<ActualPresenter extends AbstractPresenter<ActualPresenter>> implements
        Presenter<ActualPresenter> {

	@Override
	public void start(Place<ActualPresenter> place) {
		// empty default implementation
	}

	@Override
	public void onUnload() {
		// empty default implementation
	}

}
