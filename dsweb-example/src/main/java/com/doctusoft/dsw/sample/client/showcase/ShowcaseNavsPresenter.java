package com.doctusoft.dsw.sample.client.showcase;

import java.io.Serializable;

import lombok.Getter;

import com.doctusoft.dsw.mvp.client.ViewOf;
import com.doctusoft.dsw.sample.client.ClientFactory;

public class ShowcaseNavsPresenter extends com.doctusoft.dsw.client.mvp.AbstractPresenter<ShowcaseNavsPresenter>{

	@Getter
	private ViewOf<ShowcaseNavsPresenter> view;
	
	public ShowcaseNavsPresenter(Place place, ClientFactory clientFactory ) {
		view = clientFactory.getShowcaseNavsView();
	}
	
	public static class Place extends com.doctusoft.dsw.client.mvp.AbstractPlace<ShowcaseNavsPresenter> implements Serializable {
		public Place() {
			super("showcasenavs", ShowcaseNavsPresenter.class );
		}
	}
}
