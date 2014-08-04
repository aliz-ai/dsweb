package com.doctusoft.dsw.client.mvp;

import java.io.Serializable;

public interface PlacePresenterMapper extends Serializable {

	public <Presenter extends com.doctusoft.dsw.client.mvp.Presenter<Presenter>> Presenter getPresenter(
	        Place<Presenter> place);

}
