package com.doctusoft.dsw.sample.client.showcase;

import java.io.Serializable;

import lombok.Getter;

import com.doctusoft.ObservableProperty;
import com.doctusoft.dsw.mvp.client.ViewOf;
import com.doctusoft.dsw.sample.client.ClientFactory;

public class ShowcaseButtonsPresenter extends com.doctusoft.dsw.client.mvp.AbstractPresenter<ShowcaseButtonsPresenter>{

	@Getter
	private ViewOf<ShowcaseButtonsPresenter> view;

	@ObservableProperty
	private boolean editable = true;

	public ShowcaseButtonsPresenter(final Place place, final ClientFactory clientFactory ) {
		view = clientFactory.getShowcaseButtonsView();
	}

	public static class Place extends com.doctusoft.dsw.client.mvp.AbstractPlace<ShowcaseButtonsPresenter> implements Serializable {
		public Place() {
			super("showcasebuttons", ShowcaseButtonsPresenter.class );
		}
	}

}
