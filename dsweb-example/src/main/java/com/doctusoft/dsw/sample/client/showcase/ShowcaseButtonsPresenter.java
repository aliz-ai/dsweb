package com.doctusoft.dsw.sample.client.showcase;

import java.io.Serializable;

import lombok.Getter;

import com.doctusoft.dsw.mvp.client.ViewOf;
import com.doctusoft.dsw.sample.client.ClientFactory;

public class ShowcaseButtonsPresenter extends com.doctusoft.dsw.client.mvp.AbstractPresenter<ShowcaseButtonsPresenter>{

	@Getter
	private ViewOf<ShowcaseButtonsPresenter> view;
	
	public ShowcaseButtonsPresenter(Place place, ClientFactory clientFactory ) {
		view = clientFactory.getShowcaseButtonsView();
	}
	
	public static class Place extends com.doctusoft.dsw.client.mvp.AbstractPlace<ShowcaseButtonsPresenter> implements Serializable {
		public Place() {
			super("showcasebuttons", ShowcaseButtonsPresenter.class );
		}
	}

}
