package com.doctusoft.dsw.sample.client.showcase;

import java.io.Serializable;

import lombok.Getter;

import com.doctusoft.dsw.mvp.client.ViewOf;
import com.doctusoft.dsw.sample.client.ClientFactory;

public class ShowcaseProgressBarPresenter extends com.doctusoft.dsw.client.mvp.AbstractPresenter<ShowcaseProgressBarPresenter>{

	@Getter
	private ViewOf<ShowcaseProgressBarPresenter> view;
	
	public ShowcaseProgressBarPresenter(Place place, ClientFactory clientFactory ) {
		view = clientFactory.getShowcaseProgressBarView();
	}
	
	public static class Place extends com.doctusoft.dsw.client.mvp.AbstractPlace<ShowcaseProgressBarPresenter> implements Serializable {
		public Place() {
			super("showcaseprogressbars", ShowcaseProgressBarPresenter.class );
		}
	}

}
