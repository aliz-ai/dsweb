package com.doctusoft.dsw.sample.client.showcase;

import java.io.Serializable;

import lombok.Getter;

import com.doctusoft.dsw.mvp.client.ViewOf;
import com.doctusoft.dsw.sample.client.ClientFactory;

public class ShowcaseNavsActivity extends com.doctusoft.dsw.client.mvp.AbstractPresenter<ShowcaseNavsActivity>{

	@Getter
	private ViewOf<ShowcaseNavsActivity> view;
	
	public ShowcaseNavsActivity(Place place, ClientFactory clientFactory ) {
		view = clientFactory.getShowcaseNavsView();
	}
	
	public static class Place extends com.doctusoft.dsw.client.mvp.AbstractPlace<ShowcaseNavsActivity> implements Serializable {
		public Place() {
			super("showcasenavs", ShowcaseNavsActivity.class );
		}
	}
}
