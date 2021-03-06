package com.doctusoft.dsw.sample.client.showcase;

import java.io.Serializable;

import lombok.Getter;

import com.doctusoft.ObservableProperty;
import com.doctusoft.dsw.mvp.client.ViewOf;
import com.doctusoft.dsw.sample.client.ClientFactory;

public class ShowcaseInputsPresenter extends com.doctusoft.dsw.client.mvp.AbstractPresenter<ShowcaseInputsPresenter>{

	@Getter
	private ViewOf<ShowcaseInputsPresenter> view;

	@ObservableProperty
	private String placeHolder;

	@ObservableProperty
	private boolean editable = true;

	public ShowcaseInputsPresenter(final Place place, final ClientFactory clientFactory ) {
		view = clientFactory.getShowcaseInputsView();
	}

	public static class Place extends com.doctusoft.dsw.client.mvp.AbstractPlace<ShowcaseInputsPresenter> implements Serializable {
		public Place() {
			super("showcaseinputs", ShowcaseInputsPresenter.class );
		}
	}

}
